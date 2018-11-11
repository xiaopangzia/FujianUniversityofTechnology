package com.cheng.weather.repository;

import com.cheng.weather.dataobject.City;
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
 * Time: 上午9:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRepositoryTest {

    @Autowired
    private CityRepository repository;

    @Test
    public void findById() {
        City city = repository.findById(2).get();
        System.out.println(city.toString());
    }

    @Test
    public void save() {
        City city = new City(2,null,"222","广州");
        City city1 = repository.save(city);
        System.out.println(city1.toString());
    }


    @Test
    public void findByParentid() {

        List<City> cityList = repository.findByParentid(1);

        System.out.println(cityList.toArray());
        System.out.println(cityList.toString());

    }
}
