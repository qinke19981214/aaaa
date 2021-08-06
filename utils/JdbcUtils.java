package com.atgui.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;



import java.io.InputStream;
import java.sql.Connection;

import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource da ;

    static {
        try { InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties  ps = new Properties();
            ps.load(is);
            da= (DruidDataSource) DruidDataSourceFactory.createDataSource(ps);
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection1(){
        Connection  coon =null;
        try{

            coon=da.getConnection();

        }catch (Exception e){
            e.printStackTrace();
        }
        return coon;
    }
    public static  void  getClose1(Connection coon){
        try{
            if(coon!=null){
                coon.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }}
