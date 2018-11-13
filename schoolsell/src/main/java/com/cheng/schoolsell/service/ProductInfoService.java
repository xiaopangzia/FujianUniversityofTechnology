package com.cheng.schoolsell.service;

import com.cheng.schoolsell.entity.BusinessInfo;
import com.cheng.schoolsell.entity.ProductInfo;
import com.cheng.schoolsell.form.BusinessProductFrom;
import com.cheng.schoolsell.vo.ProductAllVO;
import com.cheng.schoolsell.vo.ProductVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-27
 * Time: 上午10:13
 */
@Transactional(rollbackOn = RuntimeException.class)
public interface ProductInfoService {

    /**
     * 查询一个店铺的所有商品
     * @param shopId
     * @param pageable
     * @return
     */
    Page<ProductInfo> findShopAllProduct(String shopId, Pageable pageable);

    /**
     * 通过id查询商品
     * @param productId
     * @return
     */
    Optional<ProductInfo> findById(String productId);

    /**
     * 新增、修改商品信息
     * @param businessProductFrom
     * @param request
     * @return
     */
    ProductInfo saveProduct(BusinessProductFrom businessProductFrom, HttpServletRequest request);

    /**
     * 修改商品状态
     * @param productId
     * @return
     */
    void updateStatus(String productId);

    /**
     * 修改商品图片
     * @param productId
     * @param productImg
     * @return
     */
    ProductInfo updateImg(String productId, MultipartFile productImg);

    /**
     * 获取商铺的所有商品和分类
     * @param shopId
     * @return
     */
    List<ProductAllVO> getShopProductAndCategory(String shopId);

    /**
     * 判断商品是否属于同个商铺的
     * @param productIds
     * @param shopId
     * @return
     */
    boolean getShopProductIs(List<String> productIds, String shopId);

    /**
     * 查询所有商铺商品
     * @param request
     * @return
     */
    List<ProductVO> getShopProduct(HttpServletRequest request);

    /**
     * 查询商铺的所有商品
     * @param shopId
     * @return
     */
    List<ProductVO> getShopProduct(String shopId);

}
