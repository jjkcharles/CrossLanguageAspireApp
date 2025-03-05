package com.jjkcharles.controllers;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jjkcharles.Common.*;

@RestController
public class MyFirstController {

    private final DatabaseConfig databaseConfig;

    private final WeatherConfig weatherConfig;
  
    @Autowired
    public MyFirstController(DatabaseConfig databaseConfig, WeatherConfig weatherConfig) {
        this.databaseConfig = databaseConfig;
        this.weatherConfig = weatherConfig;
    }
    
    @RequestMapping(path = "/test")
    public String home() {
        //System.out.println("Hello");

        return "Hello World from Test";
    }

    @RequestMapping(path = "/name")
    public String name() {

        String name = "";
        String weather = "";

        try {
            String url = this.weatherConfig.getUrl() + "/weatherforecast";
            String fieldName = "summary";

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            weather = jsonObject.getString(fieldName);

            name = getName();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "error";
        }

        return name + "; Today's weather is: " + weather;
    }

    private String getName() {
        // Database credentials
        String user = (databaseConfig.getUsername() != null ? databaseConfig.getUsername() : "").trim().length() > 0 ? databaseConfig.getUsername() : "postgres";
        String password = (databaseConfig.getPassword() != null ? databaseConfig.getPassword() : "").trim().length() > 0 ? databaseConfig.getPassword() : "password";

        // JDBC URL
        String url = (databaseConfig.getUrl() != null ? databaseConfig.getUrl() : "").trim().length() > 0 ? databaseConfig.getUrl() : "jdbc:postgresql://localhost/postgres";

        String name = "";
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
                name = rs.getString(1);
                System.out.println(name);
            }

            // Close the statement, result set, and connection
            stmt.close();
            rs.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return "error";
        }

        return name;
    }
}