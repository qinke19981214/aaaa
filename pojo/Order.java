package com.atgui.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String order_Id;//订单号
    private Date create_Time;//下单时间
    private BigDecimal price;//金额
    //0未发货 1已发货 2已签收
    private Integer  status=0;
    private  Integer user_Id;//用户编号

    public Order() {
    }

    public Order(String order_Id, Date create_Time, BigDecimal price, Integer status, Integer user_Id) {
        this.order_Id = order_Id;
        this.create_Time = create_Time;
        this.price = price;
        this.status = status;
        this.user_Id = user_Id;
    }

    public String getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(String order_Id) {
        this.order_Id = order_Id;
    }

    public Date getCreate_Time() {
        return create_Time;
    }

    public void setCreate_Time(Date createTime) {
        this.create_Time = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Integer userId) {
        this.user_Id = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + order_Id + '\'' +
                ", createTime=" + create_Time +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + user_Id +
                '}';
    }
}
