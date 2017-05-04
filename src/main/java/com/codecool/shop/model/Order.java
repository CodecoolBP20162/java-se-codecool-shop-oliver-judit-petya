package com.codecool.shop.model;


import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<LineItem> items = new ArrayList<>();
    private float orderPrice;
    private int orderQuantity;
    private int id;

    public void addLineItem(LineItem item) {
        int counter = 0;
        for (LineItem n : items) {
            if (n.product.equals(item.product)) {
                n.quantity += 1;
                updateOrderPrice(item);
                updateOrderQuantity(item);
                counter += 1;
            }
        }
        if (counter == 0) {
            items.add(item);
            updateOrderPrice(item);
            updateOrderQuantity(item);
        }
    }

    public void updateOrderPrice(LineItem item) {
        this.orderPrice += item.getTotalPrice();
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

    public List<LineItem> getItems() {
        return items;
    }
      
    public int getId() {
        return id;
    }
}
