package com.zhiyou.service;

import java.sql.SQLException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.zhiyou.model.City;

/**
 * 单元测试工具
 *
 * @author jack
 *
 */
public class TestCityService {
    private CityService service;
    @Before
    public void init() {
        service = new CityService();
        System.out.println("初始化方法");
    }
    @Test
    public void test() {
        try {
            List<City> list = service.getAllCity();
            for (City c : list) {
                System.out.println(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("单元测试类");
    }
    @Test
    public void test02() {
        System.out.println("我是测试方法2");
    }
}

