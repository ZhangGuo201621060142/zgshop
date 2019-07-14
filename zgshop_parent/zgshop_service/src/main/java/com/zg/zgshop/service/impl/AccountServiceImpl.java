package com.zg.zgshop.service.impl;

import com.zg.zgshop.dao.AccountDao;
import com.zg.zgshop.pojo.AccountItem;
import com.zg.zgshop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zg
 * @Date: 2019/3/18 11:21
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public List<AccountItem> selectAll() {
            return accountDao.selectAll();
    }

}
