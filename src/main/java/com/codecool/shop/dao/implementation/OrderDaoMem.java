package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao{

    private List<LineItem> items = new ArrayList<>();
    private float orderPrice;
    private int orderQuantity;
    private static OrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem(){
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    public void addLineItem(LineItem item){
        items.add(item);
        updateOrderPrice(item);
        updateOrderQuantity(item);
    }

    public void updateOrderPrice(LineItem item){
        this.orderPrice += item.totalPrice;
    }

    public void updateOrderQuantity(LineItem item){
        this.orderQuantity += item.quantity;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public List<LineItem> getItems() {
        return items;
    }

}
