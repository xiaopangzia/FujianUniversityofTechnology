package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-05
 * Time: 下午3:15
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    /**
     * 分页用户查询订单列表
     * @param userId
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByUserId(String userId, Pageable pageable);

    /**
     * 获取一天已支付的订单
     * 自定义注解
     * @nativeQuery=true开启原生sql
     * @param localDate
     * @return
     */
    @Query(nativeQuery = true,
            value = "select * from schoolsell.order_master " +
                    "where update_time like %?1% and order_status=1")
    List<OrderMaster> findByCreateTimeLike(LocalDate localDate);

    /**
     * 分页查询商铺订单列表
     * @param shopId
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByShopId(String shopId, Pageable pageable);

    /**
     * 查询商铺的订单
     * @param shopId
     * @param code
     * @return
     */
    List<OrderMaster> findByShopIdAndOrderStatusOrderByCreateTimeAsc(String shopId, Integer code);

    /**
     * 获取一个区间内的订单
     * @param one 开始时间
     * @param two 结束时间
     * @param status 订单状态
     * @return
     */
    List<OrderMaster> findByCreateTimeBetweenAndOrderStatus(Date one, Date two, Integer status);

    /**
     * 获取一个区间内的订单
     * @param one 开始时间
     * @param two 结束时间
     * @param status 订单状态
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM schoolsell.order_master where (create_time between ?1 and ?2) and order_master.order_status=?3")
    List<OrderMaster> countSeverDayOrder(LocalDate one, LocalDate two, Integer status);
}
