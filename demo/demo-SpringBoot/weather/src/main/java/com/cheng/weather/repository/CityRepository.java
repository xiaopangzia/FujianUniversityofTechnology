package com.cheng.weather.repository;

import com.cheng.weather.dataobject.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * @author cheng
 * Date: 2018-07-15
 * Time: 上午6:12
 */
public interface CityRepository extends JpaRepository<City, Integer> {

    List<City> findByParentid(Integer parentId);

}
