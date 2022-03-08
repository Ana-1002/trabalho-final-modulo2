package com.dbc.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository<KEY, OBJECT> {
    Integer getNextId(Connection connection) throws SQLException;

    OBJECT add(OBJECT object) throws SQLException;

    boolean remove(KEY id) throws SQLException;

    boolean update(KEY id, OBJECT object) throws SQLException;

    List<OBJECT> list() throws SQLException;

}
