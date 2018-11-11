package com.cheng.sell.service;

import com.cheng.sell.dataobject.ProductInfo;
import com.cheng.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 商品信息
 * @author cheng
 * Date: 2018-07-01
 * Time: 下午7:35
 */
@Service
public interface ProductService {

    /**
     * 查询一条商品信息
     * @param productId
     * @return ProductInfo
     */
    ProductInfo findById(String productId);

    /**
     * 分页查询所有商品信息
     * @param pageable
     * @return Page<ProductInfo>
     */
    Page<ProductInfo> findAll(Pageable pageable);


    /**
     * 查询所有在架商品信息
     * @return List<ProductInfo>
     */
    List<ProductInfo> findUpAll();

    /**
     * 添加,修改商品信息
     * @param productInfo
     * @return ProductInfo
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     * @param cartDTOList
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     * @param cartDTOList
     */
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 上架
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);

}
