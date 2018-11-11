package com.cheng.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 商品（包含类目）
 * View Object 视图对象
 *
 * @author cheng
 * Date: 2018-07-01
 * Time: 下午9:01
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = -7594599455578746207L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
