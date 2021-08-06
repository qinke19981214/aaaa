package com.atgui.pojo;

import java.math.BigDecimal;

public class OrderItem {
    private  Integer id;  //主键编号
    private  String name;//商品名称
    private  Integer count;//商品数量
    private BigDecimal price;//商品单价
    private BigDecimal total_Price;//总价
    private  String  order_Id;//订单号

    public OrderItem() {
    }

    public OrderItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal total_Price, String order_Id) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.total_Price = total_Price;
        this.order_Id = order_Id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal_Price() {
        return total_Price;
    }

    public void setTotal_Price(BigDecimal total_Price) {
        this.total_Price = total_Price;
    }

    public String getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(String order_Id) {
        this.order_Id = order_Id;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + total_Price +
                ", orderId='" + order_Id + '\'' +
                '}';
    }
}
