package com.codecool.shop.dao.jdbcImplementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCReadDataFromProps {

    public static ArrayList<String> connectProps() {

        Properties prop = new Properties();
        InputStream input = null;
        ArrayList<String> proplist = new ArrayList<>();

        try {
            //If you want to connect to the test database use this path: test/testResources/test_properties.txt;
            // For deployed database use this path: src/main/sql/properties.txt
            input = new FileInputStream("test/testResources/test_properties.txt");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            proplist.add(prop.getProperty("database"));
            proplist.add(prop.getProperty("username"));
            proplist.add(prop.getProperty("password"));

            return proplist;

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}