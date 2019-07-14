package com.zg.zgshop.front.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zg.zgshop.common.constant.PageinationConstant;
import com.zg.zgshop.common.constant.ResponseStatusConstant;
import com.zg.zgshop.common.exception.ProductTypeDeleteException;
import com.zg.zgshop.common.util.ResponseResult;
import com.zg.zgshop.pojo.ProductType;
import com.zg.zgshop.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/12 15:37
 */
@Controller
@RequestMapping("/front/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model) {
        //设置分页
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PageinationConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, PageinationConstant.PAGE_SIZE);
        //查找所有商品的类型
        List<ProductType> productTypes = productTypeService.findAll();
        PageInfo<ProductType> pageInfo = new PageInfo<>(productTypes);
        model.addAttribute("pageInfo", pageInfo);
        return "productTypeManager";
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(String name) {
        ResponseResult result = new ResponseResult();
        try {
            productTypeService.add(name);
            result.setStatus(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS);
            result.setMessage("添加成功");
        } catch (Exception e) {
            result.setStatus(ResponseStatusConstant.RESPONSE_STATUS_FAIL);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public ResponseResult findById(Integer id) {
        ProductType productType = productTypeService.findById(id);
        return ResponseResult.success(productType);
    }

    @RequestMapping("/modifyName")
    @ResponseBody
    public ResponseResult modifyName(Integer id, String name) {
        productTypeService.modifyName(id, name);
        return ResponseResult.success();
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public ResponseResult deleteById(Integer id) {
        try {
            productTypeService.deleteById(id);
            return ResponseResult.success();
        } catch (ProductTypeDeleteException e) {
            return ResponseResult.error(e.getMessage());
        }

    }

    @RequestMapping("/modifyStatus")
    @ResponseBody
    public ResponseResult modifyStatus(Integer id) {
        productTypeService.updateStatus(id);
        return ResponseResult.success();
    }

}
