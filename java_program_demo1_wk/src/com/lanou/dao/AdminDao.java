package com.lanou.dao;

import com.lanou.entity.AdminDO;

public interface AdminDao {

    Number searchCountByUsernameAndPassword(AdminDO adminDO);
}
