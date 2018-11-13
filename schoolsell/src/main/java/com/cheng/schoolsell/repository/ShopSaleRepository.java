package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.ShopSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-07
 * Time: 下午10:23
 */
public interface ShopSaleRepository extends JpaRepository<ShopSale,String> {

    /**
     * 查询商品的营业信息
     * @param productId
     * @return
     */
    List<ShopSale> findByProductIdOrderBySaleNumAsc(String productId);

    /**
     * 查询商铺的营业信息
     * @param shopId
     * @return
     */
    List<ShopSale> findByShopIdOrderBySaleTimeAsc(String shopId);

}
