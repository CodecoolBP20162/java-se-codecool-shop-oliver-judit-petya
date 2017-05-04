package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.ArrayList;
import java.util.List;


public class OrderDaoMem implements OrderDao {

    private static OrderDaoMem instance = null;
    private List<Order> DATA = new ArrayList<>();

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public List<Order> getAll() {
        return DATA;
    }

    @Override
    public void add(Order order) {
        order.setId(DATA.size() + 1);
        DATA.add(order);
    }

    @Override
    public Order find(int id) {
        return DATA.get(0);
        //return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }
}
