package com.zg.zgshop.dao;

import com.zg.zgshop.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/16 19:31
 */
public interface ShoppingCartDao {

    void insert(@Param("id") Integer id, @Param("price") Double price, @Param("num") Integer num);

    ShoppingCart selectByProductId(Integer productId);

    void update(@Param("productId") Integer productId, @Param("num") int num);

    List<ShoppingCart> selectAll();

    List<ShoppingCart> selectByOrderId(int id);

    int selectLast();

    void updateOrderIdByShoppingCartId(@Param("shoppingCartId") Integer shoppingCartId, @Param("orderId") int orderId);

    List<ShoppingCart> selectUnPay(int unpayOrderid);

    Integer selectNumByProductId(Integer id);

    Integer selectSaleNumById(Integer id);
}
