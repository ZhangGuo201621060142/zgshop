package com.zg.zgshop.service.impl;

import com.zg.zgshop.common.constant.ProductTypeConstant;
import com.zg.zgshop.common.exception.ProductTypeDeleteException;
import com.zg.zgshop.common.exception.ProductTypeExistException;
import com.zg.zgshop.dao.ProductDao;
import com.zg.zgshop.dao.ProductTypeDao;
import com.zg.zgshop.pojo.Product;
import com.zg.zgshop.pojo.ProductType;
import com.zg.zgshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/12 16:11
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDao productTypeDao;

    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ProductType> findAll() {
        return productTypeDao.selectAll();
    }

    @Override
    public void add(String name) throws Exception {
        if (name == null || "".equals(name.trim())) {
            throw new Exception("商品类型不能为空");
        }
        ProductType productType = productTypeDao.selectByName(name);
        if (productType != null) {
            throw new ProductTypeExistException("商品类型已存在");
        }
        productTypeDao.insert(name, ProductTypeConstant.PRODUCT_TYPE_ENABLE);
    }

    @Override
    public ProductType findById(int id) {
        return productTypeDao.selectById(id);
    }

    @Override
    public void modifyName(int id, String name) {
        productTypeDao.updateName(id, name);
    }

    @Override
    public void deleteById(int id) throws ProductTypeDeleteException {
        List<Product> products = productDao.selectAll();
        List<Integer> productTypeId = new ArrayList<>();
        for (Product product : products) {
            productTypeId.add(product.getProductType().getId());
        }
        if (productTypeId.contains(id)) {
            throw new ProductTypeDeleteException("该类型已有商品，请先删除商品！");
        }
        productTypeDao.deleteById(id);
    }

    @Override
    public void updateStatus(int id) {
        ProductType productType = productTypeDao.selectById(id);
        if (productType.getStatus() == ProductTypeConstant.PRODUCT_TYPE_ENABLE) {
            productTypeDao.updateStatus(id, ProductTypeConstant.PRODUCT_TYPE_DISENABLE);
        } else {
            productTypeDao.updateStatus(id, ProductTypeConstant.PRODUCT_TYPE_ENABLE);
        }
    }

    @Override
    public List<ProductType> findEnable() {
        return productTypeDao.selectEnable(ProductTypeConstant.PRODUCT_TYPE_ENABLE);
    }
}
