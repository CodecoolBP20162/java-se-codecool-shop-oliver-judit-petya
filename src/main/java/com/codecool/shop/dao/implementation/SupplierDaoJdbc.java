package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao{

    private static SupplierDaoJdbc instance = null;

    private String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private String DB_USER = "postgres";
    private String DB_PASSWORD = "postgres";

    private PreparedStatement preparedStatement;
    private Connection dbConnection;

    private SupplierDaoJdbc(){
        try {
            dbConnection = getConnection();
        } catch (SQLException e){
            e.getStackTrace();
        }
    }

    public static SupplierDaoJdbc getInstance(){
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    public void add(Supplier supplier){

        String insertIntoTable = "INSERT INTO supplier (name, description) VALUES (?,?);";
        try {
            preparedStatement = dbConnection.prepareStatement(insertIntoTable);

            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getDescription());

            preparedStatement.executeUpdate();
        } catch (Exception e){
            e.getStackTrace();
        }
    }

    public Supplier find(int id){

        String query = "SELECT * FROM supplier WHERE id = ?;";
        try {
            preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
//            System.out.println(rs.getObject("name"));
        } catch (SQLException e){
            e.printStackTrace();
        }
        Supplier s = new Supplier("kk", "kk");
        return s;
    }

    public void remove(int id){

    }

    public List<Supplier> getAll(){
        List<Supplier> s = new ArrayList<>();
        return s;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }
}
