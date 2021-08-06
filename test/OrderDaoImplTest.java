package com.atgui.test;

import com.atgui.dao.OrderDao;
import com.atgui.dao.impl.OrderDaoImpl;
import com.atgui.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDaoImplTest {
OrderDao  orderDao=new OrderDaoImpl();
    @Test
    public void saveOrder() {
     orderDao.saveOrder(new Order("124567892",new Date(),new BigDecimal(100),0,1));
    }
}