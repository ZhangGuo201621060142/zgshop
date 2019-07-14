package com.zg.zgshop.front.controller;

import com.github.pagehelper.PageInfo;
import com.zg.zgshop.pojo.AccountItem;
import com.zg.zgshop.service.AccountService;
import com.zg.zgshop.service.OrderService;
import com.zg.zgshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/18 9:22
 */
@Controller
@RequestMapping("/front/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/selectAll")
    public String selectAll(Model model) {
        List<AccountItem> accountItem = accountService.selectAll();
        PageInfo<AccountItem> pageInfo = new PageInfo<>(accountItem);
        model.addAttribute("pageInfo", pageInfo);
        double total = 0;
        for (AccountItem item : accountItem) {
            total += item.getPrice() * item.getNum();
        }
        model.addAttribute("total", total);
        return "account";
    }

}
