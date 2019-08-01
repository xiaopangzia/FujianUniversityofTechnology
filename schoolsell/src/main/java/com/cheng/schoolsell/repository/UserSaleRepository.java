package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.UserSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-08
 * Time: 下午4:54
 */
public interface UserSaleRepository extends JpaRepository<UserSale,String> {

    /**
     * 获取用户区间内的消费情况
     * @param userId
     * @param one
     * @param two
     * @return
     */
    List<UserSale> findByUserIdAndSaleTimeBetweenOrderBySaleTimeAsc(String userId, LocalDate one,LocalDate two);
}
