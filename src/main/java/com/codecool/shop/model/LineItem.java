package com.codecool.shop.model;

public class LineItem {

    public Product product;
    public int quantity;
    public float totalPrice;

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

    public int getQuantity() {
        return quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getName(){
        return this.product.getName();
    }

    public int getId() { return this.product.id; }

    public String getDescription() {
        String description = this.product.description;
        return description;
    }

    public void setTotalPrice() {
        this.totalPrice = quantity * product.getDefaultPrice();
    }
}
