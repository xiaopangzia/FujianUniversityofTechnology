package com.cheng.schoolsell.service;

import com.cheng.schoolsell.entity.BusinessInfo;
import com.cheng.schoolsell.form.BusinessForm;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * @author cheng
 * Description:
 * User: BinCher
 * Date: 2018-08-23
 * Time: 上午9:08
 */
@Transactional(rollbackOn = RuntimeException.class)
public interface SellerService {

    /**
     * 查询所有商家
     * @return
     */
    List<BusinessInfo> findAll();

    /**
     * 保存/修改商家信息
     * @param businessInfo
     * @return
     */
    BusinessInfo save(BusinessInfo businessInfo);

    /**
     * 通过ID查询商家
     * @param id
     * @return
     */
    Optional<BusinessInfo> findById(String id);

    /**
     * 手机号是否重复
     * @param phone
     * @return
     */
    Boolean repeatByPhone(String phone);

    /**
     * 用手机号和密码登录
     * @param businessForm
     * @return
     */
    Optional<BusinessInfo> businessLoginByPhoneAndPwd(BusinessForm businessForm);

}
