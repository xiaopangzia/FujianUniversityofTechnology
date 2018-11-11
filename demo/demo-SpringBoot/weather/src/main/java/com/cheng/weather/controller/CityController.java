package com.cheng.weather.controller;

import com.alibaba.fastjson.JSON;
import com.cheng.weather.dto.CityDTO;
import com.cheng.weather.util.ResultVOUtil;
import com.cheng.weather.vo.ResultVO;
import com.cheng.weather.config.CityConfig;

import com.cheng.weather.dataobject.City;
import com.cheng.weather.dto.GetCityDTO;
import com.cheng.weather.service.CityService;
import com.cheng.weather.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-15
 * Time: 上午7:51
 */
@RestController
@CrossOrigin
public class CityController {

    @Autowired
    private CityConfig cityConfig;

    @Autowired
    private CityService cityService;

    @GetMapping("/savecity")
    public Map<String,Object> saveCity() throws IOException {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + cityConfig.getAppCode());

        Map<String, String> querys = new HashMap<String, String>();
        try {
            HttpResponse response = HttpUtils.doGet(
                    cityConfig.getHost(),
                    cityConfig.getCity(),
                    cityConfig.getMethod(),
                    headers, querys);
            String object = EntityUtils.toString(response.getEntity());
            System.out.println(object);



             GetCityDTO getCityDTO = JSON.parseObject(object, GetCityDTO.class);

            List<City> list = getCityDTO.getResult();


            List<City> cityList = cityService.saveAll(list);
            Map<String, Object> map = new HashMap<>();
            map.put("list", cityList);
            return map;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @GetMapping("/city")
    public ResultVO city(@RequestParam(value = "pid",defaultValue = "0") Integer pid) {
        System.out.println(pid);
        List<CityDTO> cityList = cityService.findByParentId(pid);
        System.out.println(cityList.toString());
        return ResultVOUtil.success(cityList);
    }

}
