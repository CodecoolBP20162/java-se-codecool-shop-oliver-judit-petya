package com.codecool.shop.dao;

import com.codecool.shop.dao.jdbcImplementation.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.memImplementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {

    static ProductCategoryDao productCategoryDataStoreMem = ProductCategoryDaoMem.getInstance();
    static ProductCategoryDao productCategoryDataStoreJdbc = ProductCategoryDaoJDBC.getInstance();

    static Stream<ProductCategoryDao> daoProvider() {
        return Stream.of(productCategoryDataStoreJdbc, productCategoryDataStoreMem);
    }

    @BeforeEach
    public void setup(){
        daoProvider().forEach(ProductCategoryDao::removeAll);
        }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testAdd_AddNewProductCategory_ProductCategoryAdded(ProductCategoryDao dao){
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware","A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        dao.add(tablet);
        assertEquals(tablet, dao.getAll().get(dao.getAll().size() - 1));
    }

    @ParameterizedTest
    @MethodSource(names="daoProvider")
    public void testFind_FindProductCategory_ReturnProductCategoryWithId(ProductCategoryDao dao){
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware","A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        dao.add(tablet);
        assertEquals(tablet, dao.find(tablet.getId()));
    }

    @ParameterizedTest
    @MethodSource(names="daoProvider")
    public void testRemove_RemoveProductCategory_ProductCAtegoryRemoved(ProductCategoryDao dao){
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware","A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        dao.add(tablet);
        dao.remove(tablet.getId());
        assertEquals(0, dao.getAll().size());
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testFind_FindProductCategoryIfNoProductCategory_ReturnNull(ProductCategoryDao dao){
        assertEquals(null, dao.find(1));
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testRemove_RemoveProductCategoryIfThereIsNone_ProgramContinuesRunning(ProductCategoryDao dao){
        dao.remove(1);
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testGetAll_GetAllIfNothingInList_ReturnEmptyList(ProductCategoryDao dao){
        List<ProductCategory> productCategories = new ArrayList<>();
        assertEquals(productCategories, dao.getAll());
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testGetAll_GetAllIfProductCategoryInList_ReturnProductCategoriesList(ProductCategoryDao dao){
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware","A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        List<ProductCategory> suppliers = new ArrayList<>();
        suppliers.add(tablet);
        dao.add(tablet);
        assertEquals(suppliers, dao.getAll());
    }

}