package com.zg.zgshop.front.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zg.zgshop.common.constant.PageinationConstant;
import com.zg.zgshop.common.util.ResponseResult;
import com.zg.zgshop.dto.ProductDto;
import com.zg.zgshop.front.vo.ProductVo;
import com.zg.zgshop.pojo.Product;
import com.zg.zgshop.pojo.ProductType;
import com.zg.zgshop.service.ProductService;
import com.zg.zgshop.service.ProductTypeService;
import com.zg.zgshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/19 9:57
 */
@Controller
@RequestMapping("/front/productManage")
public class ProductManageController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;


    @ModelAttribute("productTypes")
    public List<ProductType> loadProductTypes() {
        return productTypeService.findAll();
    }

    @RequestMapping("/showProductManageMain")
    public String showProductManageMain() {
        return "productManageMain";
    }

    @RequestMapping("/findAll")
    public String findAll(Integer pageNum, Model model) {
        //设置分页
        if (ObjectUtils.isEmpty(pageNum)) {
            pageNum = PageinationConstant.PAGE_NUM;
        }
        PageHelper.startPage(pageNum, PageinationConstant.PAGE_SIZE);
        //查找所有商品
        List<Product> products = productService.findAll();
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        model.addAttribute("pageInfo", pageInfo);

        return "productManager";
    }

    @RequestMapping("/add")
    public String add(ProductVo productVo, HttpSession session, Model model) {
        if (productService.findAll().size() == 1000) {
            model.addAttribute("errorMsg", "商品总数到达1000，不可再添加");
            return "forward:findAll";
        }
        try {
            String uploadPath = session.getServletContext().getRealPath("/upload");

            ProductDto productDto = new ProductDto();
            productDto.setName(productVo.getName());
            productDto.setPrice(productVo.getPrice());
            productDto.setInfo(productVo.getInfo());
            productDto.setProductTypeId(productVo.getProductTypeId());
            productDto.setInputStream(productVo.getFile().getInputStream());
            productDto.setFileName(productVo.getFile().getOriginalFilename());
            productDto.setUploadPath(uploadPath);

            productService.add(productDto);
            model.addAttribute("successMsg", "添加成功");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
        }
        return "forward:findAll";
    }

    @RequestMapping("/selectById")
    @ResponseBody
    public ResponseResult selectById(int id) {
        Product product = productService.selectById(id);
        return ResponseResult.success(product);
    }

    @RequestMapping("/getImage")
    public void getImage(String path, OutputStream outputStream, HttpSession session) {
        String uploadPath = session.getServletContext().getRealPath("/upload");
        path = uploadPath + "\\" + path;
        productService.getImage(path, outputStream);
    }

    @RequestMapping("/modify")
    public String modify(ProductVo productVo, HttpSession session, Model model, Integer pageNum) {
        try {
            String uploadPath = session.getServletContext().getRealPath("/upload");

            //可以用PropertyUtils.copyProperties赋值对象属性（commons-beanutils）
            ProductDto productDto = new ProductDto();
            productDto.setId(productVo.getId());
            productDto.setName(productVo.getName());
            productDto.setPrice(productVo.getPrice());
            productDto.setInfo(productVo.getInfo());
            productDto.setProductTypeId(productVo.getProductTypeId());
            productDto.setInputStream(productVo.getFile().getInputStream());
            productDto.setFileName(productVo.getFile().getOriginalFilename());
            productDto.setUploadPath(uploadPath);

            productService.modify(productDto);
            model.addAttribute("successMsg", "编辑成功");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
        }
        return "forward:findAll?pageNum=" + pageNum;
//        return "forward:findAll";
    }

    @RequestMapping("/removeById")
    @ResponseBody
    public ResponseResult removeById(Integer id) {
        productService.removeById(id);
        return ResponseResult.success();
    }

}
