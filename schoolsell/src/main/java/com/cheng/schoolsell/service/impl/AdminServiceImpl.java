package com.cheng.schoolsell.service.impl;

import com.cheng.schoolsell.entity.Admin;
import com.cheng.schoolsell.repository.AdminRepository;
import com.cheng.schoolsell.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 下午9:41
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Optional<Admin> adminLogin(String adminUser, String adminPwd) {
        Optional<Admin> admin = adminRepository.findByAdminUserAndAdminPwd(adminUser, adminPwd);
        return admin;
    }

    @Override
    public Optional<Admin> findById(Integer adminId) {
        return adminRepository.findById(adminId);
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }
}
