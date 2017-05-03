package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import spark.Request;
import spark.Response;

public class CartController {

    public static OrderDaoMem order = OrderDaoMem.getInstance();

    public static Response addToCart(Request req, Response res) {
        String productIdStr = req.queryParams("prod_id");
        int productIdInt = Integer.parseInt(productIdStr);
        Product selectedProduct = ProductDaoMem.getInstance().find(productIdInt);

        LineItem actualItem = new LineItem(selectedProduct);
        order.addLineItem(actualItem);

        req.session().attribute("orderNum", order.getOrderQuantity());
        req.session().attribute("orderPrice", order.getOrderPrice());

        res.body("/");
        return res;
    }
}
