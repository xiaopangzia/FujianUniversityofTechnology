package com.cheng.schoolsell.controller.business;

import com.cheng.schoolsell.entity.ProductCategory;
import com.cheng.schoolsell.entity.ProductInfo;
import com.cheng.schoolsell.enums.BusinessResultEnum;
import com.cheng.schoolsell.exception.BusinessException;
import com.cheng.schoolsell.form.BusinessProductFrom;
import com.cheng.schoolsell.service.ProductCategoryService;
import com.cheng.schoolsell.service.ProductInfoService;
import com.cheng.schoolsell.service.SellerService;
import com.cheng.schoolsell.utils.CookieUtil;
import com.cheng.schoolsell.utils.ResultBusinessMapUtil;
import com.cheng.schoolsell.vo.BusinessProductInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-27
 * Time: 上午9:42
 */
@Controller
@RequestMapping("/business/product")
@Slf4j
@Api(tags = "商户端商品管理")
public class BusinessProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private SellerService sellerService;

    @GetMapping("/list")
    @ApiOperation(value = "查看商品列表")
    public ModelAndView productList(Map<String, Object> map,
                                    HttpServletRequest request,
                                    @RequestParam(value = "page",defaultValue = "1") Integer page) {

        String id = CookieUtil.getSessionValue(request, "business");
        String shopId = sellerService.findById(id).get().getShopId();

        /* 默认每页10条数据 */
        Pageable pageable = PageRequest.of(page-1, 10);
        Page<ProductInfo> productInfoList = productInfoService.findShopAllProduct(shopId, pageable);

        List<BusinessProductInfoVO> infoVOList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList.getContent()) {

            BusinessProductInfoVO infoVO = new BusinessProductInfoVO();

            String categoryName = productCategoryService.
                    findProductCategoryById(productInfo.getCategoryId()).get().getCategoryName();

            String statusName = null;
            switch (productInfo.getProductStatus()) {
                case 0:
                    statusName = "下架";
                    break;
                case 1:
                    statusName = "在售";
                    break;
                default:
                    break;
            }

            BeanUtils.copyProperties(productInfo, infoVO);

            infoVO.setCategoryName(categoryName);
            infoVO.setStatusName(statusName);

            infoVOList.add(infoVO);


        }

        map.put("voList", infoVOList);
        map.put("page", page);
        map.put("totals", productInfoList.getTotalPages());
        return new ModelAndView("business/product/list", map);
    }

    @GetMapping("/save")
    @ApiOperation(value = "跳转到保存商品信息页")
    public ModelAndView saveProduct(Map<String, Object> map,
                                    HttpServletRequest request,
                                    @RequestParam(value = "productId", required = false) String productId) {

        if (!StringUtils.isEmpty(productId)) {
            Optional<ProductInfo> productInfo = productInfoService.findById(productId);
            if (!productInfo.isPresent()) {
                log.error("跳转到保存商品信息页:productId={}", productId);
                throw new BusinessException(BusinessResultEnum.BUSINESS_PRODUCT_EXIST);
            }

            Optional<ProductCategory> productCategory =
                    productCategoryService.findProductCategoryById(productInfo.get().getCategoryId());
            map.put("product", productInfo.get());
            map.put("category", productCategory.get().getCategoryId());
        } else {
            map.put("category", null);
        }

        String id = CookieUtil.getSessionValue(request, "business");
        String shopId = sellerService.findById(id).get().getShopId();
        List<ProductCategory> productCategoryList = productCategoryService.findShopAllProductCategory(shopId);
        map.put("categoryList", productCategoryList);

        return new ModelAndView("business/product/save", map);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存商品信息")
    public ModelAndView saveProduct(@Valid BusinessProductFrom businessProductFrom,
                                    BindingResult bindingResult,
                                    HttpServletRequest request,
                                    Map<String, Object> map) {

        String msg = BusinessResultEnum.BUSINESS_PRODUCT_REPEAT.getMessage();
        String url = BusinessResultEnum.BUSINESS_PRODUCT_REPEAT.getUrl();

        if (StringUtils.isEmpty(businessProductFrom.getProductId())) {
            if (bindingResult.hasFieldErrors()) {
                log.error("保存商品信息:{}", bindingResult.getFieldError().getDefaultMessage());
                throw new BusinessException(msg, url);
            }
        } else {
            if (bindingResult.hasFieldErrors()) {
                log.error("保存商品信息:{}", bindingResult.getFieldError().getDefaultMessage());
                throw new BusinessException(msg,
                        url + "?productId=" + businessProductFrom.getProductId());
            }
        }

        productInfoService.saveProduct(businessProductFrom, request);

        return new ModelAndView("business/common/success",
                ResultBusinessMapUtil.resultMap(BusinessResultEnum.BUSINESS_SAVE_PRODUCT_SUCCESS));
    }

    @GetMapping("/img/{productId}")
    @ApiOperation(value = "跳转到修改图片页面")
    public ModelAndView updateBusinessInfoImg(@PathVariable("productId") String productId,
                                              @RequestParam("url") String url,
                                              Map<String, Object> map) {
        map.put("url", url);
        map.put("productId", productId);

        return new ModelAndView("business/product/updateImg", map);

    }

    @PostMapping("/img")
    @ApiOperation(value = "修改商品图片")
    public ModelAndView updateBusinessInfoImg(@RequestParam("productId") String productId,
                                              @RequestParam("productImg") MultipartFile productImg) {

        productInfoService.updateImg(productId, productImg);

        return new ModelAndView("business/common/success",
                ResultBusinessMapUtil.resultMap(BusinessResultEnum.BUSINESS_UPLOAD_IMG_SUCCESS));

    }

    @GetMapping("/status/{productId}")
    @ApiOperation(value = "商品上下架")
    @ResponseBody
    public Boolean updateStatus(@PathVariable("productId") String productId) {

        productInfoService.updateStatus(productId);

        return true;
    }

}
