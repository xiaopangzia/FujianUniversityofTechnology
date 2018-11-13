package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-04
 * Time: 下午4:03
 */
public interface UserRepository extends JpaRepository<User,String> {

    /**
     * 查询是否手机号是否有重复
     * @param phone
     * @return
     */
    Integer countByPhone(String phone);

    /**
     * 通过手机号和密码查询用户信息
     * @param phone
     * @param password
     * @return
     */
    Optional<User> findUserByPhoneAndPassword(String phone, String password);

}
