package com.zg.zgshop.service;

import com.zg.zgshop.pojo.Product;
import com.zg.zgshop.pojo.ShoppingCart;

import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/16 19:27
 */
public interface ShoppingCartService {
    void insert(Product product, Integer num);

    List<ShoppingCart> selectAll();

    List<ShoppingCart> selectByOrderId(int id);

    void updateOrderIdByShoppingCartId(Integer shoppingCartId, int orderId);

    List<ShoppingCart> selectUnPay();

    int selectNumByProductId(Integer id);

    Integer selectSaleNumById(Integer id);
}
