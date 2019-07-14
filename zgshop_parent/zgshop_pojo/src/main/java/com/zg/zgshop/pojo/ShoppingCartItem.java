package com.zg.zgshop.pojo;

/**
 * @Author: zg
 * @Date: 2019/3/17 14:32
 */
public class ShoppingCartItem {

    private String name;
    private Integer num;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
