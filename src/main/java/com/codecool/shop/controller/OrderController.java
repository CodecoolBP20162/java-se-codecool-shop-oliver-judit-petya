package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import org.json.simple.JSONObject;
import spark.Request;
import spark.Response;

public class OrderController {

    private static OrderDaoMem orderList = OrderDaoMem.getInstance();

    private static void updateSession(Request req, Order currentOrder) {
        req.session().attribute("orderQuantity", currentOrder.getOrderQuantity());
        req.session().attribute("orderPrice", currentOrder.getOrderPrice());
    }

    private static LineItem returnLineItemFromReq(Request req) {
        String productIdStr = req.queryParams("prodId");
        String productQuantityStr = req.queryParams("quantity");
        int productQuantityInt = Integer.parseInt(productQuantityStr);
        int productIdInt = Integer.parseInt(productIdStr);
        return new LineItem(ProductDaoMem.getInstance().find(productIdInt), productQuantityInt);
    }

    private static Order findCurrentOrder(Request req) {
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

    public static JSONObject addToCart(Request req, Response res) {
        LineItem selectedItem = returnLineItemFromReq(req);
        Order currentOrder = findCurrentOrder(req);
        currentOrder.addLineItem(selectedItem);
        updateSession(req, currentOrder);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("numOfLineItems", currentOrder.getOrderQuantity());
        res.type("application/json");
        return jsonObj;
    }
}
