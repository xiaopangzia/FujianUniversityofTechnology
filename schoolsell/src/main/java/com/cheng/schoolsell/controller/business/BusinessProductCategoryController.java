package com.cheng.schoolsell.controller.business;

import com.cheng.schoolsell.entity.BusinessInfo;
import com.cheng.schoolsell.entity.ProductCategory;
import com.cheng.schoolsell.enums.BusinessResultEnum;
import com.cheng.schoolsell.exception.BusinessException;
import com.cheng.schoolsell.form.ProductCategoryForm;
import com.cheng.schoolsell.service.ProductCategoryService;
import com.cheng.schoolsell.service.SellerService;
import com.cheng.schoolsell.utils.CookieUtil;
import com.cheng.schoolsell.utils.KeyUtil;
import com.cheng.schoolsell.utils.ResultBusinessMapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-27
 * Time: 上午9:49
 */
@Controller
@RequestMapping("/business/category")
@Slf4j
@Api(tags = "商户端商品分类管理")
public class BusinessProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private SellerService sellerService;

    @GetMapping("/list")
    @ApiOperation(value = "商品分类列表")
    public ModelAndView categoryList(Map<String, Object> map,
                                     HttpServletRequest request) {

        String id = CookieUtil.getSessionValue(request, "business");
        BusinessInfo businessInfo = sellerService.findById(id).get();

        List<ProductCategory> productCategoryList =
                productCategoryService.findShopAllProductCategory(businessInfo.getShopId());

        map.put("categoryList", productCategoryList);

        return new ModelAndView("business/category/list",map);
    }

    @GetMapping("/save")
    @ApiOperation(value = "跳转到添加分类页")
    public ModelAndView saveProductCategory(@RequestParam(value = "categoryId", required = false) String categoryId,
                                            Map<String, Object> map) {

        if (!StringUtils.isEmpty(categoryId)) {
            Optional<ProductCategory> productCategory =
                    productCategoryService.findProductCategoryById(categoryId);
            if (!productCategory.isPresent()){
                log.error("跳转到添加分类页:categoryId={}", categoryId);
                throw new BusinessException(BusinessResultEnum.BUSINESS_CATEGORY_EXIST);
            }
            map.put("category", productCategory.get());
        }
        return new ModelAndView("business/category/save",map);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存商品分类")
    public ModelAndView saveProductCategory(@Valid ProductCategoryForm productCategoryForm,
                                            BindingResult bindingResult,
                                            HttpServletRequest request) {

        ProductCategory productCategory = new ProductCategory();

        String id = CookieUtil.getSessionValue(request, "business");
        BusinessInfo businessInfo = sellerService.findById(id).get();

        String shopId = businessInfo.getShopId();

        if (StringUtils.isEmpty(productCategoryForm.getCategoryId())) {

            if (!productCategoryService.countProductCategoryByType(productCategoryForm.getCategoryType(),shopId)) {
                log.error("添加、修改商品分类信息：商品编号重复,categoryType={}",productCategoryForm.getCategoryType());
                throw new BusinessException(BusinessResultEnum.BUSINESS_CATEGORY_TYPE_REPEAT);
            }

            productCategoryForm.setCategoryId(KeyUtil.getUUID());

            if (bindingResult.hasFieldErrors()) {
                log.error("添加、修改商品分类信息：{}",bindingResult.getFieldError().getDefaultMessage());
                throw new BusinessException(BusinessResultEnum.BUSINESS_CATEGORY_INCOMPLETE);
            }

        }else {

            if (!productCategoryService.countProductCategoryByType(productCategoryForm.getCategoryType(),shopId)) {
                log.error("添加、修改商品分类信息：categoryType={}",productCategoryForm.getCategoryType());
                throw new BusinessException(
                        BusinessResultEnum.BUSINESS_CATEGORY_TYPE_REPEAT.getMessage(),
                        BusinessResultEnum.BUSINESS_CATEGORY_TYPE_REPEAT.getUrl()+
                                "?categoryId="+ productCategoryForm.getCategoryId());
            }

            if (bindingResult.hasFieldErrors()) {
                log.error("添加、修改商品分类信息：{}",bindingResult.getFieldError().getDefaultMessage());
                throw new BusinessException(
                        BusinessResultEnum.BUSINESS_CATEGORY_INCOMPLETE.getMessage(),
                        BusinessResultEnum.BUSINESS_CATEGORY_INCOMPLETE.getUrl()+
                                "?categoryId="+ productCategoryForm.getCategoryId());
            }
        }

        BeanUtils.copyProperties(productCategoryForm,productCategory);
        productCategory.setShopId(shopId);
        productCategoryService.saveProductCategory(productCategory);


        return new ModelAndView("business/common/success",
                ResultBusinessMapUtil.resultMap(BusinessResultEnum.BUSINESS_SAVE_SUCCESS));
    }

}
