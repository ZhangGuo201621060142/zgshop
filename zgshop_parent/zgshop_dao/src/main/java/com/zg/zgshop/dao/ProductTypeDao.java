package com.zg.zgshop.dao;

import com.zg.zgshop.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/12 15:55
 */
public interface ProductTypeDao {

    List<ProductType> selectAll();

    ProductType selectById(int id);

    ProductType selectByName(String name);

    void insert(@Param("name") String name, @Param("status") int status);

    void updateName(@Param("id") int id, @Param("name") String name);

    void updateStatus(@Param("id") int id, @Param("status") int status);

    void deleteById(int id);

    List<ProductType> selectEnable(int status);
}
