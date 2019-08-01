package com.cheng.schoolsell.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-05
 * Time: 上午10:26
 * 用户异常信息
 */
@Getter
public enum UserResultVOEnum {

    /**
     * code：1
     * message：用户不存在
     */
    USER_NO_EXIST(1,"用户不存在"),

    /**
     * code：2
     * message：手机号已存在
     */
    USER_PHONE_EXIST(2,"手机号已存在"),

    /**
     * code:3
     * message:表单校验失败
     */
    USER_VALID_ERROR(3, "表单校验失败"),

    /**
     * code：4
     * message：用户登录成功
     */
    USER_LOGIN_SUCCESS(4, "用户登陆成功"),

    /**
     * code:5
     * message:用户退出登录成功
     */
    USER_LOGOUT_SUCCESS(5, "用户退出登录成功"),

    /**
     * code:6
     * message:手机或密码为空
     */
    USER_PHONE_PASSWORD_NULL(6, "手机或密码为空"),

    /**
     * code:7
     * message:用户图片上传失败
     */
    USER_UPLOADIMG_ERROR(7, "用户图片上传失败"),

    /**
     * code:7
     * message:您还未登陆,请重新登录
     */
    USER_NOT_LOGIN(8, "您还未登陆,请重新登录"),

    /**
     * code:9
     * message:未发现商铺营业
     */
    USER_SHOP_EXIST(9,"未发现商铺营业"),

    /**
     * code:10
     * message:密码长度至少9位
     */
    USER_PWD_LENGTH_ERROR(10, "密码长度至少9位"),

    /**
     * code:11
     * message:新密码与确认密码不一致
     */
    USER_NEW_VERIFY_DIFFERENT(11,"新密码与确认密码不一致"),

    /**
     * code:12
     * message:原密码错误
     */
    USER_PWD_ERROR(12, "原密码错误"),

    /**
     * code:200
     * message:密码修改成功,请重新登录
     */
    USER_PWD_UPDATE_SUCCESS(200, "密码修改成功,请重新登录"),

    /**
     * code:200
     * message:手机号修改成功
     */
    USER_PHONE_UPDATE_SUCCESS(200, "手机号修改成功,请重新登录"),

    /**
     * code:200
     * message:用户修改用户名成功
     */
    USER_UPDATE_NAME_SUCCESS(200, "用户修改用户名成功"),

    /**
     * code:13
     * message:用户名未修改,请重新输入
     */
    USER_NAME_THE_SAME(13, "用户名未修改,请重新输入"),

    /**
     * code:14
     * message:用户名不能为空,请重新输入
     */
    USER_NAME_EXIST(14, "用户名不能为空,请重新输入"),

    /**
     * code:15
     * message:用户名不能为空,请重新输入
     */
    USER_UPDATE_PHONE_EXIST(15, "手机号不能为空,请重新输入"),

    /**
     * code:200
     * message:修改头像成功
     */
    USER_UPDATE_LOGO_SUCCESS(200, "修改头像成功"),

    /**
     * code:
     * message:地址不能为空
     */
    USER_ADDRESS_EXIST(15, "地址不能为空"),

    /**
     * code:200
     * message:修改地址成功
     */
    USER_UPDATE_ADDRESS_SUCCESS(200, "修改地址成功"),

    /**
     * code:16
     * message:该商铺未上架商品
     */
    USER_SHOP_PRODUCT_EXIST(16, "该商铺未上架商品"),

    /**
     * code:17
     * message:未发现商铺
     */
    USER_SHOP_EXIST_OR_UPDATE(17, "未发现商铺"),

    /**
     * code：18
     * message：选择的商品无效
     */
    USER_PRODUCT_EXIST(18, "选择的商品无效,请重新选择"),

    /**
     * code:19
     * message:商品数量错误,请重新添加商品
     */
    USER_PRODUCT_QUANTITY_ERROR(19, "商品数量错误,请重新添加商品"),

    /**
     * code:20
     * message:您选择的商品不存在或者未上架,请重新选择
     */
    USER_SELECT_PRODUCT_EXIST(20, "您选择的商品不存在或者未上架,请重新选择"),

    /**
     * code:21
     * message:选择商品错误
     */
    USER_ORDER_PRODUCT_ERROR(21, "选择商品错误"),

    /**
     * code:22
     * message:购物车操作错误，请重新操作
     */
    USER_CART_OPERATE_ERROR(22, "购物车操作错误，请重新操作"),

    /**
     * code:23
     * message:您查询的订单不存在,请重新下单
     */
    USER_ORDER_EXIST(23, "您查询的订单不存在,请重新下单"),

    /**
     * code:24
     * message:修改的信息无效,请重新填写
     */
    USER_ORDER_UPDATE_MESSAGE_ERROR(24, "修改的信息无效,请重新填写"),

    /**
     * code:200
     * message:修改信息成功
     */
    USER_ORDER_UPDATE_MESSAGE_SUCCESS(200, "修改信息成功"),

    /**
     * code:25
     * message:您已经是当前状态,不需要修改
     */
    USER_STATUS_SAME(25, "您已经是当前状态,不需要修改"),

    /**
     * code:26
     * message:订单已取消,无法修改
     */
    USER_STATUS_IS_CANCEL(26, "订单已取消,无法修改"),

    /**
     * code:27
     * message:订单已支付,您无法取消订单,请联系商家协商
     */
    USER_STATUS_NOT_CANCEL(27, "订单已支付,您无法取消订单,请联系商家协商"),

    /**
     * code:200
     * message:订单支付成功,稍后就能享受到美食了
     */
    USER_ORDER_PAY_SUCCESS(200, "订单支付成功,稍后就能享受到美食了"),

    /**
     * code:200
     * message:订单已取消
     */
    USER_ORDER_CANCEL_SUCCESS(200, "订单已取消"),

    /**
     * code:28
     * message:fail
     */
    USER_VERIFY_ERROR(28, "fail"),

    /**
     * code:29
     * message:您的地址为空，请去填写地址，再进行下单！
     */
    USER_CREATE_ADDRESS_EXIST(29, "您的地址为空，请去填写地址，再进行下单！"),

    ;

    private Integer code;

    private String message;

    UserResultVOEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
