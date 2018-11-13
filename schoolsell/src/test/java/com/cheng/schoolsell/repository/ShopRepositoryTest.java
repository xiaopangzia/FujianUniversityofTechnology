package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.Shop;
import org.junit.Assert;
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
 * Date: 2018-10-20
 * Time: 下午3:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopRepositoryTest {

    @Autowired
    private ShopRepository shopRepository;

    @Test
    public void countShopByShopPhone() {
    }

    @Test
    public void getShopByRegionIdAndShopStatus() {
    }

    @Test
    public void getShopByShopNameLike() {
        List<Shop> shopList = shopRepository.getShopByShopNameLikeAndShopStatus("%小%",1);
        Assert.assertEquals(2,shopList.size());
    }
}
