package com.codecool.shop.dao.jdbcImplementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC extends JDBCAbstractClass implements ProductCategoryDao{

    private static ProductCategoryDaoJDBC instance = null;

    private ProductCategoryDaoJDBC(){}

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
            preparedStatement.executeUpdate();

            // Get the ID of the most recent record and update our supplier
            String findProductCategory = "SELECT id FROM ProductCategory ORDER BY id DESC LIMIT 1;";
            preparedStatement = dbConnection.prepareStatement(findProductCategory);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                productCategory.setId(result.getInt("id"));
            }
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
        remove(id, "ProductCategory");
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

    public void removeAll() {

        String removeRecords = "DELETE FROM productcategory;";

        try {
            preparedStatement = dbConnection.prepareStatement(removeRecords);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


