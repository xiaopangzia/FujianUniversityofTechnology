package com.cheng.schoolsell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-26
 * Time: 上午9:24
 */
@Data
@Component
@ConfigurationProperties(prefix = "netease.nos")
public class NetEaseNosConfig {

    private String accessKey;

    private String secretKey;

    private String uploadUrl;

    private String bucket;

}
