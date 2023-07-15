package com.lanou.service.impl;

import com.lanou.dao.AdminDao;
import com.lanou.dao.impl.AdminDaoImpl;
import com.lanou.entity.AdminDO;
import com.lanou.service.AdminService;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao;

    public AdminServiceImpl() {
        //对dao对象进行实例化
        adminDao = new AdminDaoImpl();
    }

    /**
     * 登录业务判断方法
     * @param adminDO       用户名和密码
     * @return                          登录业务结果   true登录成功 ，false登录失败
     */
    @Override
    public boolean login(AdminDO adminDO) {
        //1、使用用户名和密码，进入dao进行数据库查询
        int count = adminDao.searchCountByUsernameAndPassword(adminDO).intValue();
        //2、根据dao返回结果，进行登录是否成功的判断
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
}
