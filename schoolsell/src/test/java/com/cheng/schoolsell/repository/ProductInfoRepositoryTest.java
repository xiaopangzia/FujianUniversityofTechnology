package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.ProductInfo;
import io.swagger.annotations.ApiOperation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-31
 * Time: 下午3:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findProductInfosByCategoryIdInAndProductStatus() {

        List<String> categoryIdList = Arrays.asList(
                "4667af06f15447638b5c7cb323995a9a",
                "666f4aa4ac8047338e95571d0a93cd6d");

        List<ProductInfo> productInfoList =
                productInfoRepository.findProductInfosByCategoryIdInAndProductStatus(
                        categoryIdList, 1);
        System.out.println(productInfoList);
        Assert.assertEquals(2, productInfoList.size());
    }

    @Test
    public void findProductInfosByProductIdInAndShopId() {
        List<String> productIds = Arrays.asList("2bb37009b6644577913fab615e94772e", "2bb37009b6644577913fab615e94772e");
        String shopId = "ae1ffee24d244066b5cb72224ed33fb5";
        Integer bool =
                productInfoRepository.countProductInfosByShopIdAndProductIdIn(shopId,productIds);
        System.out.println(bool);
    }
}
