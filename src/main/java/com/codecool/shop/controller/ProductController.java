package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {
    private static SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
    private static ProductDao productDataStore = ProductDaoMem.getInstance();
    private static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    private static OrderDao order = OrderDaoMem.getInstance();

    public static ModelAndView renderProducts(Request req, Response res) {
        req.session(true);

        Map indexRenderParams = paramFiller(req, productCategoryDataStore, productSupplierDataStore);
        indexRenderParams.put("products", productDataStore.getAll());
        return new ModelAndView(indexRenderParams, "product/index");
    }

    public static ModelAndView renderProductsbyCategory(Request req, Response res, int categoryID) {
        Map categoryRenderParams = paramFiller(req, productCategoryDataStore, productSupplierDataStore);
        categoryRenderParams.put("products", productDataStore.getBy(productCategoryDataStore.find(categoryID)));
        return new ModelAndView(categoryRenderParams, "product/index");
    }

    public static ModelAndView renderProductsbySupplier(Request req, Response res, int supplierID) {
        Map supRenderParams = paramFiller(req, productCategoryDataStore, productSupplierDataStore);
        supRenderParams.put("products", productDataStore.getBy(productSupplierDataStore.find(supplierID)));
        return new ModelAndView(supRenderParams, "product/index");
    }

    public static Map paramFiller(Request req, ProductCategoryDao productCategoryDataStore, SupplierDao productSupplierDataStore) {
        Map params = new HashMap<>();
        params.put("orderQuantity", req.session().attribute("orderQuantity"));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        return params;
    }
}
