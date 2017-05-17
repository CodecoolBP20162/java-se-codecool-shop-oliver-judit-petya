package com.codecool.shop.model;

import java.util.ArrayList;

public class Supplier extends BaseModel {
    private ArrayList<Product> products;

    public Supplier(String name) {
        super(name);
        this.products = new ArrayList<>();
    }

    public Supplier(String name, String description) {
        super(name, description);
        this.products = new ArrayList<>();
    }

    public ArrayList getProducts() {
        return this.products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }

    public static boolean equals(Supplier supplierOne, Supplier supplierTwo){
        if (supplierOne.getId() == supplierTwo.getId() &&
                supplierOne.getName() == supplierTwo.getName()
                ){
            return true;
        }
        return false;
    }
}