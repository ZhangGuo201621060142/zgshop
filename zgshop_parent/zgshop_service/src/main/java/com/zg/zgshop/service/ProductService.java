package com.zg.zgshop.service;

import com.zg.zgshop.dto.ProductDto;
import com.zg.zgshop.pojo.Product;
import org.apache.commons.fileupload.FileUploadException;

import java.io.OutputStream;
import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/13 11:18
 */
public interface ProductService {
    void add(ProductDto productDto) throws Exception;

    List<Product> findAll();

    Product selectById(int id);

    void getImage(String path, OutputStream outputStream);

    void modify(ProductDto productDto) throws Exception;

    void removeById(int id);

    List<Product> findBySaleStatus(int unsaleStatus);

    void updateSaleStatus(int saleStatus, int productId);

    boolean checkName(String name);
}
