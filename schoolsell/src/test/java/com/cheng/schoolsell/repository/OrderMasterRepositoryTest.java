package com.cheng.schoolsell.repository;
import com.cheng.schoolsell.entity.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-05
 * Time: 下午5:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository masterRepository;

    @Test
    public void testJpa() {
        OrderMaster orderMaster = masterRepository.findById("1").get();
        orderMaster.setOrderName("123243");
        masterRepository.save(orderMaster);
    }

    @Test
    public void findByCreateTimeBetweenAndOrderStatus() {

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime oneZDT = LocalDate.now().minusDays(7).atStartOfDay(zoneId);
        ZonedDateTime twoZDT = LocalDate.now().atStartOfDay(zoneId);
        Date one = Date.from(oneZDT.toInstant());
        Date two = Date.from(twoZDT.toInstant());

        List<OrderMaster> orderMasters =
                masterRepository.
                        findByCreateTimeBetweenAndOrderStatus(one, two, 1);

        System.out.println(orderMasters);

    }

    @Test
    public void countSeverDayOrder() {

        LocalDate one = LocalDate.now().minusDays(7);
        LocalDate two = LocalDate.now();
        List<OrderMaster> orderMasters = masterRepository.countSeverDayOrder(one, two, 1);
        System.out.println(orderMasters);

    }
}
