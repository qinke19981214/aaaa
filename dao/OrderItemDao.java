package com.atgui.dao;

import com.atgui.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    //保存訂單項
        public int saveOrderItem(OrderItem orderItem);
    //查询某个订单的明细
    public List<OrderItem> queryOrderDetailByOrderId(String order_Id);
    //删除订单项
    public int deleteSalesReturnItem(String order_Id );
}
