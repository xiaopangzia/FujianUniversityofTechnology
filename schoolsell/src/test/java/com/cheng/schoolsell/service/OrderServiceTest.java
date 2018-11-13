package com.cheng.schoolsell.service;

import com.cheng.schoolsell.entity.ShopSale;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-08
 * Time: 下午3:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void getOrderNumDay() {
        Map<String,Object> map =
                orderService.getOrderNumDay("ae1ffee24d244066b5cb72224ed33fb5");
        System.out.println(map);
    }

    @Test
    public void getAdminIndexMsg() {

        Map<String, Object> map = orderService.getAdminIndexMsg();
        System.out.println(map);

    }
}
