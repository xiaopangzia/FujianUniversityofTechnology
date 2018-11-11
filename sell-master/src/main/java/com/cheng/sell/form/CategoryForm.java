package com.cheng.sell.form;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-11
 * Time: 上午10:53
 */
@Data
public class CategoryForm {

    /** 类目ID */
    private Integer categoryId;

    /** 类目名称 */
    private String categoryName;

    /** 类目编号 */
    private Integer categoryType;

}
