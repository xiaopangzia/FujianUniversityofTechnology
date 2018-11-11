package com.cheng.shop.admin.admin.service;

import com.cheng.shop.admin.admin.dao.AdminDao;
import com.cheng.shop.admin.admin.domain.Admin;

import java.sql.SQLException;

public class AdminService {
    private AdminDao adminDao = new AdminDao();

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    public Admin login(Admin admin) {
        try {
            return adminDao.login(admin.getAdminname(), admin.getAdminpwd());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
