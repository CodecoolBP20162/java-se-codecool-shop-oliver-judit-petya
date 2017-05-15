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
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {
    private static SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
    private static ProductDao productDataStore = ProductDaoMem.getInstance();
    private static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    private static OrderDao order = OrderDaoMem.getInstance();

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

    public static ModelAndView renderShoppingCart(Request req, Response res) {
        Order cart = OrderController.findCurrentOrder(req);
        List<LineItem> cartItems = cart.getItems();

        Map params = new HashMap<>();
        params.put("cartItems", cartItems);
        params.put("sumTotalPrice", cart.sumTotalPrice());
        params.put("orderQuantity", req.session().attribute("orderQuantity"));
        return new ModelAndView(params, "product/shopping_cart");
    }

    public static ModelAndView renderEditCart(Request req, Response res) {
        Order cart = OrderController.findCurrentOrder(req);
        List<LineItem> cartItems = cart.getItems();
        for(LineItem cartItem : cartItems){
            if (Integer.toString(cartItem.getId()).equals(req.queryParams("cart-id"))){
                cartItem.quantity = Integer.parseInt(req.queryParams("cart-quantity"));
                // cart.addLineItem(cartItem);
                cart.updateOrderPrice(cartItem);
                cart.updateOrderQuantity(cartItem);
            };
        };
        Map params = new HashMap<>();
        params.put("cartItems", cartItems);
        params.put("sumTotalPrice", cart.sumTotalPrice());
        return renderShoppingCart(req, res);
    }

    public static ModelAndView renderDeleteItem(Request req, Response res) {
        String itemID = req.params(":id");
        Order cart = OrderController.findCurrentOrder(req);
        List<LineItem> cartItems = cart.getItems();
        for (LineItem cartItem : cartItems) {
            //System.out.println(cartItem.getId());
            if (itemID.equals(Integer.toString(cartItem.getId()))){
                cart.deleteItem(cartItem);
            }
        }

        Map params = new HashMap<>();
        params.put("cartItems", cartItems);
        params.put("sumTotalPrice", cart.sumTotalPrice());
        params.put("orderQuantity", req.session().attribute("orderQuantity"));
        return new ModelAndView(params, "product/shopping_cart");
    }
}
