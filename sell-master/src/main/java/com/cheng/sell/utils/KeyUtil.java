package com.cheng.sell.utils;

import java.util.Random;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * @author cheng
 * Date: 2018-07-03
 * Time: 上午8:27
 */
public class KeyUtil {


    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();

        /** 生成6位随机数 */
        Integer number = random.nextInt(900000) + 100000;

        /** 当前毫秒数 System.currentTimeMillis(); */

        return System.currentTimeMillis() + String.valueOf(number);

    }

    public static synchronized String getUUID() {
        return UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");

    }

}
