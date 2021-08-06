package com.atgui.test;

import com.atgui.service.OrderService;
import com.atgui.service.impl.OrderServiceImpl;
import org.junit.Test;

public class OrderServiceImplTest1 {

    @Test
    public void showAllOrders() {
        OrderService orderService=new OrderServiceImpl();
        orderService.showAllOrders().forEach(System.out::println);

    }
}