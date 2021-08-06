package com.atgui.dao;

import com.atgui.pojo.Order;

import java.util.List;

public interface OrderDao {
    //保存訂單
    public  int saveOrder(Order order);
    //查询所有的订单
    public List<Order> queryAllOrders();
    //查看我的订单
    public  List<Order> queryMyOrders(int user_Id);
    //删除订单
  public int deleteSalesReturn(String order_Id);
  //修改货物状态
    public int  changeOrderStatus(String order_Id,int  status);
}
