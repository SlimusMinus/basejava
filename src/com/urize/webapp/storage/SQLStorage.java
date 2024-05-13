package com.urize.webapp.storage;

import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.*;
import com.urize.webapp.sql.SQLHelper;

import java.sql.*;
import java.util.*;

public class SQLStorage implements Storage {

    public final SQLHelper sqlHelper;

    public SQLStorage(String URL, String user, String password) {
        sqlHelper = new SQLHelper(() -> DriverManager.getConnection(URL, user, password));
    }

    @Override
    public void clear() {
        sqlHelper.execute("delete from resume");
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    insertContacts(r, conn);
                    insertSections(r, conn);
                    return null;
                }
        );
    }


    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(statement -> {
            try (PreparedStatement ps = statement.prepareStatement("update resume set full_name = ? where uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new StorageNotFoundException(resume.getUuid());
                }
            }
            try (PreparedStatement ps = statement.prepareStatement("delete from contact where resume_uuid = ?")) {
                ps.setString(1, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new StorageNotFoundException(resume.getUuid());
                }
            }
            insertContacts(resume, statement);
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("delete from resume where uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new StorageNotFoundException(uuid);
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        "    select * from resume r " +
                        " left join contact c " +
                        "        on r.uuid = c.resume_uuid " +
                        " left join public.section s" +
                        "        on r.uuid = s.resume_uuid" +
                        "     where r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new StorageNotFoundException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContact(rs, resume);
                    } while (rs.next());
                    return resume;
                });

    }



    @Override
    public List<Resume> getAllSorted() {
       /* return sqlHelper.execute("" +
                "select * from resume " +
                "  left join contact c " +
                "    on resume.uuid = c.resume_uuid " +
                " order by full_name, uuid", ps -> {

            ResultSet resultSet = ps.executeQuery();
            Map<String, Resume> mapResume = new LinkedHashMap<>();
            while (resultSet.next()) {
                String uuid = resultSet.getString("uuid");
                Resume resume = mapResume.get(uuid);
                if (resume == null) {
                    resume = new Resume(uuid, resultSet.getString("full_name"));
                    mapResume.put(uuid, resume);
                }
                addContact(resultSet, resume);
            }
            return new ArrayList<>(mapResume.values());
        });
*/
        return sqlHelper.transactionalExecute(statement -> {
            Map<String, Resume> resumeList = new LinkedHashMap<>();
            try (PreparedStatement ps = statement.prepareStatement("select * from resume order by full_name, uuid")) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    final String uuid = resultSet.getString("uuid");
                    resumeList.put(uuid, new Resume(uuid, resultSet.getString("full_name")));
                }
            }
            try (PreparedStatement ps = statement.prepareStatement("select * from contact")) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    Resume resume = resumeList.get(resultSet.getString("resume_uuid"));
                    addContact(resultSet, resume);
                }
            }
            return new ArrayList<>(resumeList.values());
        });

    }

    @Override
    public int size() {
        return sqlHelper.execute("select count(*) from resume", statement -> {
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        });
    }

    private static void insertContacts(Resume resume, Connection statement) throws SQLException {
        try (PreparedStatement ps = statement.prepareStatement("insert into contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactsType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
    private void addContact(ResultSet rs, Resume r) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            r.addContacts(ContactsType.valueOf(rs.getString("type")), value);
        }
        String typeSection = rs.getString("typeSection");
        if(typeSection != null){
            switch (rs.getString("typeSection")){
                case "PERSONAL", "OBJECTIVE" -> r.addSections(SectionType.valueOf(typeSection), new TextSection(rs.getString("valueSection")));
                case "ACHIEVEMENT", "QUALIFICATIONS" -> addListSection(typeSection, rs, r);
            }
        }
    }

    private void addListSection(String typeSection, ResultSet rs, Resume r) throws SQLException {
        String[] res = rs.getString("valueSection").split("\n");
        List<String> list = new ArrayList<>(Arrays.asList(res));
        r.addSections(SectionType.valueOf(typeSection), new ListSection(list));
    }

    private void insertSections(Resume r, Connection statement) throws SQLException {
        try (PreparedStatement ps = statement.prepareStatement("insert into section (resume_uuid, typeSection, valueSection) values (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> item : r.getSections().entrySet()) {
                String result = "";
                switch (item.getKey()) {
                    case PERSONAL, OBJECTIVE -> result = item.getValue().toString();
                    case ACHIEVEMENT, QUALIFICATIONS -> result = getListSections(result, (ListSection) item.getValue());
                }
                ps.setString(1, r.getUuid());
                ps.setString(2, item.getKey().name());
                ps.setString(3, result);
                ps.executeUpdate();
            }

        }
    }

    private String getListSections(String result, ListSection value) {
        StringBuilder resultBuilder = new StringBuilder(result);
        for (var item : value.getList()) {
            resultBuilder.append(item).append("\n");
        }
        result = resultBuilder.toString();
        return result;
    }


}