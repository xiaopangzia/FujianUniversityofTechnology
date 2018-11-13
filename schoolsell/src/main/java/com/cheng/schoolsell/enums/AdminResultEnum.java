package com.cheng.schoolsell.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 下午8:44
 */
@Getter
public enum AdminResultEnum {

    /**
     * message:登录失败，您的管理员名或密码错误
     * url:/sell/admin/login
     */
    ADMIN_LOGIN_ERROR("登录失败，您的管理员名或密码错误", "/sell/admin/login"),

    /**
     * message:登陆成功
     * url:/sell/admin/index
     */
    ADMIN_LOGIN_SUCCESS("登陆成功","/sell/admin/index?token="),

    /**
     * message:登录失败
     * url：/sell/admin/login
     */
    NAME_PASSWORD_EMPTY("登录失败,管理员名和密码都不能为空", "/sell/admin/login"),

    /**
     * message:管理员不存在
     * url:common/error
     */
    ADMIN_EXIST("管理员不存在", "/sell/admin/login"),

    /**
     * message:管理员退出登录成功
     * url:common/success
     */
    ADMIN_LOGOUT_SUCCESS("管理员退出登录成功", "/sell/admin/login"),

    /**
     * message:管理员未登录
     * url:/sell/admin/login
     */
    ADMIN_NOT_LOGIN("管理员未登录", "/sell/admin/login"),

    /**
     * message:区域分类为空
     * url:/sell/admin/region/list
     */
    ADMIN_REGION_EMPTY("区域分类不存在","/sell/admin/region/list?token="),

    /**
     * message：区域分类保存成功
     * url：/sell/admin/region/list
     */
    ADMIN_REGION_SUCCESS("区域分类保存成功", "/sell/admin/region/list?token="),

    /**
     * message：用户名不能为空
     * url：sell/admin/user/save
     */
    ADMIN_USER_USERNAME_EMPTY("用户名不能为空","/sell/admin/user/save?token="),

    /**
     * message：手机号不能为空
     * url：sell/admin/user/save
     */
    ADMIN_USER_PHONE_EMPTY("手机号不能为空","/sell/admin/user/save?token="),

    /**
     * message：手机号已存在
     * url：sell/admin/user/save
     */
    ADMIN_USER_PHONE_EXIST("手机号已存在","/sell/admin/user/save?token="),

    /**
     * message：新增用户保存成功
     * url：/sell/admin/user/list
     */
    ADMIN_USER_SUCCESS("新增用户保存成功", "/sell/admin/user/list?token="),

    /**
     * message:商家名不能为空
     * url:/sell/admin/business/saveSeller
     */
    ADMIN_SELLER_NOT_EMPTY("商家名不能为空","/sell/admin/business/saveSeller?token="),

    /**
     * message：添加商家成功
     * url：/sell/admin/business/sellers
     */
    ADMIN_SAVE_BUSINESS_SUCCESS("添加商家成功","/sell/admin/business/sellers?token="),

    /**
     * message：添加商铺成功
     * url：/sell/admin/business/shops
     */
    ADMIN_SAVE_SHOP_SUCCESS("添加商铺成功","/sell/admin/business/shops?token="),

    /**
     * message:该商铺不存在
     * url:/sell/admin/business/shops
     */
    ADMIN_SHOP_EXIST("该商铺不存在", "/sell/admin/business/shops?token="),

    /**
     * message：没有商家要注册，请先添加商家
     * url：/sell/admin/business/saveSeller
     */
    ADMIN_SHOP_EMPTY("没有商家要注册，请先添加商家","/sell/admin/business/saveSeller?token="),

    /**
     * message:excel文件不能为空
     * url:/sell/admin/user/saveList
     */
    ADMIN_IMPORT_EXCEL_EMPTY("excel文件不能为空","/sell/admin/user/saveList?token="),

    /**
     * message：商户手机号重复
     * url：/sell/admin/business/saveSeller
     */
    ADMIN_SELLER_PHONE_REPEAT("商户手机号重复", "/sell/admin/business/saveSeller?token="),

    /**
     * message:商铺手机号重复
     * url:/sell/admin/business/saveShop
     */
    ADMIN_SHOP_PHONE_REPEAT("商铺手机号重复", "/sell/admin/business/saveShop?token="),
    ;

    private String message;

    private String url;

    AdminResultEnum(String message, String url) {
        this.message = message;
        this.url = url;
    }

}
