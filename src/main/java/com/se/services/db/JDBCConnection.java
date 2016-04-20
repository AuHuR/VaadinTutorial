package com.se.services.db;

import com.se.services.util.DBLoginData;

import java.sql.*;
import java.util.*;

/**
 * Created by Moritz on 20.04.16.
 */
public class JDBCConnection {

    private static JDBCConnection connection = null;
    //Changed URL for testing purpose to mariaDB
    private String url = DBLoginData.URL;
    private String user = DBLoginData.USER;
    private String pword = DBLoginData.PASSWORD;

    private Connection conn;

    public static JDBCConnection getInstance() {

        if (connection == null) {
            connection = new JDBCConnection();
        }
        return connection;
    }

    private JDBCConnection() {
        this.initConnection();
    }

    public void initConnection() {
        try {
            //Driver Register changed from PostgeSQL to MariaDB for testing purpose
            //DriverManager.registerDriver(new org.postgresql.Driver());
            DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.openConnection();
    }

    public void openConnection() {
        try {
            Properties properties = new Properties();

            properties.setProperty("user", this.user);
            properties.setProperty("password", this.pword);

            this.conn = DriverManager.getConnection(this.url, properties);
            //System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement() {

        try {
            if (this.conn.isClosed()) {
                this.openConnection();
            }
            return this.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnection() throws SQLException {
        this.conn.close();
    }
}
