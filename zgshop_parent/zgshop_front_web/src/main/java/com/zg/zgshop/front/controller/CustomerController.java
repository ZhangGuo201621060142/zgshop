package com.zg.zgshop.front.controller;

import com.zg.zgshop.common.exception.SysuserNotExistException;
import com.zg.zgshop.common.util.MD5Util;
import com.zg.zgshop.pojo.Sysuser;
import com.zg.zgshop.service.SysuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Author: zg
 * @Date: 2019/3/16 10:30
 */
@Controller
@RequestMapping("/front/customer")
public class CustomerController {

    @Autowired
    private SysuserService sysuserService;

    @RequestMapping("/login")
    public String login(String loginName, String password, HttpSession session, Model model) {
        session.removeAttribute("customer");
        if (loginName == null && password == null) {
            return "login";
        }
        //实现登录
        try {
            //MD5加密
            password = MD5Util.md5Encode(password);
            Sysuser customer = sysuserService.login(loginName, password);
            session.setAttribute("customer", customer);
            return "forward:../product/search";
        } catch (SysuserNotExistException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "login";
        }
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session) {
        //实现退出
        session.invalidate();
        return "login";
    }

}
