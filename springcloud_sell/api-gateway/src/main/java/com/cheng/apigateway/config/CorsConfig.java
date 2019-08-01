package com.cheng.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * @author cheng
 * @date 2019-07-31
 * @description corss origin resource sharing 跨域资源共享
 * 配置跨域
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        /*设置cookie跨域*/
        config.setAllowCredentials(true);
        /*原始域 http://www.a.com a域名*/
        config.setAllowedOrigins(Collections.singletonList("*"));
        /*原始头*/
        config.setAllowedHeaders(Collections.singletonList("*"));
        /*get post等*/
        config.setAllowedMethods(Collections.singletonList("*"));
        /*缓存时间*/
        config.setMaxAge(300L);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
