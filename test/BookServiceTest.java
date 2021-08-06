package com.atgui.test;

import com.atgui.pojo.Book;
import com.atgui.service.BookService;
import com.atgui.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {
    private BookService bookService=new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"java实战开发","秦颖",new BigDecimal(20),500,1100,null));
    }

    @Test
    public void deleteById() {
        bookService.deleteById(4);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(2,"javawed实战开发","秦天",new BigDecimal(20),500,1100,null));
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(1);
        System.out.println(book);
    }

    @Test
    public void queryBook() {
        List<Book> books = bookService.queryBook();
        books.forEach(System.out::println);
    }
}