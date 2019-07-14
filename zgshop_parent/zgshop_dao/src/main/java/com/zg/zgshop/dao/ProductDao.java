package com.zg.zgshop.dao;

import com.zg.zgshop.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/13 14:29
 */
public interface ProductDao {
    void insert(Product product);

    Product selectByName(String name);

    List<Product> selectAll();

    Product selectById(int id);

    void update(Product product);

    void removeById(int id);

    List<Product> selectBySaleStatus(int unSaleStatus);

    void updateSaleStatus(@Param("saleStatus") int saleStatus, @Param("productId") int productId);
}
