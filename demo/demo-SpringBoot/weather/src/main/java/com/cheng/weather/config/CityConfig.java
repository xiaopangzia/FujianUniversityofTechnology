package com.cheng.weather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 获取天气,城市配置类
 * @author cheng
 * Date: 2018-07-15
 * Time: 上午6:25
 */
@Component
@ConfigurationProperties(prefix = "alibaba.city")
@Data
public class CityConfig {

    private String host;

    private String city;

    private String method;

    private String appCode;

    private String weather;

}
