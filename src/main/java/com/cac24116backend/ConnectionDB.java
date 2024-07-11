package com.cac24116backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDB {

    public String driver = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies_cac", "root", "root");
        } catch (SQLException e) {
            System.out.println("Hay un error" + e);
        }
        return connection;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        ConnectionDB con = new ConnectionDB();
        connection = con.getConnection();

        PreparedStatement ps;
        ResultSet rs;

        ps = connection.prepareStatement("select * from movies");
        rs = ps.executeQuery();

        while(rs.next()) {
            String title = rs.getString("title");
            System.out.println(title);
        }

    }

}

