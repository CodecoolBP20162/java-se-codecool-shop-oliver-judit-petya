package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao{

    private static SupplierDaoJDBC instance = null;

    private String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private String DB_USER = "postgres";
    private String DB_PASSWORD = "postgres";

    private PreparedStatement preparedStatement;
    private Connection dbConnection;

    private SupplierDaoJDBC(){
        try {
            dbConnection = getConnection();
        } catch (SQLException e){
            e.getStackTrace();
        }
    }

    public static SupplierDaoJDBC getInstance(){
        if (instance == null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    public void add(Supplier supplier){

        String insertIntoTable = "INSERT INTO Supplier (name, description) VALUES (?,?);";
        try {
            preparedStatement = dbConnection.prepareStatement(insertIntoTable);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getDescription());
            preparedStatement.executeQuery();
        } catch (SQLException e){
            e.getStackTrace();
        }
    }

    public Supplier find(int id){

        String query = "SELECT * FROM Supplier WHERE id = ?;";
        try {
            preparedStatement = dbConnection.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            preparedStatement.setInt(1,id);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()) {
                Supplier supplier = new Supplier(
                        result.getString    ("name"),
                        result.getString    ("description"));
                supplier.setId(result.getInt("id"));
                return supplier;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void remove(int id){
        String removeFromTable = "DELETE FROM supplier WHERE id = ?;";
        try {
            preparedStatement = dbConnection.prepareStatement(removeFromTable);
            preparedStatement.setInt(1,id);
            preparedStatement.executeQuery();
        } catch (Exception e){
            e.getStackTrace();
        }
    }

    public List<Supplier> getAll(){
        String query = "SELECT * FROM supplier";
        List<Supplier> supplierList = new ArrayList<>();
        try {
            preparedStatement = dbConnection.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()) {
                Supplier supplier = new Supplier(
                        result.getString    ("name"),
                        result.getString    ("description"));
                supplier.setId(result.getInt("id"));
                supplierList.add(supplier);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return supplierList;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }
}
