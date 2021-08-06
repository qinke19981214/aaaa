package com.atgui.service.impl;

import com.atgui.dao.BookDao;
import com.atgui.dao.OrderDao;
import com.atgui.dao.OrderItemDao;
import com.atgui.dao.impl.BookDaoImpl;
import com.atgui.dao.impl.OrderDaoImpl;
import com.atgui.dao.impl.OrderItemDaoImpl;
import com.atgui.pojo.*;
import com.atgui.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
     //订单号唯一
     String orderId=System.currentTimeMillis()+""+userId;
     //先生成订单
        Order order=new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
       //保存订单
        orderDao.saveOrder(order);
     //遍历购物车把转化成OrderItem
        for(Map.Entry<Integer, CartItem> entry:cart.getMap().entrySet()){
            CartItem value = entry.getValue();
            //创建订单项
            OrderItem orderItem=new OrderItem(null,value.getName(),value.getCount(),value.getPrice(),value.getTotalPrice(),orderId);
           //保存订单项
            orderItemDao.saveOrderItem(orderItem);
            Book book = bookDao.queryBookById(value.getId());
            //设置销量
            book.setSales(book.getSales()+orderItem.getCount());
            //设置库存
           book.setStock(book.getStock()-orderItem.getCount());
           //在把更新之后数据保存到数据库
            bookDao.updateBook(book);

        }
        //清空购物车
        cart.clear();
        //返回订单号
        return  orderId;

    }
 //查找所有订单
 public List<Order> showAllOrders(){

     return    orderDao.queryAllOrders();

 }
//查询我的订单
    @Override
    public List<Order> showMyOrders(int user_id) {
        return orderDao.queryMyOrders(user_id);
    }
//查看订单详情
    @Override
    public List<OrderItem> showOrderDetail(String order_id) {
      return   orderItemDao.queryOrderDetailByOrderId(order_id);
    }
//退单
    public void salesReturnService(String order_id){

        int j= orderItemDao.deleteSalesReturnItem(order_id);
        int i=  orderDao.deleteSalesReturn(order_id);

        System.out.println(i+"  "+j);
    }
    //发货
    @Override
    public int sendOrder(String order_Id){
      return  orderDao.changeOrderStatus(order_Id,1);
    }
//签收
@Override
    public  int receiverOrder(String order_Id){
      return   orderDao.changeOrderStatus(order_Id,2);
    }

}
