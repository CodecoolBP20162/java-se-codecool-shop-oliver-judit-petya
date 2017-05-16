package com.codecool.shop.dao;

import com.codecool.shop.dao.memImplementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDaoTest {

    private static ProductDao productDataStore = ProductDaoMem.getInstance();
    private static Supplier amazon;
    private static ProductCategory tablet;


    private List<Product> prepareProductDataStoreWithOneProduct() {
        Product fire = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        List<Product> products = new ArrayList<>();
        products.add(fire);
        productDataStore.add(fire);
        return products;
    }

    @BeforeAll
    public static void setupAll() {
        amazon = new Supplier("Amazon", "Digital content and services");
        tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
    }

    @BeforeEach
    public void setup() {

        List<Integer> ids = new ArrayList<>();
        for (Product product : productDataStore.getAll()) {
            ids.add(product.getId());
        }
        for (Integer id : ids) {
            productDataStore.remove(id);
        }
    }

    @Test
    public void testAdd_AddNewProduct_ProductAddedToMem() {
        Product fire = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        productDataStore.add(fire);
        assertEquals(fire, productDataStore.getAll().get(0));
    }

    @Test
    public void testFind_FindSupplier_ReturnProductWithId() {
        Product fire = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        productDataStore.add(fire);
        assertEquals(fire, productDataStore.find(1));
 }

    @Test
    public void testRemove_RemoveProduct_ProductRemovedFromMem(){
        Product fire = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        productDataStore.add(fire);
        productDataStore.remove(1);
        assertEquals(0, productDataStore.getAll().size());
    }

    @Test
    public void testFind_FindProductIfNoProduct_ReturnNull(){
        assertEquals(null, productDataStore.find(1));
    }

    @Test
    public void testRemove_RemoveProductIfThereIsNone_ProgramContinuesRunning(){
        productDataStore.remove(1);
    }

    @Test
    public void testGetAll_GetAllIfNothingInList_ReturnEmptyList(){
        List<Product> products = new ArrayList<>();
        assertEquals(products, productDataStore.getAll());
    }

    @Test
    public void testGetAll_GetAllIfProductsInList_ReturnProductsList(){
        Product fire = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        List<Product> products = new ArrayList<>();
        products.add(fire);
        productDataStore.add(fire);
        assertEquals(products, productDataStore.getAll());
    }

    @Test
    public void testGetAllBy_Suppliers_GetAllIfNothingInList_ReturnEmptyList(){
        List<Product> products = new ArrayList<>();
        assertEquals(products, productDataStore.getBy(amazon));
    }

    @Test
    public void testGetAllBy_Suppliers_GetAllIfProductsInList_ReturnProductsList(){
        List<Product> products = prepareProductDataStoreWithOneProduct();
        assertEquals(products, productDataStore.getBy(amazon));
    }

    @Test
    public void testGetAllBy_ProductCategory_GetAllIfNothingInList_ReturnEmptyList(){
        List<Product> products = new ArrayList<>();
        assertEquals(products, productDataStore.getBy(tablet));
    }

    @Test
    public void testGetAllBy_ProductCategory_GetAllIfProductsInList_ReturnProductsList(){
        List<Product> products = prepareProductDataStoreWithOneProduct();
        assertEquals(products, productDataStore.getBy(tablet));
    }


    }