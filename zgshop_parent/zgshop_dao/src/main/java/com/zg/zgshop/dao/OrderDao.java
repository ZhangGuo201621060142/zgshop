package com.zg.zgshop.dao;

import com.zg.zgshop.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/17 19:35
 */
public interface OrderDao {
    void insert(@Param("price") Double price, @Param("date") Date date);

    Order selectLast();

    List<Order> selectAll();
}
