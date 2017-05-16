package com.codecool.shop.dao;

import com.codecool.shop.dao.memImplementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {

    static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

    @BeforeEach
    public void setup(){
        List<Integer> ids = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryDataStore.getAll()) {
            ids.add(productCategory.getId());
        }
        for (Integer id: ids){
            productCategoryDataStore.remove(id);
        }
    }

    @Test
    public void testAdd_AddNewSupplier_ProductCategoryAddedToMem(){
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware","A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        assertEquals(tablet, productCategoryDataStore.getAll().get(0));
    }

    @Test
    public void testFind_FindSupplier_ReturnProductCategoryWithId(){
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware","A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        assertEquals(tablet, productCategoryDataStore.find(1));
    }

    @Test
    public void testRemove_RemoveProductCategory_ProductCAtegoryRemovedFromMem(){
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware","A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        productCategoryDataStore.remove(1);
        assertEquals(0, productCategoryDataStore.getAll().size());
    }

    @Test
    public void testFind_FindSupplierIfNoProductCategory_ReturnNull(){
        assertEquals(null, productCategoryDataStore.find(1));
    }

    @Test
    public void testRemove_RemoveProductCategoryIfThereIsNone_ProgramContinuesRunning(){
        productCategoryDataStore.remove(1);
    }

    @Test
    public void testGetAll_GetAllIfNothingInList_ReturnEmptyList(){
        List<ProductCategory> productCategories = new ArrayList<>();
        assertEquals(productCategories, productCategoryDataStore.getAll());
    }

    @Test
    public void testGetAll_GetAllIfProductCategoryInList_ReturnProductCategoriesList(){
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware","A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        List<ProductCategory> suppliers = new ArrayList<>();
        suppliers.add(tablet);
        productCategoryDataStore.add(tablet);
        assertEquals(suppliers, productCategoryDataStore.getAll());
    }



}