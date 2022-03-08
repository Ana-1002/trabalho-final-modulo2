package com.dbc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String SERVER = "localhost";
    private static final String PORT = "1521";
    private static final String DATABASE = "xe";
    private static final String USER = "system";
    private static final String PASS = "oracle";

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@" + SERVER + ":" + PORT + ":" + DATABASE;

        Connection con = DriverManager.getConnection(url, USER, PASS);

        con.createStatement().execute("alter session set current_schema=VEM_SER");

        return con;
    }
}
