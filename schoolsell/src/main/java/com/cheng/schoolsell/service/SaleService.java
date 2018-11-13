package com.cheng.schoolsell.service;

import com.cheng.schoolsell.entity.ShopSale;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-08
 * Time: 下午3:42
 */
@Transactional(rollbackOn = RuntimeException.class)
public interface SaleService {

    /**
     * 保存商铺一天的营业信息
     * @param localDate
     * @return
     */
    Integer saveShopOneDaySale(LocalDate localDate);

    /**
     * 保存用户一天的消费信息
     * @param localDate
     * @return
     */
    Integer saveUserOneDaySale(LocalDate localDate);

    /**
     * 查询商品的营业信息
     * @param productId
     * @return
     */
    List<ShopSale> getShopSaleByProductId(String productId);

    /**
     * 查询商铺的营业信息
     * @param shopId
     * @return
     */
    List<ShopSale> getSaleForDay(String shopId);

    /**
     * 获取用户的消费情况
     * @param userId
     * @return
     */
    Map<String, Object> adminGetUserSale(String userId);

}
