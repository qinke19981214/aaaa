package com.atgui.service.impl;

import com.atgui.dao.BookDao;
import com.atgui.dao.impl.BookDaoImpl;
import com.atgui.pojo.Book;
import com.atgui.pojo.Page;
import com.atgui.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao  bookDao=new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);

    }

    @Override
    public void deleteById(Integer id) {
        bookDao.deleteBook(id);

    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);

    }
//通过id查找图书信息
    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBook() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page=new Page<>();

        //设置每页显示的数量
        page.setPageSize(pageSize);
        //总的记录数
       Integer pageTotalCpunt=bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCpunt);
        //求总页码
        Integer pageTotal=pageTotalCpunt/pageSize;
        if(pageTotalCpunt%pageSize>0){
            pageTotal+=1;
        }
        //设置总的页码
        page.setPageTotal(pageTotal);


        // 设置当前页码

        page.setPageNo(pageNo);




        int begin=(pageNo-1)*pageSize;

        List<Book> items=bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);


        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page=new Page<>();

        //设置每页显示的数量
        page.setPageSize(pageSize);
        //总的记录数
        Integer pageTotalCpunt=bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCpunt);
        //求总页码
        Integer pageTotal=pageTotalCpunt/pageSize;
        if(pageTotalCpunt%pageSize>0){
            pageTotal+=1;
        }
        //设置总的页码
        page.setPageTotal(pageTotal);

        // 设置当前页码

        page.setPageNo(pageNo);



        int begin=(pageNo-1)*pageSize;

        List<Book> items=bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);


        return page;
    }
}
