package com.cheng.weather.service.impl;

import com.cheng.weather.dataobject.City;
import com.cheng.weather.dto.CityDTO;
import com.cheng.weather.repository.CityRepository;
import com.cheng.weather.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-15
 * Time: 上午6:23
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;


    @Override
    public List<City> saveAll(List<City> cityList) {
        return cityRepository.saveAll(cityList);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public List<CityDTO> findByParentId(Integer parentId) {
        List<CityDTO> cityDTOList = new ArrayList<CityDTO>();
        List<City> cityList = cityRepository.findByParentid(parentId);
        for (City city : cityList) {
            CityDTO cityDTO = new CityDTO();
            BeanUtils.copyProperties(city, cityDTO);
            cityDTOList.add(cityDTO);
        }
        return cityDTOList;
    }
}
