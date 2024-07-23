package com.cristi.oms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/oms";
    private static final String USER = "postgres";
    private static final String PASS = "cmunti2024";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
