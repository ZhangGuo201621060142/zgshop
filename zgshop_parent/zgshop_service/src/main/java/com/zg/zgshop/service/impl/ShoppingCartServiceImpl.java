package com.zg.zgshop.service.impl;

import com.zg.zgshop.common.constant.ShoppingCartConstant;
import com.zg.zgshop.dao.OrderDao;
import com.zg.zgshop.dao.ShoppingCartDao;
import com.zg.zgshop.pojo.Product;
import com.zg.zgshop.pojo.ShoppingCart;
import com.zg.zgshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/16 19:28
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    public void insert(Product product, Integer num) {
        ShoppingCart item = shoppingCartDao.selectByProductId(product.getId());
        //若添加同种商品进购物车，则合并
        if (item != null) {
            num += item.getNum();
            shoppingCartDao.update(product.getId(), num);
        } else {
            shoppingCartDao.insert(product.getId(), product.getPrice(), num);
        }
    }

    @Override
    public List<ShoppingCart> selectAll() {
        return shoppingCartDao.selectAll();
    }

    @Override
    public List<ShoppingCart> selectByOrderId(int id) {
        return shoppingCartDao.selectByOrderId(id);
    }


    @Override
    public void updateOrderIdByShoppingCartId(Integer shoppingCartId, int orderId) {
        shoppingCartDao.updateOrderIdByShoppingCartId(shoppingCartId, orderId);
    }

    @Override
    public List<ShoppingCart> selectUnPay() {
        return shoppingCartDao.selectUnPay(ShoppingCartConstant.UNPAY_ORDERID);
    }

    @Override
    public int selectNumByProductId(Integer id) {
        Integer num = shoppingCartDao.selectNumByProductId(id);
        return num == null ? 0 : num;
    }

    @Override
    public Integer selectSaleNumById(Integer id) {
        Integer num = shoppingCartDao.selectSaleNumById(id);//匹配id且已付款，放在购物车里的不算已出售或已购买
        return num == null ? 0 : num;
    }
}
