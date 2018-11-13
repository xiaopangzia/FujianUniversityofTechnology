package com.cheng.schoolsell.service;

import com.cheng.schoolsell.entity.ProductInfo;
import com.cheng.schoolsell.vo.ProductAllVO;
import org.hibernate.validator.constraints.Range;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-31
 * Time: 下午7:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void getShopProductAndCategory() {

        List<ProductAllVO> productAllVOList = productInfoService
                .getShopProductAndCategory("ae1ffee24d244066b5cb72224ed33fb5");
        System.out.println(productAllVOList);

    }
}
