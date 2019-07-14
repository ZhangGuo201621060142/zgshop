package com.zg.zgshop.dao;

import com.zg.zgshop.pojo.AccountItem;

import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/18 11:22
 */
public interface AccountDao {
    List<AccountItem> selectAll();
}
