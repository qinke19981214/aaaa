package com.atgui.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {


    private Map<Integer,CartItem> map =new LinkedHashMap<>();

    //添加商品项
   public void addItem(CartItem cartItem){
       CartItem item = map.get(cartItem.getId());
       if(item==null){
           map.put(cartItem.getId(),cartItem);
       }else{
           item.setCount(item.getCount()+1);//数量累加
          //更新总金额
           item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
       }
   }


    //删除商品项
    public void deleteItem(Integer id){
  map.remove(id);
    }

//清空购物车
    public  void  clear(){
  map.clear();
    }

//修改商品数量

 public void updateCount(Integer id,Integer count){
     CartItem cartItem = map.get(id);
     if(cartItem!=null){
         cartItem.setCount(count);
         cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
     }
 }






    public Integer getTotalCount() {
      Integer   totalCount=0;
         for(CartItem  value   :map.values()){
             totalCount+=value.getCount();
         }

        return totalCount;
    }



    public BigDecimal getTotalPrice() {
    BigDecimal   totalPrice=new BigDecimal(0);
        for(CartItem  value   :map.values()) {

            totalPrice=totalPrice.add(value.getTotalPrice());
        }
        return totalPrice;
    }



    public Map<Integer, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<Integer, CartItem> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice()           +
                ", map=" + map +
                '}';
    }
}
