package com.cheng.schoolsell.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-05
 * Time: 上午8:11
 */
public class ShopOrderSessionUtil {

    public void getRedis(HttpServletRequest request,
                         String userId) {

    }

    public void setRedis(HttpServletRequest request,
                         String userId,
                         Map<String,Object> map) {
        request.getSession().setAttribute(userId,map);
    }
}
