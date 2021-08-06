package com.atgui.web;

import com.atgui.pojo.Book;
import com.atgui.pojo.Page;
import com.atgui.service.BookService;
import com.atgui.service.impl.BookServiceImpl;
import com.atgui.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int pageNo=BeanUtil.parseInt(req.getParameter("pageNo"),0);
       int pageSize=BeanUtil.parseInt(req.getParameter("pageSize"),4);
       int pageTotalCount=BeanUtil.parseInt(req.getParameter("pageTotalCount"),1);
        if (pageTotalCount>pageSize&&pageTotalCount%pageSize==0){
       pageNo=pageNo+1;}
        Book book = BeanUtil.copy(req.getParameterMap(), new Book());
        bookService.addBook(book);
        //使用请求重定向
//      resp.sendRedirect("/"+req.getContextPath()+"/manager/bookServlet?action=list ");
       resp.sendRedirect("http://localhost:8080/BookProject2/manager/bookServlet?action=page&pageNo="+pageNo);


    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id=BeanUtil.parseInt(req.getParameter("id"),0);
        bookService.deleteById(id);

        resp.sendRedirect("http://localhost:8080/BookProject2/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = BeanUtil.copy(req.getParameterMap(), new Book());
        bookService.updateBook(book);
        resp.sendRedirect("http://localhost:8080/BookProject2/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int id=BeanUtil.parseInt(req.getParameter("id"),0);
        Book book = bookService.queryBookById(id);
        req.setAttribute("book",book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //BookServlet查询图书
        List<Book> books = bookService.queryBook();
        //把查到的图书保存到Request域中
       req.setAttribute("books",books);

    //通过请求转发访问pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);


    }


    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1  获取请求的参数 pageNo和pageSize
      int  pageNo=BeanUtil.parseInt(req.getParameter("pageNo"),1);
      int  pageSize=BeanUtil.parseInt("pageSize", Page.PAGE_SIZE);



        //2调用BookService.page(pageNo,pageSize);返回的就是Page对象
        Page<Book> page=bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3保存Page对象到Request域中

        req.setAttribute("page",page);
        //4请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }
}
