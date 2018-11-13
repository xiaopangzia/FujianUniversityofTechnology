package com.cheng.schoolsell.service;

import com.cheng.schoolsell.entity.ProductCategory;
import com.cheng.schoolsell.entity.ProductInfo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-27
 * Time: 上午10:09
 */
@Transactional(rollbackOn = RuntimeException.class)
public interface ProductCategoryService {

    /**
     * 查询一家商铺的所有分类
     * @param shopId
     * @return
     */
    List<ProductCategory> findShopAllProductCategory(String shopId);

    /**
     * 保存分类信息
     * @param productCategory
     * @return
     */
    ProductCategory saveProductCategory(ProductCategory productCategory);

    /**
     * 通过分类Id查询分类信息
     * @param categoryId
     * @return
     */
    Optional<ProductCategory> findProductCategoryById(String categoryId);

    /**
     * 通过商品分类编号查询是否有重复
     * @param categoryType
     * @param shopId
     * @return
     */
    Boolean countProductCategoryByType(Integer categoryType,String shopId);

}
