package com.urize.webapp.storage;

import com.urize.webapp.exception.ResumeExistStorageException;
import com.urize.webapp.exception.StorageNotFoundException;
import com.urize.webapp.model.Resume;
import com.urize.webapp.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLStorage implements Storage {

    public final ConnectionFactory connectionFactory;

    public SQLStorage(String URL, String user, String password) {
        connectionFactory = () -> DriverManager.getConnection(URL, user, password);
    }

    @Override
    public void clear() {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE from resume")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Resume r) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO resume values (?,?)")) {
            statement.setString(1, r.getUuid());
            statement.setString(2, r.getFullName());
            statement.execute();
        } catch (SQLException e) {
            throw new ResumeExistStorageException(r.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * from resume WHERE uuid = (?)")) {
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new StorageNotFoundException(uuid);
            }
            return new Resume(uuid, resultSet.getString("full_name"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String uuid) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE from resume WHERE uuid = ?")) {
            statement.setString(1, uuid);
           if(statement.executeUpdate()==0){
                throw new StorageNotFoundException(uuid);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * from resume ORDER BY full_name")) {
            ResultSet resultSet = statement.executeQuery();
            List<Resume> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Resume(resultSet.getString("uuid"), resultSet.getString("full_name")));
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT count(*) from resume")) {
            int count = 0;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Resume resume) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE resume SET full_name = ? where uuid = ?")) {

            statement.setString(1, resume.getFullName());
            statement.setString(2, resume.getUuid());
            if( statement.executeUpdate() == 0){
                throw new StorageNotFoundException(resume.getUuid());
            }

        } catch (SQLException e) {
            throw new StorageNotFoundException(resume.getUuid());
        }
    }
}
