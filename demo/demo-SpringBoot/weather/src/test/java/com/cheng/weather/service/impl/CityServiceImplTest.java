package com.cheng.weather.service.impl;

import com.cheng.weather.dataobject.City;
import com.cheng.weather.dto.CityDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-15
 * Time: 下午12:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceImplTest {

    @Autowired
    private CityServiceImpl cityService;

    @Test
    public void findByParentId() {
        List<CityDTO> cityList = cityService.findByParentId(1);
        System.out.println(cityList.toString());
        Assert.assertNotEquals(0,cityList.size());

    }
}
