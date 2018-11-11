package com.cheng.sell.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 异常代码,消息
 *
 * @author cheng
 * Date: 2018-07-03
 * Time: 上午8:08
 */
@Getter
public enum ResultEnum {

    /**
     * 0 状态码
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * 1 状态码
     * 参数不正确
     */
    PARAM_ERROR(1, "参数不正确"),

    /**
     * 10 状态码
     * 商品不存在
     */
    PRODUCT_NOT_EXIST(10, "商品不存在"),

    /**
     * 11 状态码
     * 库存不正确
     */
    PRODUCT_STOCK_ERROR(11, "库存不正确"),

    /**
     * 12 状态码
     * 订单不存在
     */
    ORDER_NOT_EXIST(12, "订单不存在"),

    /**
     * 13 状态码
     * 订单详情不存在
     */
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),

    /**
     * 14 状态码
     * 订单状态不正确
     */
    ORDER_STATUS_ERROR(14, "订单状态不正确"),

    /**
     * 15 状态码
     * 更新失败
     */
    ORDER_UPDATE_FAIL(15, "更新失败"),

    /**
     * 16 状态码
     * 订单详情为空
     */
    ORDER_DETAIL_EMPTY(16, "订单详情为空"),

    /**
     * 17 状态码
     * 订单支付状态不正确
     */
    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),

    /**
     * 18 状态码
     * 购物车不能为空
     */
    CART_EMPTY(18,"购物车不能为空"),

    /**
     * 19 状态码
     * 该订单不属于当前用户
     */
    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),

    /**
     * 20 状态码
     * 微信公众账号错误
     */
    WX_MP_ERROR(20, "微信公众账号错误"),

    /**
     * 21 状态码
     * 微信支付异步通知金额校验不通过
     */
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过"),

    /**
     * 22 状态码
     * 订单取消成功
     */
    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),

    /**
     * 23 状态码
     * 订单完结成功
     */
    ORDER_FINISH_SUCCESS(23,"订单完结成功"),

    /**
     * 24 状态码
     * 商品状态不正确
     */
    PRODUCT_STATUS_ERROR(24,"商品状态不正确"),

    /**
     * 25 状态码
     * 登录失败,登录信息不正确
     */
    LOGIN_FAIL(25, "登录失败,登录信息不正确"),

    /**
     * 26 状态码
     * 登出成功
     */
    LOGOUT_SUCCESS(26, "登出成功"),

    ;



    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
