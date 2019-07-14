package com.zg.zgshop.service;

import com.zg.zgshop.common.exception.ProductTypeDeleteException;
import com.zg.zgshop.common.exception.ProductTypeExistException;
import com.zg.zgshop.common.util.ResponseResult;
import com.zg.zgshop.pojo.ProductType;

import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/12 16:10
 */
public interface ProductTypeService {

    /**
     * 查找所有商品类型信息
     * @return
     */
    List<ProductType> findAll();

    /**
     * 添加商品类型
     * 判断商品名称是否存在，存在则抛出异常
     * @param name
     */
    void add(String name) throws Exception;

    /**
     * 根据id查询商品类型
     * @param id
     * @return
     */
    ProductType findById(int id);

    /**
     * 根据id查询到商品类型并修改为新的名称
     * @param id
     * @param name
     * @return
     */
    void modifyName(int id, String name);

    /**
     * 根据id删除商品类型
     * @param id
     */
    void deleteById(int id) throws ProductTypeDeleteException;

    /**
     * 根据id修改商品类型的状态
     * @param id
     */
    void updateStatus(int id);

    /**
     * 查找所有有效的商品类型
     * @return
     */
    List<ProductType> findEnable();
}
