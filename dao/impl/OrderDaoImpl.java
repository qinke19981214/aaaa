package com.atgui.dao.impl;

import com.atgui.dao.OrderDao;
import com.atgui.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(order_id,create_time,price,status,user_id)values(?,?,?,?,?)";
     return    update(sql,order.getOrder_Id(),order.getCreate_Time(),order.getPrice(),order.getStatus(),order.getUser_Id());
    }

    @Override
    public List<Order> queryAllOrders() {
       String sql="select order_id,create_time,price,status,user_id from t_order ";
       return  queryones(Order.class,sql);
    }

    @Override
    public List<Order> queryMyOrders(int user_Id) {
        String sql="select order_id,create_time,price,status,user_id from t_order where user_id=?";
        return queryones(Order.class,sql,user_Id);
    }

    @Override
    public int deleteSalesReturn(String order_Id) {
        String sql="delete from t_order where order_id=? ";
        return update(sql,order_Id);
    }

    @Override
    public int changeOrderStatus(String order, int status) {
        String sql="update t_order set status=? where order_id=? ";
      return   update(sql,status,order);
    }
}
