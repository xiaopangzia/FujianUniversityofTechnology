package com.cheng.sell.repository;

import com.cheng.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 订单Dao
 * @author cheng
 * Date: 2018-07-02
 * Time: 上午8:27
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 查询当前页个人订单
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
