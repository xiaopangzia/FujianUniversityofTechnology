package com.cheng.order.repository;


import com.cheng.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 廖师兄
 * 2017-12-10 16:12
 * @author cheng
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}
