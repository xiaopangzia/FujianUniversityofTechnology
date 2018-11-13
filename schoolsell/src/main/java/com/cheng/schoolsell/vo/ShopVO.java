package com.cheng.schoolsell.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: BinCher
 * Date: 2018-08-23
 * Time: 上午11:04
 */
@Data
public class ShopVO {

    private String shopId;

    /**
     * 店铺名
     */
    private String shopName;

    /**
     * 所属商家id
     */
    private String businessId;

    /**
     * 商家名
     */
    private String businessName;

    /**
     * 区域划分id
     */
    private String regionId;

    /**
     * 区域名
     */
    private String regionName;

    /**
     * 商店logo
     */
    private String shopLogo;


    /**
     * 商铺地址
     */
    private String shopAddr;

    /**
     * 商店电话
     */
    private String shopPhone;

    /**
     * 商铺状态
     * 0 休息中 默认
     * 1 营业中
     */
    private String shopStatus;

}
