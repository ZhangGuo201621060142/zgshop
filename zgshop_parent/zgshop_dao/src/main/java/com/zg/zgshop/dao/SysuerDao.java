package com.zg.zgshop.dao;

import com.zg.zgshop.pojo.Sysuser;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: zg
 * @Date: 2019/3/14 16:17
 */
public interface SysuerDao {
    Sysuser selectByNameAndPassword(@Param("loginName") String loginName, @Param("password") String password);
}
