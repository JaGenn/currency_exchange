package org.example;

import org.example.exception.DataBaseOperationErrorException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    private static final String URL = "jdbc:sqlite:my_database.db";

    public static Connection getConnection() {

        try {
            Connection connection = DriverManager.getConnection(URL);

            return connection;
        } catch (SQLException e) {
            throw new DataBaseOperationErrorException("Could not connect to DB " + e.getMessage());
        }
    }
}
