package com.cheng.schoolsell.service;

import com.cheng.schoolsell.entity.Admin;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 下午9:38
 */
@Transactional(rollbackOn = RuntimeException.class)
public interface AdminService {

    /**
     * 管理员登录
     * @param adminUser
     * @param adminPwd
     * @return
     */
    Optional<Admin> adminLogin(String adminUser, String adminPwd);

    /**
     * 查询管理员信息
     * @param adminId
     * @return
     */
    Optional<Admin> findById(Integer adminId);

    /**
     * 保存管理员信息
     * @param admin
     */
    Admin save(Admin admin);

}
