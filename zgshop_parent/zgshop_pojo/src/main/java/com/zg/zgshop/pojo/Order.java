package com.zg.zgshop.pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: zg
 * @Date: 2019/3/17 19:30
 */
public class Order {

    private Integer id;

    private Double price;

    private Timestamp date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
