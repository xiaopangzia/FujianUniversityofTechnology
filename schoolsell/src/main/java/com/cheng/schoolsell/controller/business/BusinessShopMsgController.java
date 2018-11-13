package com.cheng.schoolsell.controller.business;

import com.cheng.schoolsell.entity.RegionCategory;
import com.cheng.schoolsell.entity.Shop;
import com.cheng.schoolsell.enums.BusinessResultEnum;
import com.cheng.schoolsell.exception.BusinessException;
import com.cheng.schoolsell.form.BusinessShopForm;
import com.cheng.schoolsell.service.RegionCategoryService;
import com.cheng.schoolsell.service.SellerService;
import com.cheng.schoolsell.service.ShopService;
import com.cheng.schoolsell.utils.ResultBusinessMapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-08
 * Time: 下午4:04
 */
@RequestMapping("/business/shop")
@Controller
@Slf4j
@Api(tags = "商户端商铺信息操作")
public class BusinessShopMsgController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private RegionCategoryService regionCategoryService;

    @GetMapping("/status/{shopId}")
    @ApiOperation(value = "修改商铺状态")
    @CacheEvict(cacheNames = "regionShop",allEntries = true)
    public ModelAndView updateShopStatus(@PathVariable("shopId") String shopId) {

        shopService.updateShopStatus(shopId);
        return new ModelAndView("business/common/success",
                ResultBusinessMapUtil.resultMap(BusinessResultEnum.BUSINESS_UPDATE_SHOP_STATUS_SUCCESS));
    }

    @GetMapping("/msg/{shopId}")
    @ApiOperation(value = "查看商铺详细信息")
    public ModelAndView businessShopMsg(@PathVariable("shopId") String shopId,
                                        Map<String,Object> map) {

        Optional<Shop> shop = shopService.findById(shopId);
        List<RegionCategory> regionCategoryList = regionCategoryService.findAll();
        map.put("regionList", regionCategoryList);
        if (!StringUtils.isEmpty(shop.get().getShopHour())) {
            map.put("startTime", shop.get().getShopHour().substring(0, shop.get().getShopHour().lastIndexOf("-")));
            map.put("endTime", shop.get().getShopHour().substring(shop.get().getShopHour().lastIndexOf("-")+1));
        }
        map.put("shop", shop.get());
        return new ModelAndView("business/shop/info",map);
    }

    @PostMapping("/msg/{shopId}")
    @ApiOperation(value = "修改商铺信息")
    public ModelAndView updateShopMsg(@PathVariable("shopId") String shopId,
                                      @Valid BusinessShopForm businessShopForm,
                                      BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            log.error("修改商铺信息:修改信息出错");
            throw new BusinessException(
                    bindingResult.getFieldError().getDefaultMessage(),
                    "/sell/business/shop/msg/" + shopId);
        }

        shopService.updateShopMsg(businessShopForm, shopId);

        return new ModelAndView("business/common/success",
                ResultBusinessMapUtil.resultMapCode(BusinessResultEnum.BUSINESS_SAVE_SHOP_MSG_SUCCESS,shopId));
    }

    @PostMapping("/img/{shopId}")
    @ApiOperation(value = "修改商铺Logo")
    public ModelAndView updateShopImg(@PathVariable("shopId") String shopId,
                                      @RequestParam("shopLogo") MultipartFile shopLogo) {

        shopService.updateShopLogo(shopLogo,shopId);
        return new ModelAndView("business/common/success",
                ResultBusinessMapUtil.resultMapCode(BusinessResultEnum.BUSINESS_SAVE_SHOP_IMG_SUCCESS,shopId));
    }



}
