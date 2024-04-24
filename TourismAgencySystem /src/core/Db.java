package core;

import java.sql.*;

import java.sql.SQLException;

public class Db {
    private static Db instance = null;
    private Connection connection = null;
    private final String DB_URL = "jdbc:postgresql://localhost:5433/tourismagency";
    private final String DB_USERNAME = "postgres";
    private final String DB_PASS = "1998";

    private Db(){
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASS);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Connection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Db();
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }
}