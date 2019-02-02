package com.marchesani.clair.fridge.db;

import java.sql.*;
import java.util.Arrays;

public class H2MemoryDatabase {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "clair";
    private static final String DB_PASSWORD = "password";

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

    public static ResultSet query(String statement, Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }
        }
        return preparedStatement.executeQuery();
    }

    public static int update(String statement, Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }
        }
        return preparedStatement.executeUpdate();
    }

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
