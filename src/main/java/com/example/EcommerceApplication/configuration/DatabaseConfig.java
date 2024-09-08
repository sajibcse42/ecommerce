package com.example.EcommerceApplication.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Configuration
public class DatabaseConfig {
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Bean
    public DataSource dataSource() throws Exception {
        // Extract the base URL for the database connection without the database name
        String baseUrl = databaseUrl.substring(0, databaseUrl.lastIndexOf("/"));

        // Establish a connection to the base MySQL server
        try (Connection connection = java.sql.DriverManager.getConnection(baseUrl, databaseUsername, databasePassword)) {
            // Execute the query to create the database if it does not exist
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS Ecommerce");
        }

        // After creating the database, return the actual data source that connects to the Ecommerce database
        return new org.springframework.jdbc.datasource.DriverManagerDataSource(databaseUrl, databaseUsername, databasePassword);
    }
}
