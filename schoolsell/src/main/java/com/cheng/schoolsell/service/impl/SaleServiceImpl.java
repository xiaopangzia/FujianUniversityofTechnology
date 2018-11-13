package com.cheng.schoolsell.service.impl;
import java.math.BigDecimal;

import com.cheng.schoolsell.entity.*;
import com.cheng.schoolsell.repository.*;
import com.cheng.schoolsell.service.SaleService;
import com.cheng.schoolsell.utils.CookieUtil;
import com.cheng.schoolsell.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-08
 * Time: 下午3:43
 */
@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private OrderMasterRepository masterRepository;

    @Autowired
    private OrderDetailRepository detailRepository;

    @Autowired
    private ShopSaleRepository shopSaleRepository;

    @Autowired
    private UserSaleRepository userSaleRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Integer saveShopOneDaySale(LocalDate localDate) {

        List<OrderMaster> orderMasters =
                masterRepository.findByCreateTimeLike(localDate);

        List<String> orderIds = new ArrayList<>();
        for (OrderMaster orderMaster : orderMasters) {
            orderIds.add(orderMaster.getOrderId());
        }

        List<OrderDetail> orderDetails =
                detailRepository.findByOrderIdIn(orderIds);
        Map<Integer, String> map = new HashMap<>(16);
        Integer key = 0;

        List<ShopSale> shopSales = new ArrayList<>();

        for (OrderDetail orderDetail : orderDetails) {
            if (!map.containsValue(orderDetail.getProductId())) {
                map.put(key, orderDetail.getProductId());
                key++;

                ShopSale shopSale = new ShopSale();
                shopSale.setSaleId(KeyUtil.getUUID());
                shopSale.setShopId(orderDetail.getShopId());
                shopSale.setProductId(orderDetail.getProductId());
                shopSale.setProductName(orderDetail.getProductName());
                shopSale.setSaleNum(orderDetail.getProductQuantity());
                shopSale.setTurnover(orderDetail.getProductAmount());
                shopSale.setSaleTime(localDate);
                shopSales.add(shopSale);

            }else {

                for (ShopSale shopSale : shopSales) {
                    if (shopSale.getProductId().equals(orderDetail.getProductId())) {
                        shopSale.setSaleNum(shopSale.getSaleNum() + orderDetail.getProductQuantity());
                        shopSale.setTurnover(shopSale.getTurnover().add(orderDetail.getProductAmount()));
                    }
                }

            }
        }

        if (shopSales.size() != 0) {
            shopSaleRepository.saveAll(shopSales);
        }

        return shopSales.size();
    }

    @Override
    public Integer saveUserOneDaySale(LocalDate localDate) {
        List<OrderMaster> orderMasters =
                masterRepository.findByCreateTimeLike(localDate);

        List<UserSale> userSales = new ArrayList<>();

        Map<Integer, Object> map = new HashMap<>(16);
        Integer key = 0;

        for (OrderMaster orderMaster : orderMasters) {
            if (!map.containsValue(orderMaster.getUserId())) {
                map.put(key, orderMaster.getUserId());

                UserSale userSale = new UserSale();
                userSale.setSaleId(KeyUtil.getUUID());
                userSale.setUserId(orderMaster.getUserId());
                userSale.setUsername(orderMaster.getUsername());
                userSale.setTurnover(orderMaster.getOrderAmount());
                userSale.setSaleTime(localDate);
                userSale.setOrderNum(1);
                userSales.add(userSale);

                key++;
            }else {

                for (UserSale userSale : userSales) {
                    if (userSale.getUserId().equals(orderMaster.getUserId())) {
                        userSale.setTurnover(userSale.getTurnover().
                                add(orderMaster.getOrderAmount()));
                        userSale.setOrderNum(userSale.getOrderNum()+1);
                    }
                }

            }
        }

        userSaleRepository.saveAll(userSales);

        return userSales.size();
    }

    @Override
    public List<ShopSale> getShopSaleByProductId(String productId) {

        List<ShopSale> shopSales =
                shopSaleRepository.findByProductIdOrderBySaleNumAsc(productId);

        return shopSales;
    }

    @Override
    public List<ShopSale> getSaleForDay(String shopId) {

        if (StringUtils.isEmpty(shopId)) {
            return null;
        }

        return shopSaleRepository.findByShopIdOrderBySaleTimeAsc(shopId);

    }

    @Override
    public Map<String, Object> adminGetUserSale(String userId) {
        LocalDate one = LocalDate.now().minusDays(7);
        LocalDate two = LocalDate.now();
        List<UserSale> userSales =
                userSaleRepository
                        .findByUserIdAndSaleTimeBetween(userId, one, two);
        List<LocalDate> dates = new ArrayList<>();
        List<BigDecimal> amounts = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        for (UserSale userSale : userSales) {
            dates.add(userSale.getSaleTime());
            amounts.add(userSale.getTurnover());
            nums.add(userSale.getOrderNum());
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("dates", dates);
        map.put("amounts", amounts);
        map.put("nums", nums);
        return map;
    }
}
