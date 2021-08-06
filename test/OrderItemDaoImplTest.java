package com.atgui.test;

import com.atgui.dao.OrderItemDao;
import com.atgui.dao.impl.OrderItemDaoImpl;
import com.atgui.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {
    OrderItemDao orderItemDao=new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"时间简史",1,new BigDecimal(100),new BigDecimal(100),"124567890"));
    }
}