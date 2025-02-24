package com.jjkcharles.controllers;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjkcharles.Common.*;

@RestController
public class MyFirstController {

    private final DatabaseConfig databaseConfig;
  
    @Autowired
    public MyFirstController(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }
    
    @RequestMapping(path = "/test")
    public String home() {
        //System.out.println("Hello");

        // Database credentials
        String user = (databaseConfig.getUsername() != null ? databaseConfig.getUsername() : "").trim().length() > 0 ? databaseConfig.getUsername() : "postgres";
        String password = (databaseConfig.getPassword() != null ? databaseConfig.getPassword() : "").trim().length() > 0 ? databaseConfig.getPassword() : "password";

        // JDBC URL
        String url = (databaseConfig.getUrl() != null ? databaseConfig.getUrl() : "").trim().length() > 0 ? databaseConfig.getUrl() : "jdbc:postgresql://localhost/postgres";

        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(url, user, password);

            // Create a statement object
            Statement stmt = conn.createStatement();

            // Query to select a random record from "mytable"
            ResultSet rs = stmt.executeQuery("SELECT name FROM mytable ORDER BY RANDOM() LIMIT 1");

            // Fetch the result
            if (rs.next()) {
                String name = rs.getString(1);
                System.out.println(name);
            }

            // Close the statement, result set, and connection
            stmt.close();
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return "Hello World from Test";
    }
}