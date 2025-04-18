package org.example.Utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBase {

    private static final HikariDataSource HIKARI_DATA_SOURCE;
    private static final String URL = "jdbc:sqlite::resource:my_database.db";

    static {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(URL);
        hikariConfig.setDriverClassName("org.sqlite.JDBC");

        HIKARI_DATA_SOURCE = new HikariDataSource(hikariConfig);
    }

    public static Connection getConnection() throws SQLException {
        return HIKARI_DATA_SOURCE.getConnection();
    }
}
