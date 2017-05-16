package com.codecool.shop.dao.jdbcImplementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class JDBCAbstractClass {

    String DATABASE;
    String DB_USER;
    String DB_PASSWORD;
    PreparedStatement preparedStatement;
    Connection dbConnection;

    public JDBCAbstractClass(){
        try {
            DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
            DB_USER = "postgres";
            DB_PASSWORD = "postgres";
            dbConnection = getConnection();
        } catch (SQLException e){
            e.getStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public void remove(int id, String table){

        String removeFromTable = "DELETE FROM ? WHERE id = ?;";
        try {
            preparedStatement = dbConnection.prepareStatement(removeFromTable);
            preparedStatement.setString(1, table);
            preparedStatement.setInt(2, id);
            preparedStatement.executeQuery();
        } catch (Exception e){
            e.getStackTrace();
        }

    }


}
