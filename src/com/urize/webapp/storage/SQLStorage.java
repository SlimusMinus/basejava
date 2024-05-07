package com.urize.webapp.storage;

import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.ContactsType;
import com.urize.webapp.model.Resume;
import com.urize.webapp.sql.SQLHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            insertContacts(resume, statement);
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
        return sqlHelper.execute("" +
                "SELECT * FROM resume r" +
                "  LEFT JOIN contact c ON r.uuid = c.resume_uuid" +
                " ORDER BY full_name, uuid", statement -> {
            ResultSet resultSet = statement.executeQuery();
            List<Resume> list = new ArrayList<>();
            while (resultSet.next()) {
                Resume resume = new Resume(resultSet.getString("uuid"), resultSet.getString("full_name"));
                getContacts(resultSet, resume);
                list.add(resume);
            }
            return list;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("delete from resume where uuid = ?", statement -> {
            statement.setString(1, uuid);
            if (statement.executeUpdate() == 0) {
                throw new StorageNotFoundException(uuid);
            }
            return null;
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