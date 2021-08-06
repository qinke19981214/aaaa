package com.atgui.test;

import com.atgui.dao.OrderItemDao;
import com.atgui.dao.impl.OrderItemDaoImpl;
import com.atgui.pojo.OrderItem;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest1 {

    @Test
    public void queryOrderDetailByOrderId() {
        OrderItemDao orderItemDao=new OrderItemDaoImpl();
        List<OrderItem> orderItems = orderItemDao.queryOrderDetailByOrderId("124567890");
        for (OrderItem orderItem : orderItems) {

            System.out.println(orderItem);
        }
    }
}