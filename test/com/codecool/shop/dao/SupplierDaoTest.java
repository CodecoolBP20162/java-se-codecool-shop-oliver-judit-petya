package com.codecool.shop.dao;

import com.codecool.shop.dao.jdbcImplementation.SupplierDaoJDBC;
import com.codecool.shop.dao.memImplementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterAll;
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
        assertEquals(amazon, dao.find(amazon.getId()));
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testRemove_RemoveSupplier_SupplierRemoved(SupplierDao dao){
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        dao.add(amazon);
        dao.remove(amazon.getId());
        assertEquals(0, dao.getAll().size());
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testFind_FindSupplierIfNoSupplier_ReturnNull(SupplierDao dao){
        assertEquals(null, dao.find(1));
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testRemove_RemoveSupplierIfThereIsNone_ProgramContinuesRunning(SupplierDao dao){
        dao.remove(1);
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testGetAll_GetAllIfNothingInList_ReturnEmptyList(SupplierDao dao){
        List<Supplier> suppliers = new ArrayList<>();
        assertEquals(suppliers, dao.getAll());
    }

    @ParameterizedTest
    @MethodSource(names = "daoProvider")
    public void testGetAll_GetAllIfSuppliersInList_ReturnSuppliersList(SupplierDao dao){
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(amazon);
        dao.add(amazon);
        assertEquals(suppliers, dao.getAll());
    }

}