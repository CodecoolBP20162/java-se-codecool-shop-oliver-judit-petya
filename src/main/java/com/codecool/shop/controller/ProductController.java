package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.*;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        req.session(true);

        Map params = new HashMap<>();
        params.put("orderQuantity", req.session().attribute("orderQuantity"));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsbyCategory(Request req, Response res, int categoryID) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        OrderDao order = OrderDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("orderQuantity", req.session().attribute("orderQuantity"));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(categoryID)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsbySupplier(Request req, Response res, int supplierID) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        OrderDao order = OrderDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("orderQuantity", req.session().attribute("orderQuantity"));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getBy(productSupplierDataStore.find(supplierID)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderShoppingCart(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        OrderDao order = OrderDaoMem.getInstance();
        Order cart = order.find(0);
        List<LineItem> cartItems = cart.getAll();

        Map params = new HashMap<>();
        params.put("cartItems", cartItems);
        return new ModelAndView(params, "product/shopping_cart");
    }

    public static ModelAndView renderEditCart(Request req, Response res) {
        OrderDao order = OrderDaoMem.getInstance();
        Order cart = order.find(0);
        List<LineItem> cartItems = cart.getAll();

        Map params = new HashMap<>();
        params.put("cartItems", cartItems);
        return new ModelAndView(params, "product/shopping_cart");
    }
}
