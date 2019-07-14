package com.zg.zgshop.pojo;

import java.io.Serializable;

/**
 * @Author: zg
 * @Date: 2019/3/12 15:45
 */
public class ProductType implements Serializable {

    private Integer id;
    private String name;
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
