package com.codecool.stackoverflowtw.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnector {

    private final String url;
    private final String userName;
    private final String password;
    private final String dbName;

    public SqlConnector() {
        this.userName = "postgres";
        this.password = "333333";
        this.dbName = "stackoverflow_db";
        this.url = "jdbc:postgresql://localhost:5432/" + dbName;
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection to Postgresql has been established.");
            return conn;
        } catch (SQLException ex) {
            System.err.println("Could not create database connection.");
            throw new RuntimeException(ex);
        }
    }


}
