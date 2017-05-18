package com.codecool.shop.dao;

import com.codecool.shop.dao.jdbcImplementation.ProductDaoJDBC;
import com.codecool.shop.dao.jdbcImplementation.SupplierDaoJDBC;
import com.codecool.shop.dao.memImplementation.ProductDaoMem;
import com.codecool.shop.dao.memImplementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDaoTest {

    static ProductDao productDataStoreMem = ProductDaoMem.getInstance();
    static ProductDao productDataStoreJdbc = ProductDaoJDBC.getInstance();
    static Supplier amazon;
    static ProductCategory tablet;

    static Stream<ProductDao> daoProvider() {
        return Stream.of(productDataStoreJdbc, productDataStoreMem);
    }

    @BeforeAll
    public static void setupAll() {
        amazon = new Supplier("Amazon", "Digital content and services");
        tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        amazon.setId(1);
        tablet.setId(1);
    }

    @BeforeEach
    public void setupTests(){
        daoProvider().forEach(ProductDao::removeAll);
    }

    private List<Product> prepareProductDataStoreWithOneProduct(ProductDao dao) {
        Product fire = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        List<Product> products = new ArrayList<>();
        products.add(fire);
        dao.add(fire);
        return products;
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testAdd_AddNewProduct_ProductAddedToMem(ProductDao dao) {
        Product fire = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        dao.add(fire);
        assertEquals(fire, dao.getAll().get(dao.getAll().size() - 1));
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testFind_FindSupplier_ReturnProductWithId(ProductDao dao) {
        Product fire = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        dao.add(fire);
        assertEquals(fire, dao.find(fire.getId()));
 }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testRemove_RemoveProduct_ProductRemovedFromMem(ProductDao dao){
        Product fire = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        dao.add(fire);
        dao.remove(fire.getId());
        assertEquals(0, dao.getAll().size());
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testFind_FindProductIfNoProduct_ReturnNull(ProductDao dao){
        assertEquals(null, dao.find(1));
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testRemove_RemoveProductIfThereIsNone_ProgramContinuesRunning(ProductDao dao){
        dao.remove(1);
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testGetAll_GetAllIfNothingInList_ReturnEmptyList(ProductDao dao){
        List<Product> products = new ArrayList<>();
        assertEquals(products, dao.getAll());
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testGetAll_GetAllIfProductsInList_ReturnProductsList(ProductDao dao){
        Product fire = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        List<Product> products = new ArrayList<>();
        products.add(fire);
        dao.add(fire);
        assertEquals(products, dao.getAll());
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testGetAllBy_Suppliers_GetAllIfNothingInList_ReturnEmptyList(ProductDao dao){
        List<Product> products = new ArrayList<>();
        assertEquals(products, dao.getBy(amazon));
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testGetAllBy_Suppliers_GetAllIfProductsInList_ReturnProductsList(ProductDao dao){
        List<Product> products = prepareProductDataStoreWithOneProduct(dao);
        assertEquals(products, dao.getBy(amazon));
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testGetAllBy_ProductCategory_GetAllIfNothingInList_ReturnEmptyList(ProductDao dao){
        List<Product> products = new ArrayList<>();
        assertEquals(products, dao.getBy(tablet));
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testGetAllBy_ProductCategory_GetAllIfProductsInList_ReturnProductsList(ProductDao dao){
        List<Product> products = prepareProductDataStoreWithOneProduct(dao);
        assertEquals(products, dao.getBy(tablet));
    }
}