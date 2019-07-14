package com.zg.zgshop.service;

import com.zg.zgshop.common.exception.SysuserNotExistException;
import com.zg.zgshop.pojo.Sysuser;

/**
 * @Author: zg
 * @Date: 2019/3/14 16:12
 */
public interface SysuserService {
    Sysuser login(String loginName, String password) throws SysuserNotExistException;
}
