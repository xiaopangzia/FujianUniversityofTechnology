package com.cheng.shop.admin.admin.dao;

import cn.itcast.jdbc.TxQueryRunner;
import com.cheng.shop.admin.admin.domain.Admin;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminDao {
    private QueryRunner queryRunner = new TxQueryRunner();

    /**
     * 管理员登录
     * @param adminname
     * @param adminpwd
     * @return
     * @throws SQLException
     */
    public Admin login(String adminname, String adminpwd) throws SQLException {
        String sql = "select * from t_admin where adminname=? and adminpwd=?";
        return queryRunner.query(sql, new BeanHandler<Admin>(Admin.class),adminname,adminpwd);
    }
}
