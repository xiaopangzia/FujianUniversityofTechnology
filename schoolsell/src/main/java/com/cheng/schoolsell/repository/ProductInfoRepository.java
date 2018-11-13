package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.BusinessInfo;
import com.cheng.schoolsell.entity.ProductCategory;
import com.cheng.schoolsell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-27
 * Time: 上午10:04
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 查询单个店铺的所有商品
     * @param shopId
     * @param pageable
     * @return
     */
    Page<ProductInfo> findProductInfoByShopId(String shopId, Pageable pageable);

    /**
     * 通过分类id,查询所有已上架商品
     * @param categoryIds
     * @param status
     * @return
     */
    List<ProductInfo> findProductInfosByCategoryIdInAndProductStatus(List<String> categoryIds, Integer status);

    /**
     * 通过商品id，查询多个商品
     * @param productIds
     * @return
     */
    List<ProductInfo> findProductInfosByProductIdInAndProductStatus(List<String> productIds,Integer status);

    /**
     * 查询商铺的所有商品
     * @param shopId
     * @param productIds
     * @return
     */
    Integer countProductInfosByShopIdAndProductIdIn(String shopId,List<String> productIds);

    /**
     * 通过商铺id查询所有商品
     * @param shopId
     * @return
     */
    List<ProductInfo> findByShopId(String shopId);
}
