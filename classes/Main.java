package classes;

import java.sql.*;

public class Main {
    // JDBC URL for SQLite (a library)
    static final String JDBC_URL = "jdbc:sqlite:test.db";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Establish connection to the SQLite database
            connection = DriverManager.getConnection(JDBC_URL);

            // Create a table


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}

