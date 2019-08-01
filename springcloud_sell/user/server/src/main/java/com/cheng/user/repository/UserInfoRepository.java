package com.cheng.user.repository;

import com.cheng.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cheng
 * @date 2019-07-27
 * @description
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    /**
     * 通过openid查询用户
     * @param openid
     * @return UserInfo
     */
    UserInfo findByOpenid(String openid) ;

}
