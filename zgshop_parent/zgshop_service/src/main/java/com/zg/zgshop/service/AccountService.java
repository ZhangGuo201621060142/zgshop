package com.zg.zgshop.service;

import com.zg.zgshop.pojo.AccountItem;

import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/18 11:20
 */
public interface AccountService {
    List<AccountItem> selectAll();
}
