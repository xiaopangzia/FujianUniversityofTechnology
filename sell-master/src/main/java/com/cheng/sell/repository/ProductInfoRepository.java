package com.cheng.sell.repository;

import com.cheng.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 商品信息Dao层
 * @author cheng
 * Date: 2018-07-01
 * Time: 下午7:28
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    /**
     * 用商品状态查询商品信息
     * @param productStatus
     * @return List<ProductInfo>
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

}
