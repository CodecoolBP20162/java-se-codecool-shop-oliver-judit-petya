package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import spark.Request;
import spark.Response;

public class CartController {

    public static OrderDaoMem orderList = OrderDaoMem.getInstance();

    public static Response addToCart(Request req, Response res) {

        String productIdStr = req.queryParams("prod_id");
        int productIdInt = Integer.parseInt(productIdStr);
        Product selectedProduct = ProductDaoMem.getInstance().find(productIdInt);

        LineItem selectedItem = new LineItem(selectedProduct);
        Order currentOrder = new Order();

        if (!req.session().attributes().contains("orderId")) {
            orderList.add(currentOrder);
            req.session().attribute("orderId", currentOrder.getId());
        } else {
            int orderId = req.session().attribute("orderId");
            currentOrder = orderList.find(orderId);
        }

        currentOrder.addLineItem(selectedItem);

        req.session().attribute("orderQuantity", currentOrder.getOrderQuantity());
        req.session().attribute("orderPrice", currentOrder.getOrderPrice());

        res.redirect("/");
        return res;
    }
}
