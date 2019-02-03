package com.marchesani.clair.fridge.db;

import java.sql.*;
import java.util.Arrays;

/**
 * Class that creates an H2 in memory database upon initialisation
 */
public class H2MemoryDatabase {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "clair";
    private static final String DB_PASSWORD = "password";

    /**
     * Creates a new H2 in memory database and runs initialisation queries if provided
     * @param initialisationSql initialisation queries
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public H2MemoryDatabase(String... initialisationSql) throws ClassNotFoundException, SQLException {
        Connection connection = getDBConnection();
        if (initialisationSql != null) {
            for (String sql : initialisationSql) {
                initialiseDB(connection, sql);
            }
        }
        connection.commit();
        connection.close();
    }

    /**
     * Runs an initialisation statement on the DB
     * @param connection
     * @param initialisationSql
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void initialiseDB(Connection connection, String initialisationSql) throws SQLException, ClassNotFoundException {
        Statement initStatement = connection.createStatement();
        initStatement.execute(initialisationSql);
        initStatement.close();
    }

    private static Connection getDBConnection() throws SQLException, ClassNotFoundException {
        Connection dbConnection = null;
        Class.forName(DB_DRIVER);
        dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        dbConnection.setAutoCommit(false);
        return dbConnection;
    }



}
