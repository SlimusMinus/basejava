package com.urize.webapp.storage;

import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.Resume;
import com.urize.webapp.storage.sql.SQLHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        sqlHelper.execute("insert into resume values (?,?)", statement -> {
            statement.setString(1, r.getUuid());
            statement.setString(2, r.getFullName());
            statement.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("select * from resume where uuid = (?)", statement -> {
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new StorageNotFoundException(uuid);
            }
            return new Resume(uuid, resultSet.getString("full_name"));
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
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("select * from resume order by full_name", statement -> {
            ResultSet resultSet = statement.executeQuery();
            List<Resume> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Resume(resultSet.getString("uuid"), resultSet.getString("full_name")));
            }
            return list;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("select count(*) from resume", statement -> {
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.execute("update resume set full_name = ? where uuid = ?", statement -> {
            statement.setString(1, resume.getFullName());
            statement.setString(2, resume.getUuid());
            if (statement.executeUpdate() == 0) {
                throw new StorageNotFoundException(resume.getUuid());
            }
            return null;
        });
    }
}