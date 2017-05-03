package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import spark.Request;
import spark.Response;

public class CartController {

    public static OrderDaoMem orderList = OrderDaoMem.getInstance();

    private static void updateSession(Request req, Order currentOrder){
        req.session().attribute("orderQuantity", currentOrder.getOrderQuantity());
        req.session().attribute("orderPrice", currentOrder.getOrderPrice());
    }

    private static LineItem returnLineItemFromReq(Request req){
        String productIdStr = req.queryParams("prod_id");
        int productIdInt = Integer.parseInt(productIdStr);
        return new LineItem(ProductDaoMem.getInstance().find(productIdInt));
    }

    private static Order findCurrentOrder(Request req){
        Order currentOrder = new Order();
        if (!req.session().attributes().contains("orderId")) {
            orderList.add(currentOrder);
            req.session().attribute("orderId", currentOrder.getId());
        } else {
            int orderId = req.session().attribute("orderId");
            currentOrder = orderList.find(orderId);
        }
        return currentOrder;
    }

    public static Response addToCart(Request req, Response res) {
        LineItem selectedItem = returnLineItemFromReq(req);
        Order currentOrder = findCurrentOrder(req);
        currentOrder.addLineItem(selectedItem);
        updateSession(req, currentOrder);
        res.redirect("/");
        return res;
    }
}
