package com.cheng.schoolsell.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-31
 * Time: 下午3:19
 */
@Data
public class ProductAllVO implements Serializable {

    private static final long serialVersionUID = 8742048586663781724L;

    private String categoryId;

    private String categoryName;

    private Integer categoryType;

    private List<ProductVO> productVOList;

}
