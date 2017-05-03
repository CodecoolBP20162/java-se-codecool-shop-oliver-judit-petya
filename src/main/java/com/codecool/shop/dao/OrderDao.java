package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;

public interface OrderDao {
    void addLineItem(LineItem item);
    void updateOrderPrice(LineItem item);
    void updateOrderQuantity(LineItem item);

    float getOrderPrice();
    int getOrderQuantity();
}
