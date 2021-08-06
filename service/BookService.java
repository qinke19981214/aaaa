package com.atgui.service;

import com.atgui.pojo.Book;
import com.atgui.pojo.Page;

import java.util.List;

public interface BookService {
    public  void addBook(Book book);
    public  void deleteById(Integer id);
    public  void updateBook(Book book);
    public  Book queryBookById(Integer id);
    public List<Book> queryBook();
     public Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
