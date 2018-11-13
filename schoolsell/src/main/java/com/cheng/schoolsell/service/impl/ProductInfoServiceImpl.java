package com.cheng.schoolsell.service.impl;
import java.math.BigDecimal;
import com.cheng.schoolsell.constant.ShopConstant;
import com.cheng.schoolsell.entity.BusinessInfo;
import com.cheng.schoolsell.entity.ProductCategory;
import com.cheng.schoolsell.entity.ProductInfo;
import com.cheng.schoolsell.enums.BusinessResultEnum;
import com.cheng.schoolsell.exception.BusinessException;
import com.cheng.schoolsell.form.BusinessProductFrom;
import com.cheng.schoolsell.repository.ProductCategoryRepository;
import com.cheng.schoolsell.repository.ProductInfoRepository;
import com.cheng.schoolsell.repository.SellerRepository;
import com.cheng.schoolsell.repository.UserRepository;
import com.cheng.schoolsell.service.ProductInfoService;
import com.cheng.schoolsell.utils.CookieUtil;
import com.cheng.schoolsell.utils.KeyUtil;
import com.cheng.schoolsell.utils.UploadUtil;
import com.cheng.schoolsell.vo.ProductAllVO;
import com.cheng.schoolsell.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-27
 * Time: 上午10:15
 */
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private UploadUtil uploadUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<ProductInfo> findShopAllProduct(String shopId, Pageable pageable) {
        return productInfoRepository.findProductInfoByShopId(shopId,pageable);
    }

    @Override
    public Optional<ProductInfo> findById(String productId) {
        return productInfoRepository.findById(productId);
    }

    @Override
    public ProductInfo saveProduct(BusinessProductFrom businessProductFrom,
                                   HttpServletRequest request) {
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(businessProductFrom, productInfo);
        if (StringUtils.isEmpty(productInfo.getProductId())) {

            productInfo.setProductId(KeyUtil.getUUID());
            productInfo.setProductStatus(0);
            productInfo.setProductImg(ShopConstant.SHOPIMG);

        }

        String id = CookieUtil.getSessionValue(request, "business");
        String shopId = sellerRepository.findById(id).get().getShopId();
        productInfo.setShopId(shopId);

        Optional<ProductCategory> productCategory =
                productCategoryRepository.findById(businessProductFrom.getCategoryId());

        if (!productCategory.isPresent()) {
            log.error("保存商品信息：商品分类不存在={}",businessProductFrom.getCategoryId());
            throw new BusinessException(BusinessResultEnum.BUSINESS_CATEGORY_EXIST);
        }

        return productInfoRepository.save(productInfo);
    }

    @Override
    public void updateStatus(String productId) {

        Optional<ProductInfo> productInfo = productInfoRepository.findById(productId);
        if (!productInfo.isPresent()) {
            log.error("修改商品状态：商品不存在={}", productId);
            throw new BusinessException(BusinessResultEnum.BUSINESS_PRODUCT_EXIST);
        }

        Integer productStatus = productInfo.get().getProductStatus();

        productStatus = productStatus == 0 ? 1 : 0;

        productInfo.get().setProductStatus(productStatus);

        productInfoRepository.save(productInfo.get());
    }

    @Override
    public ProductInfo updateImg(String productId, MultipartFile productImg) {

        Optional<ProductInfo> productInfo = productInfoRepository.findById(productId);
        if (!productInfo.isPresent()) {
            log.error("修改商品图片：商品不存在,productId={}", productId);
            throw new BusinessException(BusinessResultEnum.BUSINESS_PRODUCT_EXIST);
        }

        String url = null;
        try {
            url = uploadUtil.uploadFileNOS(productImg);
        } catch (IOException e) {
            log.error("修改商品图片:图片上传失败={}",e.getMessage());
            throw new BusinessException("图片上传失败",
                    "/sell/business/product/img/"+productInfo.get().getProductId()+
                    "?url="+productInfo.get().getProductImg());
        }

        String oldImg = productInfo.get().getProductImg();

        productInfo.get().setProductImg(url);
        productInfoRepository.save(productInfo.get());
        if (!oldImg.equals(ShopConstant.SHOPIMG)){
            if (!uploadUtil.deleteFileNOS(oldImg)) {
                log.info("修改商品图片:未查到图片,name={}",oldImg);
            }
        }

        return productInfo.get();
    }

    @Override
    public List<ProductAllVO> getShopProductAndCategory(String shopId) {

        List<ProductAllVO> productAllVOList = new ArrayList<>();

        /**
         * 获取该商铺所有分类
         */
        List<ProductCategory> productCategoryList =
                productCategoryRepository.findProductCategoryByShopId(shopId);

        /**
         * 获取分类的List查询商品
         */
        List<String> categoryIdList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            categoryIdList.add(productCategory.getCategoryId());
            ProductAllVO productAllVO = new ProductAllVO();
            /**
             * 保存分类信息到返回List里
             */
            BeanUtils.copyProperties(productCategory, productAllVO);
            productAllVOList.add(productAllVO);
        }

        List<ProductInfo> productInfoList =
                productInfoRepository.findProductInfosByCategoryIdInAndProductStatus(
                        categoryIdList, 1);

        /*todo 待优化,减少list的循环*/
        for (ProductAllVO productAllVO : productAllVOList) {
            List<ProductVO> productVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productAllVO.getCategoryId()
                        .equals(productInfo.getCategoryId())) {
                    ProductVO productVO = new ProductVO();
                    BeanUtils.copyProperties(productInfo, productVO);
                    productVOList.add(productVO);
                }
            }
            productAllVO.setProductVOList(productVOList);
        }

        for (int i = productAllVOList.size() - 1; i >= 0; i--) {
            if (productAllVOList.get(i).getProductVOList().size() == 0) {
                productAllVOList.remove(i);
            }
        }

        return productAllVOList;

    }

    @Override
    public boolean getShopProductIs(List<String> productIds, String shopId) {

        //获取list里是否有相同的数据
        Integer count = Math.toIntExact(productIds.stream().distinct().count());
        Integer productNum =
                productInfoRepository.countProductInfosByShopIdAndProductIdIn(shopId, productIds);
        return productNum.equals(count);
    }

    @Override
    public List<ProductVO> getShopProduct(HttpServletRequest request) {
        String businessId = CookieUtil.getSessionValue(request, "business");
        BusinessInfo businessInfo = sellerRepository.findById(businessId).get();
        String shopId = businessInfo.getShopId();

        if (StringUtils.isEmpty(shopId)) {
            return null;
        }

        return getShopProduct(shopId);
    }

    @Override
    public List<ProductVO> getShopProduct(String shopId) {

        List<ProductInfo> productInfoList =
                productInfoRepository.findByShopId(shopId);

        List<ProductVO> productVOS = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            ProductVO productVO = new ProductVO();
            productVO.setProductId(productInfo.getProductId());
            productVO.setProductName(productInfo.getProductName());
            productVOS.add(productVO);
        }

        return productVOS;
    }

}
