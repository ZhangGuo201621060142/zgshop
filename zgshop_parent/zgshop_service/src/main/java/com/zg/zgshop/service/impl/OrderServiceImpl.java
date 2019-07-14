package com.zg.zgshop.service.impl;

import com.zg.zgshop.common.constant.OrderConstant;
import com.zg.zgshop.dao.OrderDao;
import com.zg.zgshop.pojo.Order;
import com.zg.zgshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/17 19:34
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void insert(Order order) {
        orderDao.insert(order.getPrice(), order.getDate());
    }

    @Override
    public int selectLast() {
        if (orderDao.selectLast() == null) {
            return OrderConstant.ORDER_BEGIN_ID;
        }
        return orderDao.selectLast().getId();
    }

    @Override
    public List<Order> selectAll() {
        return orderDao.selectAll();
    }
}
