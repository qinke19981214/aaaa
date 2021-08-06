package com.atgui.dao.impl;

import com.atgui.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao {
    QueryRunner qr=new QueryRunner();
    /*
     *返回其它则成功，返回-1则失败
     * */
    public  int update(String sql ,Object...args){
        Connection coon= JdbcUtils.getConnection1();
        try {
            return qr.update(coon, sql, args);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.getClose1(coon);
        }
        return -1;
    }
    /*
     * 返回一条javabean对象 ,返回null失败
     * */
    public <T> T queryforone(Class<T> type,String sql,Object...args){
        Connection coon=JdbcUtils.getConnection1();
        try {
            return qr.query(coon,sql,new BeanHandler<T>(type), args);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.getClose1(coon);
        }
        return null;
    }
    /*
     * 返回多条javabean对象 ,返回null失败
     * */
    public <T> List<T> queryones(Class<T> type, String sql, Object...args){
        Connection coon=JdbcUtils.getConnection1();
        try {
            return qr.query(coon, sql, new BeanListHandler<T>(type), args);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.getClose1(coon);
        }
        return null;
    }

    public  Object queryforStringValue(String sql ,Object...args){
        Connection coon=JdbcUtils.getConnection1();

        try {
            return qr.query(coon, sql, new ScalarHandler(), args);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.getClose1(coon);
        }
        return null;


    }
}
