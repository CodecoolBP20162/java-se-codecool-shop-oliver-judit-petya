package com.codecool.shop.dao.jdbcImplementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCConnectPropParser {

    public static ArrayList<String> connectProps() {
        Properties prop = new Properties();
        InputStream input = null;
        ArrayList<String> proplist = new ArrayList<>();
        //if you want to connect to live DB use this line 20th: input = new FileInputStream(pathToLiveDB);
        String pathToLiveDB = "src/main/sql/properties.txt";
        //if you want to connect to live DB use this line 20th: input = new FileInputStream(pathToTestDB);
        String pathToTestDB = "test/testResources/test_properties.txt";
        try {
            input = new FileInputStream(pathToTestDB);

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