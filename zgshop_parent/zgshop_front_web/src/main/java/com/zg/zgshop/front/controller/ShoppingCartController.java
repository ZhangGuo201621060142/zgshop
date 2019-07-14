package com.zg.zgshop.front.controller;

import com.github.pagehelper.PageInfo;
import com.zg.zgshop.common.util.ResponseResult;
import com.zg.zgshop.pojo.Product;
import com.zg.zgshop.pojo.ShoppingCart;
import com.zg.zgshop.pojo.ShoppingCartItem;
import com.zg.zgshop.service.ProductService;
import com.zg.zgshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/17 11:26
 */
@Controller
@RequestMapping("/front/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/selectAll")
    public String selectAll(Model model) {
        List<ShoppingCartItem> items = new ArrayList<>();
        ShoppingCartItem item = null;
        for (ShoppingCart shoppingCart : shoppingCartService.selectUnPay()) {
            item = new ShoppingCartItem();
            item.setNum(shoppingCart.getNum());
            item.setPrice(shoppingCart.getPrice());
            Product product = productService.selectById(shoppingCart.getProductId());
            item.setName(product.getName());
            items.add(item);
        }
        PageInfo<ShoppingCartItem> pageInfo = new PageInfo<>(items);
        model.addAttribute("pageInfo", pageInfo);
        return "shoppingCart";
    }

    @RequestMapping("/backMain")
    @ResponseBody
    public ResponseResult backMain() {
        return ResponseResult.success();
    }

}
