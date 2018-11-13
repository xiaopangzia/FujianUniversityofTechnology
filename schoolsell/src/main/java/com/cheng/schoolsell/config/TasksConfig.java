package com.cheng.schoolsell.config;

import com.cheng.schoolsell.service.SaleService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-07
 * Time: 下午10:11
 */
@Component
@Slf4j
public class TasksConfig {

    @Autowired
    private SaleService saleService;

    /**
     * 定时任务
     * 每天一点统计昨日的商铺商品销量和用户消费情况
     * 秒 分钟 小时 日 月 星期 年
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void taskShopSale() {
        Integer shopSize = saleService.saveShopOneDaySale(LocalDate.now().minusDays(1));
        Integer userSize = saleService.saveUserOneDaySale(LocalDate.now().minusDays(1));

        log.info("shopSale.size={}",shopSize);
        log.info("userSale.size={}", userSize);

    }

}
