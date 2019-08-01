package com.cheng.user.service;

import com.cheng.user.dataobject.UserInfo;

/**
 * @author cheng
 * @date 2019-07-27
 * @description
 */
public interface UserInfoService {

    /**
     * 通过openid查询用户
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);

}
