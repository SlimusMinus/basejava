package com.urize.webapp.storage.sql;

import com.urize.webapp.exception.ResumeExistStorageException;
import com.urize.webapp.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLHelper {
    private final ConnectionFactory connectionFactory;

    public SQLHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String SQL) {
        execute(SQL, PreparedStatement::execute);
    }

    public <T> T execute(String SQL, ExecutorInterface<T> executorInterface) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {
            return executorInterface.execute(statement);
        } catch (SQLException e) {
            throw new ResumeExistStorageException(e.getMessage());
        }
    }
}
