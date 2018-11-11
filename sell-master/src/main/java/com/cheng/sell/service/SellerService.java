package com.cheng.sell.service;

import com.cheng.sell.dataobject.SellerInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-12
 * Time: 上午11:16
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

}
