package com.atgui.test;

import com.atgui.dao.BookDao;
import com.atgui.dao.impl.BookDaoImpl;
import com.atgui.pojo.Book;
import com.atgui.service.BookService;
import com.atgui.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookDaoTest {
    BookDao  bookDao=new BookDaoImpl();

    @org.junit.Test
    public void addBook() {
        bookDao.addBook(new Book(null,"我是谁","秦柯",new BigDecimal(2000),9999,100,null));
    }

    @org.junit.Test
    public void deleteBook() {
        System.out.println(bookDao.deleteBook(2));

    }

    @org.junit.Test
    public void updateBook() {
        bookDao.updateBook(new Book(1,"我是谁","秦智",new BigDecimal(20001),9999,1001,null));
    }

    @org.junit.Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(1);
        System.out.println(book);
    }

    @org.junit.Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        books.forEach(System.out::println);
    }
    @Test
    public void queryForPageTotalCount(){
        System.out.println(bookDao.queryForPageTotalCount());
    }
    @Test
    public void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(0, 4);
        books.forEach(System.out::println);

    }
    @Test
    public  void page(){
        BookService bookService=new BookServiceImpl();
        System.out.println(bookService.page(1,4));
    }
}