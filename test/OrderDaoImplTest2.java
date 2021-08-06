package com.atgui.test;

import com.atgui.dao.OrderDao;
import com.atgui.dao.impl.OrderDaoImpl;
import com.atgui.pojo.Order;
import org.junit.Test;

import java.util.List;

public class OrderDaoImplTest2 {

    @Test


    public void queryMyOrders() {
        OrderDao order= new OrderDaoImpl();
        List<Order> orders = order.queryMyOrders(1);
        orders.forEach(System.out::println);

    }
}