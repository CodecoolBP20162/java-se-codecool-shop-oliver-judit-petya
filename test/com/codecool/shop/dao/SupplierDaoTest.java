package com.codecool.shop.dao;

import com.codecool.shop.dao.jdbcImplementation.SupplierDaoJDBC;
import com.codecool.shop.dao.memImplementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {


    static SupplierDao supplierDataStoreMem = SupplierDaoMem.getInstance();
    static SupplierDao supplierDataStoreJdbc = SupplierDaoJDBC.getInstance();

    static Stream<SupplierDao> daoProvider() {
        return Stream.of(supplierDataStoreJdbc, supplierDataStoreMem);
    }

    @BeforeEach
    public void setup(){
        daoProvider().forEach(SupplierDao::removeAll);
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testAdd_AddNewSupplier_SupplierAdded(SupplierDao dao){
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        dao.add(amazon);
        assertEquals(amazon, dao.getAll().get(dao.getAll().size() - 1));
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testFind_FindSupplier_ReturnSupplierWithId(SupplierDao dao){
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        dao.add(amazon);
        assertEquals(amazon, dao.find(1));
    }

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


}