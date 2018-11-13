package com.cheng.schoolsell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: cheng
 * Date: 2018-08-04
 * Time: 上午10：03
 */
@SpringBootApplication
@EnableCaching
@EnableRedisHttpSession
@EnableScheduling
public class SchoolsellApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolsellApplication.class, args);
    }
}
