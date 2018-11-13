package com.cheng.schoolsell.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-26
 * Time: 上午9:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QiNiuUploadConfigTest {

    @Autowired
    private QiNiuUploadConfig qiNiuUploadConfig;

    @Autowired
    private NetEaseNosConfig netEaseNosConfig;

    @Test
    public void getConfig(){

        System.out.println(qiNiuUploadConfig.getAccessKey());

        System.out.println(netEaseNosConfig.getAccessKey());

    }

}
