package com.zhiyou.demo;

import java.io.InputStream;
import java.util.Properties;
/**
 * 读取配置文件的内容
 * @author jack
 *
 */
public class PropertiesDemo {

    public static void main(String[] args) throws Exception {

        Properties prop = new Properties();
        //将数据流加载到Properties类的实例中 多态
        //InputStream in = new FileInputStream("bin/jdbc.properties");
        //InputStream in = PropertiesDemo.class.getClassLoader().getResourceAsStream("jdbc.properties");
        InputStream in = PropertiesDemo.class.getClassLoader().getResourceAsStream("jdbc.properties");

        prop.load(in);
        in.close();
        //设置一个属性
          prop.setProperty("key1", "大家好");
          System.out.println(prop.getProperty("key1"));

          System.out.println(prop.getProperty("driverClassName"));
          System.out.println(prop.getProperty("url"));
          System.out.println(prop.getProperty("username"));
          System.out.println(prop.getProperty("password"));


    }

//    public static void main(String[] args) throws Exception {
//
//        Properties prop = new Properties();
//        //将数据流加载到Properties类的实例中 多态
//        //InputStream in = new FileInputStream("bin/jdbc.properties");
//        //InputStream in = PropertiesDemo.class.getClassLoader().getResourceAsStream("jdbc.properties");
//        InputStream in = PropertiesDemo.class.getClassLoader().getResourceAsStream("app.properties");
//
//        prop.load(in);
//        in.close();
//        //设置一个属性
//        prop.setProperty("key1", "大家好");
//        System.out.println(prop.getProperty("key1"));
//
//        System.out.println(prop.getProperty("alibaba.api.appcode"));
//        System.out.println(prop.getProperty("alibaba.api.weather.host"));
//        System.out.println(prop.getProperty("alibaba.api.weather.city"));
//        System.out.println(prop.getProperty("alibaba.api.weather.query.path"));
//
//
//    }
}

