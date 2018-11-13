package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: BinCher
 * Date: 2018-08-23
 * Time: 上午9:14
 */
public interface ShopRepository extends JpaRepository<Shop,String> {

    /**
     * 统计手机号是否有重复
     * @param phone
     * @return
     */
    Integer countShopByShopPhone(String phone);

    /**
     * 通过区域分类获取营业中的商铺
     * @param regionId
     * @param shopStatus
     * @return
     */
    List<Shop> getShopByRegionIdAndShopStatus(String regionId, Integer shopStatus);

    /**
     * 用商铺名模糊查询
     * @param shopName
     * @param shopStatus
     * @return
     */
    List<Shop> getShopByShopNameLikeAndShopStatus(String shopName,Integer shopStatus);

    /**
     * 获取订单列表的商铺信息
     * @param shopIds
     * @return
     */
    List<Shop> findByShopIdIn(List<String> shopIds);

}
