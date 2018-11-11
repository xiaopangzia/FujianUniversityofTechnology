package com.cheng.sell.repository;

import com.cheng.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 订单详情Dao
 * @author cheng
 * Date: 2018-07-02
 * Time: 上午8:30
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}
