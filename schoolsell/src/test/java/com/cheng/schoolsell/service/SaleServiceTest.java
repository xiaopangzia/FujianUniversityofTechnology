package com.cheng.schoolsell.service;

import com.cheng.schoolsell.entity.ShopSale;
import com.cheng.schoolsell.service.SaleService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-08
 * Time: 下午3:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleServiceTest {

    @Autowired
    private SaleService saleService;

    @Test
    public void saveShopOneDaySale() {

        Integer size = saleService.saveShopOneDaySale(LocalDate.now());
    }

    @Test
    public void saveUserOneDaySale() {

        Integer size = saleService.saveUserOneDaySale(LocalDate.now().minusDays(1));
    }

    @Test
    public void getShopSaleByProductId() {
        List<ShopSale> shopSales =
                saleService.getShopSaleByProductId("2164776f008447168848ea51116af83c");
        System.out.println(shopSales);

    }
}
