package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {

    private static ProductCategoryDaoJDBC instance = null;

    private String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private String DB_USER = "petya";
    private String DB_PASSWORD = "petya";

    private PreparedStatement preparedStatement;
    private Connection dbConnection;

    private ProductCategoryDaoJDBC(){
        try {
            dbConnection = getConnection();
        } catch (SQLException e){
            e.getStackTrace();
        }
    }

    public static ProductCategoryDaoJDBC getInstance(){
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    public void add(ProductCategory productCategory){

        String insertIntoTable = "INSERT INTO productcategory (name, description, department) VALUES (?,?,?);";
        try {
            preparedStatement = dbConnection.prepareStatement(insertIntoTable);
            preparedStatement.setString(1, productCategory.getName());
            preparedStatement.setString(2, productCategory.getDescription());
            preparedStatement.setString(3, productCategory.getDepartment());

            preparedStatement.executeQuery();
        } catch (SQLException e){
            e.getStackTrace();
        }
    }

    public ProductCategory find(int id){

        String query = "SELECT * FROM ProductCategory WHERE id = ?;";
        try {
            preparedStatement = dbConnection.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            preparedStatement.setInt(1,id);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()) {
                ProductCategory productCategory = new ProductCategory(
                result.getString    ("name"),
                result.getString    ("description"),
                result.getString("department"));
                productCategory.setId(result.getInt("id"));
                return productCategory;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void remove(int id){

        String removeFromTable = "DELETE FROM ProductCategory WHERE id = ?;";
        try {
            preparedStatement = dbConnection.prepareStatement(removeFromTable);
            preparedStatement.setInt(1,id);
            preparedStatement.executeQuery();
        } catch (Exception e){
            e.getStackTrace();
        }

    }

    public List<ProductCategory> getAll(){

        String query = "SELECT * FROM ProductCategory";
        List<ProductCategory> productCategoryList = new ArrayList<>();
        try {
            preparedStatement = dbConnection.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()) {
                ProductCategory productCategory = new ProductCategory(
                        result.getString    ("name"),
                        result.getString    ("description"),
                        result.getString("department"));
                productCategory.setId(result.getInt("id"));
                productCategoryList.add(productCategory);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return productCategoryList;
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }
}


