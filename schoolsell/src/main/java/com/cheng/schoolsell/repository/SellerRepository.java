package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.BusinessInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: BinCher
 * Date: 2018-08-23
 * Time: 上午9:06
 */
public interface SellerRepository extends JpaRepository<BusinessInfo, String> {

    /**
     * 通过手机查询是否重复
     * @param phone
     * @return
     */
    Integer countBusinessInfoByBusinessPhone(String phone);

    /**
     * 使用手机号和密码查询商户
     * @param phone
     * @param pwd
     * @return
     */
    Optional<BusinessInfo> findByBusinessPhoneAndBusinessPwd(String phone, String pwd);

}
