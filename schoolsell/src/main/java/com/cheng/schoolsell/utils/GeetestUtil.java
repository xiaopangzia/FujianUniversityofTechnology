package com.cheng.schoolsell.utils;

import com.cheng.schoolsell.config.GeetestConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: cheng
 * @Date: 2018-11-28
 */
public class GeetestUtil {

    public static String startCaptcha(HttpServletRequest request) {
        GeetestLib gtSdk = new GeetestLib(
                GeetestConfig.getGeetest_id(),
                GeetestConfig.getGeetest_key(),
                GeetestConfig.isnewfailback());
        String resStr = "{}";
        String userid = "sell";

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        //网站用户id
        param.put("user_id", userid);
        // web:电脑上的浏览器；
        // h5:手机上的浏览器，
        // 包括移动应用内完全内置的web_view；
        // native：通过原生SDK植入APP应用的方式
        param.put("client_type", "unknown");
        //传输用户请求验证时所携带的IP
        param.put("ip_address", "unknown");

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);
        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        //将userid设置到session中
        request.getSession().setAttribute("userid", userid);

        resStr = gtSdk.getResponseStr();

        return resStr;
    }

    public static int verifyLogin(HttpServletRequest request) {
        GeetestLib gtSdk = new GeetestLib(
                GeetestConfig.getGeetest_id(),
                GeetestConfig.getGeetest_key(),
                GeetestConfig.isnewfailback());

        String challenge = request.getParameter(
                GeetestLib.fn_geetest_challenge);

        String validate = request.getParameter(
                GeetestLib.fn_geetest_validate);

        String seccode = request.getParameter(
                GeetestLib.fn_geetest_seccode);

        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession()
                .getAttribute(gtSdk.gtServerStatusSessionKey);
        //从session中获取userid
        String userid = (String) request.getSession()
                .getAttribute("userid");

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        //网站用户id
        param.put("user_id", userid);
        //web:电脑上的浏览器；
        // h5:手机上的浏览器，包括移动应用内完全内置的web_view；
        // native：通过原生SDK植入APP应用的方式
        param.put("client_type", "unknown");
        //传输用户请求验证时所携带的IP
        param.put("ip_address", "unknown");

        int gtResult = 0;
        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(
                    challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(
                    challenge, validate, seccode);
        }

        return gtResult;
    }

}
