package com.atgui.web;

import com.atgui.pojo.Cart;
import com.atgui.pojo.Order;
import com.atgui.pojo.OrderItem;
import com.atgui.pojo.User;
import com.atgui.service.OrderService;
import com.atgui.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {
    private OrderService orderServlet=new OrderServiceImpl();
    //生成订单
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //获取Cart和userId
        Cart cart = (Cart) req.getSession().getAttribute("cart");
       User user = (User) req.getSession().getAttribute("user");
       if(user==null){
           //如果没有登入跳到登入页面
           req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
           return;
       }



        Integer id = user.getId();
        String orderId = orderServlet.createOrder(cart, id);
      //页面显示订单号
        req.getSession().setAttribute("orderId", orderId);
        //请求重定向，防止重复提交
       /* req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);*/
       resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }



    //查看所有订单
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Order> orders = orderServlet.showAllOrders();


        req.setAttribute("orders",orders);




        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);


    }
   //查看我的订单



    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到用户id号
        User user = (User) req.getSession().getAttribute("user");
        if(user!=null){

            Integer id = user.getId();
            List<Order> order = orderServlet.showMyOrders(id);
            req.setAttribute("order",order);
          req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);

        }else {
            resp.sendRedirect(req.getContextPath()+"/pages/user/login.jsp");
        }
    }

    //查看订单详情
  protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String order_Id = req.getParameter("id");
      List<OrderItem> item = orderServlet.showOrderDetail(order_Id);
      req.setAttribute("items",item);
      req.setAttribute("id",order_Id);
      req.getRequestDispatcher("/pages/cart/cart.jsp").forward(req,resp);


  }



    //退单
    protected void salesReturn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String order_Id = req.getParameter("id");

        orderServlet.salesReturnService(order_Id);
        //得到登入用户
        User user = (User) req.getSession().getAttribute("user");
        if(user!=null){
            //执行退单后查看更新后登入订单
            Integer id = user.getId();
            List<Order> order = orderServlet.showMyOrders(id);
            req.setAttribute("order",order);
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
          /* resp.sendRedirect(req.getContextPath()+"/pages/order/order.jsp");*/
        }else {
            //执行退单后查看更新后管理员订单
            List<Order> orders = orderServlet.showAllOrders();
            req.setAttribute("orders",orders);
            req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
           /* resp.sendRedirect(req.getContextPath()+"/pages/manager/order_manager.jsp");*/
        }


    }
    //发货


    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String order_Id=req.getParameter("id");
        int i = orderServlet.sendOrder(order_Id);
        List<Order> orders = orderServlet.showAllOrders();
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

  //签收订单



    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        int i = orderServlet.receiverOrder(userId);
        User user = (User) req.getSession().getAttribute("user");
        if(user!=null){

            Integer id = user.getId();
            List<Order> order = orderServlet.showMyOrders(id);
            req.setAttribute("order",order);
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);

        }else {
            resp.sendRedirect(req.getContextPath()+"/pages/user/login.jsp");
        }

    }
}

