package com.example.ep_p4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final String propertiesFile = "properties.txt";

    private static Properties loadProperties() throws IOException {
        String filePath = Thread.currentThread().getContextClassLoader().getResource(propertiesFile).getPath();
        Properties properties = new Properties();
        properties.load(new FileInputStream(filePath));
        return properties;
    }

    public static String getDatabaseUrl() throws IOException {
        Properties properties = loadProperties();
        return properties.getProperty("database_url");
    }

    public static String getDatabaseUsername() throws IOException {
        Properties properties = loadProperties();
        return properties.getProperty("database_username");
    }

    public static String getDatabasePassword() throws IOException {
        Properties properties = loadProperties();
        return properties.getProperty("database_password");
    }

    public static String getMySQLDriver() throws IOException {
        Properties properties = loadProperties();
        return properties.getProperty("mysql_driver");
    }



}
