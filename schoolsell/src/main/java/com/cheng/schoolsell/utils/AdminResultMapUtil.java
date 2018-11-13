package com.cheng.schoolsell.utils;

import com.cheng.schoolsell.enums.AdminResultEnum;
import com.cheng.schoolsell.exception.AdminException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 下午8:57
 */
public class AdminResultMapUtil {

    public static Map<String, Object> success(AdminResultEnum adminResultEnum,String token) {
        Map<String, Object> map = new HashMap<>(2);
        if (token == null) {
            map.put("url", adminResultEnum.getUrl());
        }else {
            map.put("url", adminResultEnum.getUrl()+token);
        }
        map.put("msg", adminResultEnum.getMessage());
        return map;
    }

    public static Map<String, Object> success(AdminResultEnum adminResultEnum) {
        return success(adminResultEnum,null);
    }

    public static Map<String, Object> error(AdminException e) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("msg", e.getMessage());
        if (e.getToken() == null) {
            map.put("url", e.getUrl());
        }else {
            map.put("url", e.getUrl()+e.getToken());
        }
        return map;
    }

}
