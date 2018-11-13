package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-27
 * Time: 上午10:08
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {

    /**
     * 通过商铺Id查询所有该店铺的分类
     * @param shopId
     * @return
     */
    List<ProductCategory> findProductCategoryByShopId(String shopId);

    /**
     * 统计同个商铺商品分类的编号是否重复
     * @param categoryType
     * @param shopId
     * @return
     */
    Integer countProductCategoryByCategoryTypeAndShopId(Integer categoryType,String shopId);

}
