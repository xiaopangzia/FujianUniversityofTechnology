package com.cheng.schoolsell.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: BinCher
 * Date: 2018-08-23
 * Time: 上午9:13
 */
@Data
public class SellerVO implements Serializable {

    private static final long serialVersionUID = 5573058088833758862L;

    private String businessId;

    /**
     * 商家名
     */
    private String businessName;

    /**
     * 商家手机号
     */
    private String businessPhone;

    /**
     * 商家的店铺id
     */
    private String shopId;

    /**
     * 所属商家的商铺名
     */
    private String shopName;
}
