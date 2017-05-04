package com.codecool.shop.model;


import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<LineItem> items = new ArrayList<>();
    private float orderPrice;
    private int orderQuantity;
    private int id;

    public void addLineItem(LineItem item) {
        items.add(item);
        updateOrderPrice(item);
        updateOrderQuantity(item);
    }

    public void updateOrderPrice(LineItem item) {
        this.orderPrice += item.totalPrice;
    }

    public void updateOrderQuantity(LineItem item) {
        this.orderQuantity += item.quantity;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
