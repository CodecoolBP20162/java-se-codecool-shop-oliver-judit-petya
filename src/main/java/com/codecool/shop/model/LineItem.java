package com.codecool.shop.model;


public class LineItem {
    protected int quantity;
    private float totalPrice;
    public Product product;

    public LineItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.totalPrice = product.getDefaultPrice();
    }
    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = quantity * product.getDefaultPrice();
    }

    protected float getTotalPrice() {
        return this.totalPrice;
    }
}
