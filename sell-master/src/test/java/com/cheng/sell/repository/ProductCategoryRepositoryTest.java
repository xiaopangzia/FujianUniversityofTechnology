package com.cheng.sell.repository;

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
 * @author cheng
 * Description:
 * User: cheng
 * Date: 2018-07-01
 * Time: 下午3:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void test() {
        System.out.println(repository.findById(1).toString());
    }

    @Test
    public void test1() {
        ProductCategory productCategory = repository.findById(1).get();
        productCategory.setCategoryType(9);
        repository.save(productCategory);
    }

    @Test
    public void test2() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("12");
        productCategory.setCategoryType(2);
        repository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(2, 3, 4);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}
