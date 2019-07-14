package com.zg.zgshop.service.impl;

import com.zg.zgshop.common.constant.ProductConstant;
import com.zg.zgshop.common.util.StringUtils;
import com.zg.zgshop.dao.ProductDao;
import com.zg.zgshop.dao.ProductTypeDao;
import com.zg.zgshop.dto.ProductDto;
import com.zg.zgshop.pojo.Product;
import com.zg.zgshop.pojo.ProductType;
import com.zg.zgshop.service.ProductService;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/13 11:18
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    public void add(ProductDto productDto) throws Exception {
        if (productDao.selectByName(productDto.getName()) != null) {
            throw new Exception("商品已存在");
        }
        //1.文件上传
        String fileName = StringUtils.renameFileName(productDto.getFileName());
        String filePath = productDto.getUploadPath() + "\\" + fileName;
        try {
            StreamUtils.copy(productDto.getInputStream(), new FileOutputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadException("文件上传失败" + e.getMessage());
        }
        //2.保存到数据库
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImage(fileName);
        if (productDto.getInfo() == null || "".equals(product.getInfo())) {
            product.setInfo(ProductConstant.INFO);
        } else {
            product.setInfo(productDto.getInfo());
        }
        product.setSale_status(ProductConstant.UNSALE_STATUS);
        ProductType productType = productTypeDao.selectById(productDto.getProductTypeId());
        product.setProductType(productType);

        productDao.insert(product);

    }

    @Override
    public List<Product> findAll() {
        return productDao.selectAll();
    }

    @Override
    public Product selectById(int id) {
        return productDao.selectById(id);
    }

    @Override
    public void getImage(String path, OutputStream outputStream) {
        try {
            StreamUtils.copy(new FileInputStream(path), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modify(ProductDto productDto) throws Exception {
        String filePath = "";
        //1.文件上传
        if (!"".equals(productDto.getFileName())) {
            String fileName = StringUtils.renameFileName(productDto.getFileName());
            filePath = productDto.getUploadPath() + "/" + fileName;
            try {
                StreamUtils.copy(productDto.getInputStream(), new FileOutputStream(filePath));
            } catch (IOException e) {
                throw new FileUploadException("文件上传失败" + e.getMessage());
            }
        }
        //2.保存到数据库
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setInfo(productDto.getInfo());
        if (!"".equals(filePath)) {
            product.setImage(filePath);
        }
        product.setSale_status(ProductConstant.UNSALE_STATUS);
        ProductType productType = productTypeDao.selectById(productDto.getProductTypeId());
        product.setProductType(productType);
        try {
            productDao.update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(int id) {
        try {
            productDao.removeById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findBySaleStatus(int unSaleStatus) {
        return productDao.selectBySaleStatus(unSaleStatus);
    }

    @Override
    public void updateSaleStatus(int saleStatus, int productId) {
        productDao.updateSaleStatus(saleStatus, productId);
    }

    @Override
    public boolean checkName(String name) {
        Product product=productDao.selectByName(name);
        return product == null;
    }

}
