package com.cheng.weather.service;

import com.cheng.weather.dataobject.City;
import com.cheng.weather.dto.CityDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-15
 * Time: 上午6:22
 */
public interface CityService {

    List<City> saveAll(List<City> cityList);

    List<City> findAll();

    List<CityDTO> findByParentId(Integer parentId);

}
