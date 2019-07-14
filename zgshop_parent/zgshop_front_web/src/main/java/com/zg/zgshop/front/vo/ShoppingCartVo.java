package com.zg.zgshop.front.vo;

/**
 * @Author: zg
 * @Date: 2019/3/16 20:08
 */
public class ShoppingCartVo {

    private Integer product_id;
    private Double price;
    private Integer num;

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
