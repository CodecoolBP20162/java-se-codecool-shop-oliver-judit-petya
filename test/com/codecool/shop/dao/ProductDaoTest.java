package com.codecool.shop.dao;

import com.codecool.shop.dao.memImplementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.memImplementation.ProductDaoMem;
import com.codecool.shop.dao.memImplementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codecool.shop.dao.SupplierDaoTest.supplierDataStore;
import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    static ProductDao productDataStore = ProductDaoMem.getInstance();
    static Supplier amazon;
    static Supplier lenovo;
    static ProductCategory tablet;
    static ProductCategory phone;
    static ProductCategory notebook;

    @BeforeAll
    public void setupAll(){
        amazon = new Supplier("Amazon", "Digital content and services");
        lenovo = new Supplier("Lenovo", "Computers");

        tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        phone = new ProductCategory("Phone", "Hardware", "Moblie phones. Your mother can ask you what you eaten for lunch through these devices.");
        notebook = new ProductCategory("Notebook", "Hardware", "Like a tablet but with keyboard");
    }

    @BeforeEach
    public void setup(){

        List<Integer> ids = new ArrayList<>();
        for (Product product: productDataStore.getAll()) {
            ids.add(product.getId());
        }
        for (Integer id: ids){
            productDataStore.remove(id);
        }
    }

    @Test
    public void testAdd_AddNewProduct_ProductAddedToMem(){
        Product fire = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        productDataStore.add(fire);
        assertEquals(fire, productDataStore.getAll().get(0));
    }
//
//    @Test
//    public void testFind_FindSupplier_ReturnSupplierWithId(){
//        Supplier amazon = new Supplier("Amazon", "Digital content and services");
//        supplierDataStore.add(amazon);
//        assertEquals(amazon, supplierDataStore.find(1));
//    }
//
//    @Test
//    public void testRemove_RemoveSupplier_SupplierRemovedFromMem(){
//        Supplier amazon = new Supplier("Amazon", "Digital content and services");
//        supplierDataStore.add(amazon);
//        supplierDataStore.remove(1);
//        assertEquals(0, supplierDataStore.getAll().size());
//    }
//
//    @Test
//    public void testFind_FindSupplierIfNoSupplier_ReturnNull(){
//        assertEquals(null, supplierDataStore.find(1));
//    }
//
//    @Test
//    public void testRemove_RemoveSupplierIfThereIsNone_ProgramContinuesRunning(){
//        supplierDataStore.remove(1);
//    }
//
//    @Test
//    public void testGetAll_GetAllIfNothingInList_ReturnEmptyList(){
//        List<Supplier> suppliers = new ArrayList<>();
//        assertEquals(suppliers, supplierDataStore.getAll());
//    }
//
//    @Test
//    public void testGetAll_GetAllIfSuppliersInList_ReturnSuppliersList(){
//        Supplier amazon = new Supplier("Amazon", "Digital content and services");
//        List<Supplier> suppliers = new ArrayList<>();
//        suppliers.add(amazon);
//        supplierDataStore.add(amazon);
//        assertEquals(suppliers, supplierDataStore.getAll());
//    }
//

}