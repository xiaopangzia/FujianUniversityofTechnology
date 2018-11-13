package com.cheng.schoolsell.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-14
 * Time: 下午2:32
 */
@Getter
public enum BusinessResultEnum {

    /**
     * message：商户登陆成功
     * url：/sell/business/index
     */
    BUSINESS_LOGIN_SUCCESS("商户登陆成功", "/sell/business/index"),

    /**
     * message:登录失败，商户不存在
     * url:/sell/business/login
     */
    BUSINESS_LOGIN_ERROR_BY_EXIST("登录失败，商户不存在", "/sell/business/login"),

    /**
     * message:商户退出登录成功
     * url:/sell/business/login
     */
    BUSINESS_LOGOUT_SUCCESS("商户退出登录成功", "/sell/business/login"),

    /**
     * message:商户已登出，请重新登录
     * url:/sell/business/login
     */
    BUSINESS_LOGOUT("商户已登出，请重新登录", "/sell/business/login"),

    /**
     * message:商户名不能为空
     * url:/sell/business/updateInfo/1
     */
    BUSINESS_IS_EXIST("商户名不能为空", "/sell/business/updateInfo/1"),

    /**
     * message:商户修改名字成功
     * url:/sell/business/index
     */
    BUSINESS_UPDATE_NAME_SUCCESS("商户修改名字成功", "/sell/business/index"),

    /**
     * message:商户手机不能为空
     * url:/sell/business/updateInfo/2
     */
    BUSINESS_PHONE_EXIST("商户手机不能为空", "/sell/business/updateInfo/2"),

    /**
     * message:商户旧手机号错误
     * url:/sell/business/updateInfo/2
     */
    BUSINESS_OLD_PHONE_ERROR("商户旧手机号错误", "/sell/business/updateInfo/2"),

    /**
     * message:商户修改手机号成功
     * url:/sell/business/index
     */
    BUSINESS_UPDATE_PHONE_SUCCESS("商户修改手机号成功,请重新登录", "/sell/business/login"),

    /**
     * message:密码不能为空
     * url:/sell/business/updateInfo/3
     */
    BUSINESS_PWD_EXIST("密码不能为空","/sell/business/updateInfo/3"),

    /**
     * message:输入的两次新密码不一致
     * url:/sell/business/updateInfo/3
     */
    BUSINESS_TWO_PWD_DIFFERENT("输入的两次新密码不一致", "/sell/business/updateInfo/3"),

    /**
     * message:商户旧密码不正确
     * url:/sell/business/updateInfo/3
     */
    BUSINESS_PWD_ERROR("商户旧密码不正确", "/sell/business/updateInfo/3"),

    /**
     * message:商户修改密码成功
     * url:/sell/business/login
     */
    BUSINESS_UPDATE_PWD_SUCCESS("商户修改密码成功,请重新登录", "/sell/business/login"),

    /**
     * message:商品分类信息不完整
     * url:/sell/business/category/save
     */
    BUSINESS_CATEGORY_INCOMPLETE("商品分类信息不完整", "/sell/business/category/save"),

    /**
     * message:商品分类保存成功
     * url:/sell/business/category/list
     */
    BUSINESS_SAVE_SUCCESS("商品分类保存成功", "/sell/business/category/list"),

    /**
     * message:该商品分类不存在
     * url:/sell/business/category/list
     */
    BUSINESS_CATEGORY_EXIST("该商品分类不存在", "/sell/business/category/list"),

    /**
     * message:商品分类编号重复，请重新填写商品分类编号
     * url:/sell/business/category/save
     */
    BUSINESS_CATEGORY_TYPE_REPEAT("商品分类编号重复，请重新填写商品分类编号", "/sell/business/category/save"),

    /**
     * message:商品不存在
     * url:/sell/business/product/list
     */
    BUSINESS_PRODUCT_EXIST("商品不存在", "/sell/business/product/list"),

    /**
     * message:商品信息不完整
     * url:/sell/business/product/save
     */
    BUSINESS_PRODUCT_REPEAT("商品信息不完整", "/sell/business/product/save"),

    /**
     * message:保存商品信息成功
     * url:/sell/business/product/list
     */
    BUSINESS_SAVE_PRODUCT_SUCCESS("保存商品信息成功", "/sell/business/product/list"),

    /**
     * message:修改图片成功
     * url:/sell/business/product/list
     */
    BUSINESS_UPLOAD_IMG_SUCCESS("修改图片成功", "/sell/business/product/list"),

    /**
     * message:商铺不存在
     * url:/sell/business/index
     */
    BUSINESS_SHOP_EXIST("商铺不存在", "/sell/business/index"),

    /**
     * message:商铺修改状态成功
     * url:/sell/business/index
     */
    BUSINESS_UPDATE_SHOP_STATUS_SUCCESS("商铺修改状态成功", "/sell/business/index"),

    /**
     * message:商铺信息修改成功
     * url:/sell/business/msg
     */
    BUSINESS_SAVE_SHOP_MSG_SUCCESS("商铺信息修改成功", "/sell/business/shop/msg/"),

    /**
     * message:商铺信息修改成功
     * url:/sell/business/msg
     */
    BUSINESS_SAVE_SHOP_IMG_SUCCESS("商铺LOGO修改成功", "/sell/business/shop/msg/"),

    /**
     * message:您的商铺为注册,请联系管理员注册
     * url:/sell/business/index
     */
    BUSINESS_SHOP_EXIST_TO("您的商铺为注册,请联系管理员注册", "/sell/business/index"),
    ;

    private String message;

    private String url;

    BusinessResultEnum(String message, String url) {
        this.message = message;
        this.url = url;
    }

}
