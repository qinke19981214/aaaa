package com.atgui.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class BeanUtil {
    public static <T> T copy(Map value,T bean){
        try {
            System.out.println("注入之前:"+bean);
            BeanUtils.populate(bean,value);
            return  bean;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  bean;
    }
    public  static  int parseInt(String s,int s1){
        try {
            if(s!=null){

            return Integer.parseInt(s);}
        }catch (Exception e){
//            e.printStackTrace();
        }

        return s1;

    }
}
