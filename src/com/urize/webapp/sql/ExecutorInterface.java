package com.urize.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ExecutorInterface<T> {
    public T execute(PreparedStatement preparedStatement) throws SQLException;
}
