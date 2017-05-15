package com.codecool.shop.dao.implementation;
import java.sql.*;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.*;

public class ProductDaoJDBC implements ProductDao{

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "petya";
    private static final String DB_PASSWORD = "petya";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void add(Product product){
        String query = "INSERT INTO Product (" +
                "name," +
                " description, " +
                "default_price, " +
                "currency, " +
                "product_category_id," +
                "supplier_id) " +
                "VALUES ('" + product.getName() +
                "', '" + product.getDescription() +
                "', '" + product.getDefaultPrice() +
                "', '" + product.getDefaultCurrency() +
                "', '" + product.getProductCategory() +
                "', '" + product.getSupplier() + "');";

        executeQuery(query);
    }

    public Product find(int id){
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "Moblie phones. Your mother can ask you what you eaten for lunch through these devices.");
        Supplier lenovo = new Supplier("Lenovo", "Computers");

        return new Product("Super Telephone 3000", 90, "USD", "The best telephone on the planet.", phone, lenovo);
        }
        /*
        String query = "SELECT * FROM product WHERE id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                Product result = new Product(resultSet.getString("name"),
                        resultSet.getString("id"),
                        result.setSupplier(resultSet.getString("supplier_id")),

                return result;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    };*/

     public void remove(int id){
        assert true;
    }

    public List<Product> getAll(){
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "Moblie phones. Your mother can ask you what you eaten for lunch through these devices.");
        Product dummy = new Product("Super Telephone 3000", 90, "USD", "The best telephone on the planet.", phone, lenovo);
        List<Product> dummyList = new ArrayList<>();
        dummyList.add(dummy);
        return dummyList;
        }

    public List<Product> getBy(Supplier supplier){
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "Moblie phones. Your mother can ask you what you eaten for lunch through these devices.");
        Product dummy = new Product("Super Telephone 3000", 90, "USD", "The best telephone on the planet.", phone, lenovo);
        List<Product> dummyList = new ArrayList<>();
        dummyList.add(dummy);
        return dummyList;
    };

    public List<Product> getBy(ProductCategory productCategory){
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "Moblie phones. Your mother can ask you what you eaten for lunch through these devices.");
        Product dummy = new Product("Super Telephone 3000", 90, "USD", "The best telephone on the planet.", phone, lenovo);
        List<Product> dummyList = new ArrayList<>();
        dummyList.add(dummy);
        return dummyList;
    }

}
