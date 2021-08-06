package com.atgui.test;

import com.atgui.dao.OrderDao;
import com.atgui.dao.impl.OrderDaoImpl;
import com.atgui.pojo.Order;
import org.junit.Test;

import java.util.List;

public class OrderDaoImplTest1 {

    @Test
    public void queryAllOrders() {
        OrderDao orderDao=new OrderDaoImpl();
        List<Order> orders = orderDao.queryAllOrders();
        orders.forEach(System.out::println);
    }
}