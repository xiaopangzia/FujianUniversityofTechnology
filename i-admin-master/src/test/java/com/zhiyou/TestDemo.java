package com.zhiyou;

import org.junit.Test;

import java.util.UUID;

public class TestDemo {

    @Test
    public void test() {
        //获取时间戳
       /* for (int i = 0; i < 100; i++) {
            Long date=System.currentTimeMillis();
            System.out.println(date);
        }*/

       //产生uuid
        for (int i = 0; i < 100; i++) {
            String uuid=UUID.randomUUID().toString();
            String newUuid = uuid.replace("-", "");
            System.out.println(newUuid.toUpperCase());
        }

    }
}
