package com.atgui.test;

import com.atgui.pojo.Cart;
import com.atgui.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {

    @Test
    public void addItem() {
        Cart  cart=new Cart();
        CartItem cart1=new CartItem(1,"JAVA从入门到放弃",1,new BigDecimal(10),new BigDecimal(10));
        CartItem cart2=new CartItem(1,"JAVA从入门到放弃",1,new BigDecimal(10),new BigDecimal(10));
        CartItem cart3=new CartItem(2,"JAVA从入门到成功",1,new BigDecimal(5),new BigDecimal(5));
       cart.addItem(cart1);
        cart.addItem(cart2);
        cart.addItem(cart3);
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void updateCount() {
    }
}