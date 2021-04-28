package com.codecool.shop.dao.implementation.jdbc;

import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public abstract class DatabaseConnection {
    private static String DATABASE;
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static String initFilePath = "src/main/webapp/connection.properties";

    public static Connection getConnection() throws SQLException {
        connectionAuthentication();
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private static void connectionAuthentication() {
        try (InputStream input = new FileInputStream(initFilePath)) {

            Properties prop = new Properties();

            //load a properties file from class path, inside static method
            prop.load(input);

            DATABASE = "jdbc:postgresql://" + prop.getProperty("db.url") + "/" + prop.getProperty("db.database");
            DB_USER = prop.getProperty("db.user");
            DB_PASSWORD = prop.getProperty("db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void executeQuery (String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ){
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            getConnection();
        } catch (SQLException throwables) {
            System.err.println("Could not connect to the database.");
        }
    }
}
