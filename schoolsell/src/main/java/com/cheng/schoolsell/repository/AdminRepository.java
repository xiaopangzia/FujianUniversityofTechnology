package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 下午9:37
 */
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    /**
     *  通过管理员名和密码查询管理员
     * @param adminUser
     * @param adminPwd
     * @return
     */
    Optional<Admin> findByAdminUserAndAdminPwd(String adminUser, String adminPwd);

}
