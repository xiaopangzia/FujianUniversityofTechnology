package com.cheng.schoolsell.repository;
import java.util.Date;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.cheng.schoolsell.entity.ShopSale;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-08
 * Time: 下午2:26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopSaleRepositoryTest {

    @Autowired
    private ShopSaleRepository shopSaleRepository;

    @Test
    public void save() {
        ShopSale shopSale = new ShopSale();
        shopSale.setSaleId("1");
        shopSale.setShopId("1");
        shopSale.setProductId("1");
        shopSale.setProductName("1");
        shopSale.setSaleNum(10);
        shopSale.setTurnover(new BigDecimal("0"));
        shopSale.setSaleTime(LocalDate.now());
        shopSaleRepository.save(shopSale);


    }

}
