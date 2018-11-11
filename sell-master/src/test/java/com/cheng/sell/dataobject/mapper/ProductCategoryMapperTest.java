package com.cheng.sell.dataobject.mapper;

import com.cheng.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-14
 * Time: 上午8:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMapper() {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "师兄最不爱");
        map.put("category_type", 9);
        int result = mapper.insertByMapper(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject() {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("最爱");
        productCategory.setCategoryType(9);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory productCategory = mapper.findByCategoryType(1);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void updateByCategoryType() {
        int result = mapper.updateByCategoryType("最不爱", 10);
        Assert.assertEquals(1, result);
    }

    @Test
    public void updateByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("最爱");
        productCategory.setCategoryType(10);
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteByCategoryType() {
        int result = mapper.deleteByCategoryType(10);
        Assert.assertEquals(1, result);
    }
}
