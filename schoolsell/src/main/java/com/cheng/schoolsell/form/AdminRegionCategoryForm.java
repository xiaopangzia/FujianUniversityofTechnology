package com.cheng.schoolsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-20
 * Time: 上午10:58
 */
@Data
public class AdminRegionCategoryForm {

    /**
     * 区域划分名
     */
    @NotEmpty(message = "区域名不能为空")
    private String regionName;

    /**
     * 区域划分编号
     */
    @NotNull(message = "区域编号不能为空")
    private Integer regionType;

    private String regionId;

    private String token;

}
