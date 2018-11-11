package com.zhiyou.util;

import java.io.InputStream;
import java.util.Properties;
/**
 * 读取属性配置文件工具类
 * @author jack
 *
 */
public class PropUtil {

    private static Properties properties;

    static {
        try {
            // 创建一个属性类
            properties = new Properties();
            // 通过类加载器加载classes根目录下的jdbc.properties
            InputStream inStream = PropUtil.class.getClassLoader().getResourceAsStream("app.properties");
            properties.load(inStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 通过属性文件的key获取对应的value
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
