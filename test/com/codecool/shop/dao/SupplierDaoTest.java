package com.codecool.shop.dao;

import com.codecool.shop.dao.memImplementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {

    static SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

    @BeforeEach
    public void setup(){
        List<Integer> ids = new ArrayList<>();
        for (Supplier supplier: supplierDataStore.getAll()) {
            ids.add(supplier.getId());
        }
        for (Integer id: ids){
            supplierDataStore.remove(id);
        }
    }

    @Test
    public void testAdd_AddNewSupplier_SupplierAddedToMem(){
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        assertEquals(amazon, supplierDataStore.getAll().get(0));
    }

    @Test
    public void testFind_FindSupplier_ReturnSupplierWithId(){
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        assertEquals(amazon, supplierDataStore.find(1));
    }

    @Test
    public void testRemove_RemoveSupplier_SupplierRemovedFromMem(){
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
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
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(amazon);
        supplierDataStore.add(amazon);
        assertEquals(suppliers, supplierDataStore.getAll());
    }


}