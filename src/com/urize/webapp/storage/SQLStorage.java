package com.urize.webapp.storage;

import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.Contacts;
import com.urize.webapp.model.ContactsType;
import com.urize.webapp.model.Resume;
import com.urize.webapp.sql.SQLHelper;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

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
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new StorageNotFoundException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    getContacts(rs, resume);
                    return resume;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(ps -> {
            List<Resume> list = new ArrayList<>();
            Map<String, List<Contacts>> contactsMap = new HashMap<>();

            try (PreparedStatement statement = ps.prepareStatement("SELECT * from resume")) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    String fullName = resultSet.getString("full_name");
                    list.add(new Resume(uuid, fullName));
                    contactsMap.put(uuid, new ArrayList<>());
                }
            }

            try (PreparedStatement statement = ps.prepareStatement("SELECT * from contact")) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String resumeUuid = resultSet.getString("resume_uuid");
                    Contacts contacts = new Contacts(
                            ContactsType.valueOf(resultSet.getString("type")),
                            resultSet.getString("value")
                    );
                    contactsMap.get(resumeUuid).add(contacts);
                }
            }

            list.forEach(item -> {
                List<Contacts> contactsList = contactsMap.get(item.getUuid());
                if (contactsList != null) {
                    contactsList.forEach(contacts -> item.addContacts(contacts.getContactsType(), contacts.getValue()));
                }
            });

            return list.stream().sorted().collect(Collectors.toList());
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
        try (PreparedStatement ps = statement.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactsType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void getContacts(ResultSet rs, Resume r) throws SQLException {
        do {
            String value = rs.getString("value");
            if (value != null) {
                r.addContacts(ContactsType.valueOf(rs.getString("type")), value);
            }
        } while (rs.next());
    }

}