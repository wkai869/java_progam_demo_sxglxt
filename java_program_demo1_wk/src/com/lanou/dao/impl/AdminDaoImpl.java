package com.lanou.dao.impl;

import com.lanou.dao.AdminDao;
import com.lanou.entity.AdminDO;
import com.lanou.util.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao{
    //定义QueryRunner,封装了jdbc，可以和数据库进行快速交互
    private QueryRunner queryRunner;

    public AdminDaoImpl() {
        queryRunner = new QueryRunner(DBUtil.source());
    }

    /**
     * 根据用户名和密码，查询用户信息
     * @param adminDO       用户名和密码
     * @return                      查询到几条用户信息
     */
    @Override
    public Number searchCountByUsernameAndPassword(AdminDO adminDO) {
        //根据用户名和密码向mysql发送sql语句执行，并且把执行结果返回
        try {
            return queryRunner.query(
                    "select count(*) from manager where username=? and password=?",
                    new ScalarHandler<Number>(),
                    adminDO.getUserName(),
                    adminDO.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
