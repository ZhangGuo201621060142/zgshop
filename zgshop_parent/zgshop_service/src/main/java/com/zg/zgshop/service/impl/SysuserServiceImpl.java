package com.zg.zgshop.service.impl;

import com.zg.zgshop.common.exception.SysuserNotExistException;
import com.zg.zgshop.dao.SysuerDao;
import com.zg.zgshop.pojo.Sysuser;
import com.zg.zgshop.service.SysuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zg
 * @Date: 2019/3/14 16:13
 */
@Service
public class SysuserServiceImpl implements SysuserService {

    @Autowired
    private SysuerDao sysuerDao;

    @Override
    public Sysuser login(String loginName, String password) throws SysuserNotExistException {
        Sysuser sysuser = sysuerDao.selectByNameAndPassword(loginName, password);
        if (sysuser != null) {
            return sysuser;
        }
        throw new SysuserNotExistException("用户名或密码错误");
    }
}
