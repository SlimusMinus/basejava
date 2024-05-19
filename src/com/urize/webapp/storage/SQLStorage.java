package com.urize.webapp.storage;

import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.*;
import com.urize.webapp.sql.SQLHelper;
import com.urize.webapp.util.JsonParser;

import java.sql.*;
import java.util.*;

public class SQLStorage implements Storage {

    public final SQLHelper sqlHelper;

    public SQLStorage(String URL, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
                if (ps.executeUpdate() != 1) {
                    throw new StorageNotFoundException(resume.getUuid());
                }
            }
            deleteContactOrSection(statement, "delete from contact where resume_uuid = ?", resume);
            deleteContactOrSection(statement, "delete from section where resume_uuid = ?", resume);

            insertContacts(resume, statement);
            insertSections(resume, statement);

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
                        addSection(rs, resume);
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

            try (PreparedStatement ps = statement.prepareStatement("select * from section")) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    Resume resume = resumeList.get(resultSet.getString("resume_uuid"));
                    addSection(resultSet, resume);
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

    }

    private void addSection(ResultSet rs, Resume resume) throws SQLException {
        final String content = rs.getString("valueSection");

        if (content != null) {
            SectionType type = SectionType.valueOf(rs.getString("typeSection"));
            resume.addSections(type, JsonParser.read(content, AbstractSection.class));
        }
    }

    private void insertSections(Resume r, Connection statement) throws SQLException {
        try (PreparedStatement ps = statement.prepareStatement("insert into section (resume_uuid, typeSection, valueSection) values (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> item : r.getSections().entrySet()) {

                ps.setString(1, r.getUuid());
                ps.setString(2, item.getKey().name());
                AbstractSection sectionType = item.getValue();
                ps.setString(3, JsonParser.write(sectionType, AbstractSection.class));
                ps.executeUpdate();
            }
        }
    }

    private static void deleteContactOrSection(Connection statement, String sql, Resume resume) throws SQLException {
        try (PreparedStatement ps = statement.prepareStatement(sql)) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

}