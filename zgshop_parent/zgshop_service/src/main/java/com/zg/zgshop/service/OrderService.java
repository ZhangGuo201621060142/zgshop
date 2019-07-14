package com.zg.zgshop.service;

import com.zg.zgshop.pojo.Order;

import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/17 19:33
 */
public interface OrderService {
    void insert(Order order);

    int selectLast();

    List<Order> selectAll();
}
