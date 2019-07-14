package com.zg.zgshop.front.controller;

import com.github.pagehelper.PageInfo;
import com.zg.zgshop.common.constant.ProductConstant;
import com.zg.zgshop.common.util.ResponseResult;
import com.zg.zgshop.pojo.Product;
import com.zg.zgshop.service.ProductService;
import com.zg.zgshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zg
 * @Date: 2019/3/15 15:08
 */
@Controller
@RequestMapping("/front/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/search")
    public String search(Model model) {
        List<Product> products = productService.findAll();
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        model.addAttribute("pageInfo", pageInfo);
        return "main";
    }

    /**
     * 查询未购买商品
     *
     * @return
     */
    @RequestMapping("/selectValidProduct")
    public String selectValidProduct(Model model) {
        List<Product> products = productService.findBySaleStatus(ProductConstant.UNSALE_STATUS);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        model.addAttribute("pageInfo", pageInfo);
        return "main";
    }

    /**
     * 查询已购买商品
     *
     * @return
     */
    @RequestMapping("/selectUnValidProduct")
    public String selectUnValidProduct(Model model) {
        List<Product> products = productService.findBySaleStatus(ProductConstant.SALE_STATUS);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        model.addAttribute("pageInfo", pageInfo);
        return "main";
    }

    @RequestMapping("/selectById")
    public String selectById(Integer id, Integer tag, Model model) {
        Product product = productService.selectById(id);
        Integer saleNum = shoppingCartService.selectSaleNumById(id);
        model.addAttribute("product", product);
        model.addAttribute("num", saleNum);
        //在商品管理系统中，点击商品名字，跳转到商品展示界面，
        // 但是之前使用的那个show页面包含了top，所以使用了一个不包含top的show界面
        if (tag != null && tag == 1) {
            return "smallShow";
        }
        return "show";
    }

    @RequestMapping("/addProduct2ShoppingCart")
    @ResponseBody
    public ResponseResult addProduct2ShoppingCart(Integer id, Integer num) {
        Product product = productService.selectById(id);
        shoppingCartService.insert(product, num);
        return ResponseResult.success();
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String, Object> checkName(String name) {
        Map<String, Object> map = new HashMap<>();
        if (productService.checkName(name) && (name.length() > 1 && name.length() <= 80)) { //不存在，可用
            map.put("valid", true);
        } else {
            map.put("valid", false);
            if (!productService.checkName(name)) {
                map.put("message", "商品（" + name + "）已存在");
            } else {
                map.put("message", "标题长度不在[2-80]字符内");
            }
        }
        return map;
    }

    @RequestMapping("/checkInfo")
    @ResponseBody
    public Map<String, Object> checkInfo(String info) {
        Map<String, Object> map = new HashMap<>();
        //判断长度是否符合2-1000内
        if (info.length() > 1 && info.length() <= 1000) {
            map.put("valid", true);
        } else {
            map.put("valid", false);
            map.put("message", "详情内容请控制在[2-1000]字符内");
        }
        return map;
    }


}
