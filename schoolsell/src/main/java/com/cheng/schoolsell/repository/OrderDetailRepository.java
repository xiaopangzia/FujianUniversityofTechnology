package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-05
 * Time: 下午3:16
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /**
     * 订单号查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

    /**
     * 查询多个订单
     * @param orderIds
     * @return
     */
    List<OrderDetail> findByOrderIdIn(List<String> orderIds);

}
