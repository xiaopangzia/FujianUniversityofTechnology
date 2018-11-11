package com.cheng.sell.service.impl;

import com.cheng.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-01
 * Time: 下午7:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findById() {
        ProductCategory productCategory = categoryService.findById(1);
        System.out.println(productCategory.toString());
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(2, 9);
        List<ProductCategory> result = categoryService.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result);
        System.out.println(result.size());
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = categoryService.findAll();
        for (ProductCategory productCategory : list) {
            System.out.println(productCategory.toString());
        }
        Assert.assertNotEquals(0,list);
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("男生专享", 4);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}
