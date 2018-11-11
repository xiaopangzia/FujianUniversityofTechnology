package com.cheng.sell.service;

import com.cheng.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 类目服务层
 * @author cheng
 * Date: 2018-07-01
 * Time: 下午6:38
 */
public interface CategoryService {

    /**
     * 按id查询一条类目信息
     * @param categoryId
     * @return ProductCategory
     */
    ProductCategory findById(Integer categoryId);

    /**
     * 按categoryType查询类目
     * @param categoryTypeList
     * @return List<ProductCategory>
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 查询所有类目
     * @return List<ProductCategory>
     */
    List<ProductCategory> findAll();

    /**
     * 插入,修改类目信息
     * @param productCategory
     * @return ProductCategory
     */
    ProductCategory save(ProductCategory productCategory);


}
