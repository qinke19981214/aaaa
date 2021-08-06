package com.atgui.dao.impl;

import com.atgui.dao.BookDao;
import com.atgui.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String  sql="insert into t_book(name,price,author,sales,img_path,stock)values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getImgPath(),book.getStock());
    }

    @Override
    public int deleteBook(Integer id) {
       String sql="delete from t_book where id=?";
       return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql="update t_book set name=?,price=?,author=?,sales=?, img_path=?,stock=? where id=?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getImgPath(),book.getStock(),book.getId());
    }

    @Override
    public Book queryBookById( Integer id) {
       String sql="select id,name,price,author,sales, img_path,stock from t_book where id=? ";
       return  queryforone(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String  sql="select id, name,price,author,sales, img_path,stock from t_book ";
        return queryones(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String  sql="select count(*) from t_book";
        Number o =(Number) queryforStringValue(sql);
        return o.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql="select id, name,price,author,sales, img_path,stock from t_book limit ?,?";
        return queryones(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String  sql="select count(*) from t_book where price between ? and ?";
        Number o =(Number) queryforStringValue(sql,min,max);
        return o.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select id, name,price,author,sales, img_path,stock from t_book where price between ? and ? order by price limit ?,?";
        return queryones(Book.class,sql,min,max,begin,pageSize);
    }


}
