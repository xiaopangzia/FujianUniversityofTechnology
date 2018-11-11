package com.cheng.sell.repository;

import com.cheng.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * @author cheng
 * Date: 2018-07-01
 * Time: 下午7:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(15.9));
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("好吃的皮蛋粥");
        productInfo.setProductIcon("http://xxxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setProductStock(3);
        repository.save(productInfo);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = repository.findByProductStatus(0);
        System.out.println(list.size());
        Assert.assertNotEquals(0,list.size());
    }
}
