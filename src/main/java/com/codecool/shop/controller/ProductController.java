package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.jdbcImplementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.jdbcImplementation.ProductDaoJDBC;
import com.codecool.shop.dao.jdbcImplementation.SupplierDaoJDBC;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {
    private static SupplierDao productSupplierDataStore = SupplierDaoJDBC.getInstance();
    private static ProductDao productDataStore = ProductDaoJDBC.getInstance();
    private static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();

    public static ModelAndView renderProducts(Request req, Response res) {
        req.session(true);

        Map indexRenderParams = paramFiller(req);
        indexRenderParams.put("products", productDataStore.getAll());
        return new ModelAndView(indexRenderParams, "product/index");
    }

    public static ModelAndView renderProductsbyCategory(Request req, Response res, int categoryID) {
        Map categoryRenderParams = paramFiller(req);
        categoryRenderParams.put("products", productDataStore.getBy(productCategoryDataStore.find(categoryID)));
        return new ModelAndView(categoryRenderParams, "product/index");
    }

    public static ModelAndView renderProductsbySupplier(Request req, Response res, int supplierID) {
        Map supRenderParams = paramFiller(req);
        supRenderParams.put("products", productDataStore.getBy(productSupplierDataStore.find(supplierID)));
        return new ModelAndView(supRenderParams, "product/index");
    }

    public static Map paramFiller(Request req) {
        Map params = new HashMap<>();
        params.put("orderQuantity", req.session().attribute("orderQuantity"));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        return params;
    }
}
