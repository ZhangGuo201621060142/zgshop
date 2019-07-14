package com.zg.zgshop.front.controller;

import com.zg.zgshop.common.constant.ProductConstant;
import com.zg.zgshop.common.constant.ShoppingCartConstant;
import com.zg.zgshop.common.util.ResponseResult;
import com.zg.zgshop.pojo.Order;
import com.zg.zgshop.pojo.ShoppingCart;
import com.zg.zgshop.service.OrderService;
import com.zg.zgshop.service.ProductService;
import com.zg.zgshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/17 19:31
 */
@Controller
@RequestMapping("/front/order")
public class OrderController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/payShoppingCart")
    @ResponseBody
    public ResponseResult payShoppingCart() {
        //取得未付款商品及数量单价信息
        List<ShoppingCart> items = shoppingCartService.selectByOrderId(ShoppingCartConstant.UNPAY_ORDERID);
        //结账并将订单更新到order表
        Order order = new Order();
        double price = 0;
        List<Integer> shoppingCartIds = new ArrayList<>();
        List<Integer> productIds = new ArrayList<>();
        for (ShoppingCart item : items) {
            price += item.getPrice() * item.getNum();
            shoppingCartIds.add(item.getId());
            productIds.add(item.getProductId());
        }
        order.setPrice(price);
        order.setDate(new Timestamp(System.currentTimeMillis()));
        orderService.insert(order);
        //获取当前结账的id号
        int orderId = orderService.selectLast();
        //更新购物车表中的商品订单id
        for (Integer shoppingCartId : shoppingCartIds) {
            shoppingCartService.updateOrderIdByShoppingCartId(shoppingCartId, orderId);
        }
        //将商品表中对应的商品设置为已购买
        for (Integer productId : productIds) {
            productService.updateSaleStatus(ProductConstant.SALE_STATUS,productId);
        }
        return ResponseResult.success("结账成功！");
    }

}
