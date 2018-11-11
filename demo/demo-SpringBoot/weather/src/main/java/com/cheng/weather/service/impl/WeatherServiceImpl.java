package com.cheng.weather.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cheng.weather.config.CityConfig;
import com.cheng.weather.dataobject.City;
import com.cheng.weather.dto.HourlyDTO;
import com.cheng.weather.dto.ResultDTO;
import com.cheng.weather.dto.WeatherDTO;
import com.cheng.weather.service.WeatherService;
import com.cheng.weather.util.HttpUtils;
import com.cheng.weather.vo.ResultVO;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-17
 * Time: 上午10:31
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private CityConfig cityConfig;

    @Override
    public ResultDTO getWeather(City city) {
        Map<String, String> header = new HashMap<String, String>();
        header.put("Authorization", "APPCODE " + cityConfig.getAppCode());
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("city", city.getCity());
        querys.put("citycode", city.getCitycode());
        querys.put("cityid", city.getCityid()+"");
        querys.put("ip", "");
        querys.put("location", "");
        try {
            HttpResponse response = HttpUtils.doGet(
                    cityConfig.getHost(),
                    cityConfig.getWeather(),
                    cityConfig.getMethod(),
                    header, querys);
            String entity = EntityUtils.toString(response.getEntity());
            ResultDTO resultDTO = JSON.parseObject(entity, ResultDTO.class);
            return resultDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
