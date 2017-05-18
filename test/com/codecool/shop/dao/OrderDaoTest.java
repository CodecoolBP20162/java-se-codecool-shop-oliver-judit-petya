package com.codecool.shop.dao;

import com.codecool.shop.dao.memImplementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {

    private static OrderDaoMem orderList = OrderDaoMem.getInstance();

    @BeforeEach
    public void clearOrderList(){
        orderList.getAll().clear();
    }

    @Test
    public void testAdd_AddNewOrder_OrderAddedToMem(){
        Order order = new Order();
        orderList.add(order);
        assertEquals(order, orderList.getAll().get(0));
    }

    @Test
    public void testFind_FindExistingOrder_ReturnsOrderById(){
        Order order = new Order();
        orderList.add(order);
        assertEquals(order, orderList.find(order.getId()));
    }

    @Test
    public void testRemove_AddNewOrderAndRemove_NewOrderRemovedFromMem(){
        Order order = new Order();
        orderList.add(order);
        orderList.remove(order.getId());
        assertEquals(0, orderList.getAll().size());
    }

    @Test
    public void testGetAll_AddNewOrders_ReturnsAllAddedOrders(){
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        List<Order> expectedList = Arrays.asList(order1, order2, order3);
        assertEquals(expectedList, orderList.getAll());
    }
}