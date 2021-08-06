package com.atgui.web;

import com.atgui.pojo.Book;
import com.atgui.pojo.Cart;
import com.atgui.pojo.CartItem;
import com.atgui.service.BookService;
import com.atgui.service.impl.BookServiceImpl;
import com.atgui.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {
   private BookService bookServlet= new BookServiceImpl();

//清空购物车
    protected void clearItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }


    }




//删除商品
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      int id=BeanUtil.parseInt(req.getParameter("id"),0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }


    }
    //添加商品
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
       int id = BeanUtil.parseInt(req.getParameter("id"),0) ;
       //通过商品编号查找商品信息
        Book book = bookServlet.queryBookById(id);
        //把书封装成CartItem对象
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }

        cart.addItem(cartItem);
        //请求重定向,req.getHeader("Referer")获取当前页面地址
        resp.sendRedirect(req.getHeader("Referer"));
        //最后添加的商品名称
        req.getSession().setAttribute("lastName",cartItem.getName());


    }
    //修改购车商品的数量
    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //获取请求参数
        //1获取id
        int id=BeanUtil.parseInt(req.getParameter("id"),0);
        //获取商品数量
        int count=BeanUtil.parseInt(req.getParameter("count"),0);
        //获取cart对象
        Cart  cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.updateCount(id,count);

            //重定向到原来的展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }


    }
}
