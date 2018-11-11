package com.cheng.sell.repository;

import com.cheng.sell.dataobject.OrderDetail;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-02
 * Time: 上午9:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567879");
        orderDetail.setOrderId("123123");
        orderDetail.setProductIcon("http://xxxxx.jpg");
        orderDetail.setProductId("12345");
        orderDetail.setProductName("瘦肉粥");
        orderDetail.setProductPrice(new BigDecimal(15.9));
        orderDetail.setProductQuantity(2);
        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> result = repository.findByOrderId("123123");
        Assert.assertNotEquals(0,result.size());
    }
}
