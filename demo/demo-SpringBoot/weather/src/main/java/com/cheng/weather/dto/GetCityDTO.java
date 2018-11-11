package com.cheng.weather.dto;

import com.cheng.weather.dataobject.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-15
 * Time: 上午8:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCityDTO {

    private String status;

    private String msg;

    private List<City> result;

}
