package com.codecool.shop.dao.jdbcImplementation;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

abstract class JDBCAbstractClass {

    private static ArrayList<String> dbProps = JDBCReadDataFromProps.connectProps();
    String DATABASE;
    String DB_USER;
    String DB_PASSWORD;
    PreparedStatement preparedStatement;
    Connection dbConnection;

    public JDBCAbstractClass() {
        try {
            DATABASE = dbProps.get(0);
            DB_USER = dbProps.get(1);
            DB_PASSWORD = dbProps.get(2);
            dbConnection = getConnection();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public void remove(int id, String table) {
        String removeFromTable = "";
        switch (table) {
            case "Product":
                removeFromTable = "DELETE FROM product WHERE id = ?;";
                break;

            case "ProductCategory":
                removeFromTable = "DELETE FROM productcategory WHERE id = ?;";
                break;

            case "Supplier":
                removeFromTable = "DELETE FROM supplier WHERE id = ?;";
                break;
        }
        try {
            preparedStatement = dbConnection.prepareStatement(removeFromTable);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}



