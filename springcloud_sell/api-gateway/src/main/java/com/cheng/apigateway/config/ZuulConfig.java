package com.cheng.apigateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * zuul的动态配置注入
 * @Author: Cheng
 * @DATE: 2019-07-22
 */
@Component
public class ZuulConfig {

    @ConfigurationProperties("zuul")
    @RefreshScope
    private ZuulProperties zuulProperties(){
        return new ZuulProperties();
    }

}
