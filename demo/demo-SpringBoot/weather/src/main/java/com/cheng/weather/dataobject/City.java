package com.cheng.weather.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * 城市实体类
 * Description:
 * @author cheng
 * Date: 2018-07-15
 * Time: 上午6:07
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class City {

    /** 城市id */
    @Id
    @Column(name = "cityId")
    private Integer cityid;

    /** 上级id */
    @Column(name = "parentId")
    private Integer parentid;

    /** 城市天气代号 */
    @Column(name = "cityCode")
    private String citycode;

    /** 城市 */
    private String city;

}

