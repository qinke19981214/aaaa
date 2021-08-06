package com.atgui.dao.impl;

import com.atgui.dao.OrderItemDao;
import com.atgui.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    //保存订单项
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(name,count,price,total_Price,order_Id)values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotal_Price(),orderItem.getOrder_Id());
    }
    //查询某个订单的明细
    @Override
    public List<OrderItem> queryOrderDetailByOrderId(String order_Id) {
        String sql="select id,name,count,price,total_price,order_id from t_order_item where order_id=?";
        return queryones(OrderItem.class,sql,order_Id);

    }
    //删除订单项
    @Override
    public int deleteSalesReturnItem(String order_Id) {
     String sql="delete from t_order_item where order_id=?";
    return update(sql,order_Id);
    }
}
