package com.atgui.service;

import com.atgui.pojo.Cart;
import com.atgui.pojo.Order;
import com.atgui.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    //生成订单
    public String createOrder(Cart cart,Integer userId);
    //查询所有订单
    public List<Order> showAllOrders();
    //查询我的订单
    public  List<Order> showMyOrders(int user_id);

    //查看订单详情
    public List<OrderItem> showOrderDetail(String order_id);
    //退单
    public void salesReturnService(String order_id);
//发货
    public int sendOrder(String order_Id);
    //签收
    public  int receiverOrder(String order_Id);
}
