package com.atgui.dao;

import com.atgui.pojo.Book;

import java.util.List;

public interface BookDao {
    public  int addBook(Book book);
    public  int  deleteBook(Integer id);
    public  int  updateBook(Book  book );
    public  Book  queryBookById(Integer id);
    public List<Book> queryBooks();

   public Integer queryForPageTotalCount();


   public List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
