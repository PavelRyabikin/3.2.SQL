package ru.netology.web.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private SQLHelper() {
    }

    private static final QueryRunner runner = new QueryRunner();
    private static final String URL = System.getProperty("db.url", "jdbc:mysql://localhost:3306/app");
    private static final String USER = System.getProperty("db.user", "app");
    private static final String PASSWORD = System.getProperty("db.pass", "pass");

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String getVerificationCode(String login) {
        String codeSQL =
                "SELECT code " +
                        "FROM auth_codes " +
                        "WHERE user_id = ( " +
                        "    SELECT id " +
                        "    FROM users " +
                        "    WHERE login = ? " +
                        ") " +
                        "ORDER BY created DESC " +
                        "LIMIT 1";

        try (var conn = getConnection()) {
            return runner.query(conn, codeSQL, new ScalarHandler<>(), login);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}