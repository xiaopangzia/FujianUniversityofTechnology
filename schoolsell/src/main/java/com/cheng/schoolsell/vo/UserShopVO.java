package com.cheng.schoolsell.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-19
 * Time: 下午6:40
 */
@Data
public class UserShopVO implements Serializable {

    private static final long serialVersionUID = 8078191404738661771L;

    private String shopId;

    /**
     * 店铺名
     */
    private String shopName;

    /**
     * 商店logo
     */
    private String shopLogo;

    /**
     * 商店描述
     */
    private String shopDescribe;

    /**
     * 商铺状态
     * 0 休息中 默认
     * 1 营业中
     */
    private Integer shopStatus;


}
