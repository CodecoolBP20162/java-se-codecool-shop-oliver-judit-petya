package com.codecool.shop.dao;

import com.codecool.shop.dao.memImplementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {

    static SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    static Supplier amazon = new Supplier("Amazon", "Digital content and services");

    @Test
    public void testAdd_AddNewSupplier_SupplierAddedToMem(){
        supplierDataStore.add(amazon);
        assertEquals(amazon, supplierDataStore.getAll().get(0));
    }

    @Test
    public void testFind_FindSupplier_ReturnSupplierWithId(){
        assertEquals(amazon, supplierDataStore.find(1));
    }

    @Test
    public void testRemove_RemoveSupplier_SupplierRemovedFromMem(){
        supplierDataStore.remove(1);
        assertEquals(0, supplierDataStore.getAll().size());
    }

    @Test
    public void testFind_FindSupplierIfNoSupplier_ReturnNull(){
        assertEquals(null, supplierDataStore.find(1));
    }

    @Test
    public void testRemove_RemoveSupplierIfThereIsNone_ProgramContinuesRunning(){
        supplierDataStore.remove(1);
    }

    @Test
    public void testGetAll_GetAllIfNothingInList_ReturnEmptyList(){
        List<Supplier> suppliers = new ArrayList<>();
        assertEquals(suppliers, supplierDataStore.getAll());
    }

    @Test
    public void testGetAll_GetAllIfSuppliersInList_ReturnSuppliersList(){
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(amazon);
        supplierDataStore.add(amazon);
        assertEquals(suppliers, supplierDataStore.getAll());
    }


}