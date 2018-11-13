package com.cheng.schoolsell.utils;

import com.cheng.schoolsell.enums.BusinessResultEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-24
 * Time: 下午8:39
 */
public class ResultBusinessMapUtil {

    public static Map<String, Object> resultMap(BusinessResultEnum businessResultEnum) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("url", businessResultEnum.getUrl());
        map.put("msg", businessResultEnum.getMessage());
        return map;
    }

    public static Map<String, Object> resultMapCode(BusinessResultEnum businessResultEnum,String code) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("url", businessResultEnum.getUrl()+code);
        map.put("msg", businessResultEnum.getMessage());
        return map;
    }



}
