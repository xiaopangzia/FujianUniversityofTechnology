package com.cheng.schoolsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-27
 * Time: 下午4:00
 */
@Data
public class ProductCategoryForm {

    /**
     * 商品分类ID
     */
    private String categoryId;

    /**
     * 分类名
     */
    @NotEmpty(message = "商品分类名不能为空")
    private String categoryName;

    /**
     * 分类编号
     */
    @NotNull(message = "商品编号不能为空")
    private Integer categoryType;

}
