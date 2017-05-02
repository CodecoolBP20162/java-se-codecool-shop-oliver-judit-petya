package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsbyCategory(Request req, Response res, int categoryID) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(categoryID)));
        return new ModelAndView(params, "product/index");
    }


    public static ModelAndView renderProductsbySupplier(Request req, Response res, int supplierID) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("products", productDataStore.getBy(productSupplierDataStore.find(supplierID)));
        return new ModelAndView(params, "product/index");
    }

}
