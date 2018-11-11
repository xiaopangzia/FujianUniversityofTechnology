package com.cheng.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.MaxValidatorForNumber;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 商品信息
 * View Object 视图对象
 *
 * @author cheng
 * Date: 2018-07-01
 * Time: 下午9:06
 * vo：value object
 */
@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = -7822323890652952428L;

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;

}
