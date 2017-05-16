package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {


    private static ProductDaoJDBC instance = null;

    private String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private String DB_USER = "postgres";
    private String DB_PASSWORD = "postgres";
    private SupplierDaoJDBC supplierDaoJdbc = SupplierDaoJDBC.getInstance();
    private ProductCategoryDaoJDBC productCategoryDaoJdbc = ProductCategoryDaoJDBC.getInstance();

    private PreparedStatement preparedStatement;
    private Connection dbConnection;

    private ProductDaoJDBC() {
        try {
            dbConnection = getConnection();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public static ProductDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }

    public void add(Product product) {

        String insertIntoTable = "INSERT INTO product (name, description, currency, default_price, supplier_id, product_category_id) VALUES (?,?,?,?,?,?);";
        try {
            preparedStatement = dbConnection.prepareStatement(insertIntoTable);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getDefaultCurrency().toString());
            preparedStatement.setFloat(4, product.getDefaultPrice());
            preparedStatement.setInt(5, product.getSupplier().getId());
            preparedStatement.setInt(6, product.getProductCategory().getId());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public Product find(int id) {
        String query = "SELECT * FROM Product WHERE id = ?;";
        try {
            preparedStatement = dbConnection.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                Product product = new Product(
                        result.getString("name"),
                        result.getFloat("default_price"),
                        result.getString("currency"),
                        result.getString("description"),
                        productCategoryDaoJdbc.find(result.getInt("supplier_id")),
                        supplierDaoJdbc.find(result.getInt("product_category_id"))
                );
                product.setId(result.getInt("id"));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(int id) {
        String removeFromTable = "DELETE FROM Product WHERE id = ?;";
        try {
            preparedStatement = dbConnection.prepareStatement(removeFromTable);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public List<Product> getAll() {
        String query = "SELECT * FROM Product;";
        List<Product> productList = new ArrayList<>();

        try {
            preparedStatement = dbConnection.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Product product = new Product(
                        result.getString("name"),
                        result.getFloat("default_price"),
                        result.getString("currency"),
                        result.getString("description"),
                        productCategoryDaoJdbc.find(result.getInt("product_category_id")),
                        supplierDaoJdbc.find(result.getInt("supplier_id"))
                );
                product.setId(result.getInt("id"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM Product WHERE supplier_id = ?;";
        List<Product> productList = new ArrayList<>();

        try {
            preparedStatement = dbConnection.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            preparedStatement.setInt(1, supplier.getId());
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Product product = new Product(
                        result.getString("name"),
                        result.getFloat("default_price"),
                        result.getString("currency"),
                        result.getString("description"),
                        productCategoryDaoJdbc.find(result.getInt("product_category_id")),
                        supplierDaoJdbc.find(result.getInt("supplier_id"))
                );
                product.setId(result.getInt("id"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM Product WHERE product_category_id = ?;";
        List<Product> productList = new ArrayList<>();

        try {
            preparedStatement = dbConnection.prepareStatement(query,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
            preparedStatement.setInt(1, productCategory.getId());
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Product product = new Product(
                        result.getString("name"),
                        result.getFloat("default_price"),
                        result.getString("currency"),
                        result.getString("description"),
                        productCategoryDaoJdbc.find(result.getInt("product_category_id")),
                        supplierDaoJdbc.find(result.getInt("supplier_id"))
                );
                product.setId(result.getInt("id"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }
}


