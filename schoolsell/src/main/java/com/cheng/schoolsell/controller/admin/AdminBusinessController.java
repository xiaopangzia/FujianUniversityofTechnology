package com.cheng.schoolsell.controller.admin;
import com.cheng.schoolsell.constant.ShopConstant;
import com.cheng.schoolsell.entity.BusinessInfo;
import com.cheng.schoolsell.entity.RegionCategory;
import com.cheng.schoolsell.entity.Shop;
import com.cheng.schoolsell.entity.ShopSale;
import com.cheng.schoolsell.enums.AdminResultEnum;
import com.cheng.schoolsell.exception.AdminException;
import com.cheng.schoolsell.form.AdminShopForm;
import com.cheng.schoolsell.service.*;
import com.cheng.schoolsell.utils.AdminResultMapUtil;
import com.cheng.schoolsell.utils.KeyUtil;
import com.cheng.schoolsell.utils.ResultVoUtil;
import com.cheng.schoolsell.vo.ProductVO;
import com.cheng.schoolsell.vo.ResultVO;
import com.cheng.schoolsell.vo.SellerVO;
import com.cheng.schoolsell.vo.ShopVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: BinCher
 * Date: 2018-08-22
 * Time: 上午11:31
 */
@Controller
@Slf4j
@RequestMapping("/admin/business")
@Api(tags = "管理员端商户管理")
public class AdminBusinessController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private RegionCategoryService regionCategoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/sellers")
    @ApiOperation(value = "查看商户列表")
    public ModelAndView sellerList(Map<String, Object> map) {

        List<SellerVO> sellerVOList = new ArrayList<>();
        List<BusinessInfo> businessInfoList = sellerService.findAll();
        for (BusinessInfo businessInfo : businessInfoList) {
            SellerVO sellerVO = new SellerVO();
            BeanUtils.copyProperties(businessInfo, sellerVO);

            String shopId = businessInfo.getShopId();
            if (shopId != null) {
                Optional<Shop> shop = shopService.findById(shopId);
                if (shop.isPresent()) {
                    String shopName = shop.get().getShopName();
                    sellerVO.setShopName(shopName);
                }
            }

            sellerVOList.add(sellerVO);
        }

        map.put("sellers", sellerVOList);
        return new ModelAndView("admin/business/sellerList",map);
    }

    @GetMapping("/saveSeller")
    @ApiOperation(value = "跳转到添加、修改商户信息页")
    public ModelAndView saveSeller() {
        return new ModelAndView("admin/business/saveSeller");
    }

    @PostMapping("/saveSeller")
    @ApiOperation(value = "添加、修改商户信息")
    public ModelAndView saveSeller(@RequestParam("businessName") String businessName,
                                   @RequestParam("businessPhone") String businessPhone,
                                   @RequestParam("token") String token,
                                   Map<String,Object> map) {
        if (StringUtils.isEmpty(businessName)) {
            throw new AdminException(AdminResultEnum.ADMIN_SELLER_NOT_EMPTY, token);
        }

        if (!sellerService.repeatByPhone(businessPhone)) {
            throw new AdminException(AdminResultEnum.ADMIN_SELLER_PHONE_REPEAT, token);
        }

        BusinessInfo businessInfo = new BusinessInfo();
        businessInfo.setBusinessId(KeyUtil.getUUID());
        businessInfo.setBusinessName(businessName);
        businessInfo.setBusinessPhone(businessPhone);
        businessInfo.setBusinessPwd(businessPhone);

        sellerService.save(businessInfo);

        return new ModelAndView("admin/common/success",
                AdminResultMapUtil.success(AdminResultEnum.ADMIN_SAVE_BUSINESS_SUCCESS,token));
    }



    @GetMapping("/shops")
    @ApiOperation(value = "查看商铺列表")
    public ModelAndView shopList(Map<String, Object> map) {

        List<ShopVO> shopVOList = new ArrayList<>();
        List<Shop> shopList = shopService.findAll();
        for (Shop shop : shopList) {
            ShopVO shopVO = new ShopVO();
            String regionId = shop.getRegionId();
            String businessId = shop.getBusinessId();
            BeanUtils.copyProperties(shop, shopVO);
            if (!StringUtils.isEmpty(regionId) && !StringUtils.isEmpty(businessId)) {
                String regionName = regionCategoryService.findOne(regionId).get().getRegionName();
                String businessName = sellerService.findById(businessId).get().getBusinessName();
                shopVO.setBusinessName(businessName);
                shopVO.setRegionName(regionName);
            }
            Integer status = shop.getShopStatus();
            switch (status) {
                case 0:
                    shopVO.setShopStatus("休息");
                    break;
                case 1:
                    shopVO.setShopStatus("营业中");
                    break;
                default:
                    break;
            }
            shopVOList.add(shopVO);
        }

        map.put("shops", shopVOList);

        return new ModelAndView("admin/business/shopList",map);
    }

    @GetMapping("/saveShop")
    @ApiOperation(value = "跳转到添加、修改商铺信息页")
    public ModelAndView saveShop(@RequestParam(value = "businessId",required = false) String businessId,
                                 @RequestParam("token") String token,
                                 Map<String,Object> map) {

        List<RegionCategory> regionCategoryList = regionCategoryService.findAll();
        if (!StringUtils.isEmpty(businessId)) {
            Optional<BusinessInfo> businessInfo = sellerService.findById(businessId);
            map.put("sellers", businessInfo.get());
        }else {
            List<BusinessInfo> businessInfoList = sellerService.findAll();

            for (int i = businessInfoList.size()-1; i >= 0; i--) {
                if (!StringUtils.isEmpty(businessInfoList.get(i).getShopId())) {
                    businessInfoList.remove(i);
                }
            }
            if (businessInfoList.size() == 0) {
                throw new AdminException(AdminResultEnum.ADMIN_SHOP_EMPTY, token);
            }
            map.put("sellers", businessInfoList);
        }
        map.put("regions", regionCategoryList);
        return new ModelAndView("admin/business/saveShop",map);
    }

    @PostMapping("/saveShop")
    @ApiOperation(value = "保存商铺信息")
    public ModelAndView saveShop(@Valid AdminShopForm adminShopForm,
                                 BindingResult bindingResult,
                                 @RequestParam("token") String token,
                                 Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            throw new AdminException(bindingResult.getFieldError().getDefaultMessage(),
                    "/sell/admin/business/sellers?token=",token);
        }

        if (!shopService.repeatByPhone(adminShopForm.getShopPhone())) {
            throw new AdminException(AdminResultEnum.ADMIN_SHOP_PHONE_REPEAT, token);
        }

        Shop shop = new Shop();
        BeanUtils.copyProperties(adminShopForm, shop);
        shop.setShopId(KeyUtil.getUUID());
        shop.setShopLogo(ShopConstant.SHOPIMG);
        shopService.save(shop);
        Optional<BusinessInfo> businessInfo = sellerService.findById(shop.getBusinessId());

        businessInfo.get().setShopId(shop.getShopId());
        sellerService.save(businessInfo.get());
        return new ModelAndView("admin/common/success",
                AdminResultMapUtil.success(AdminResultEnum.ADMIN_SAVE_SHOP_SUCCESS,token));
    }

    @GetMapping("/details/{shopId}")
    @ApiOperation(value = "查看商铺详细信息")
    public ModelAndView shopDetails(@PathVariable("shopId") String shopId,
                                    @RequestParam("token") String token) {

        if (StringUtils.isEmpty(shopId)) {
            throw new AdminException(AdminResultEnum.ADMIN_SHOP_EXIST, token);
        }

        Map<String,Object> map = orderService.getOrderNumDay(shopId);
        map.put("shopId", shopId);

        List<ShopSale> shopSales = saleService.getSaleForDay(shopId);

        List<LocalDate> dates = new ArrayList<>();
        List<BigDecimal> sales= new ArrayList<>();
        if (shopSales.size() == 0) {
            map.put("saleDates", null);
        }else {
            for (ShopSale shopSale : shopSales) {
                if (!dates.contains(shopSale.getSaleTime())) {
                    dates.add(shopSale.getSaleTime());
                }
            }

            for (LocalDate date : dates) {
                BigDecimal amount = new BigDecimal(0);
                for (ShopSale shopSale : shopSales) {
                    if (date.equals(shopSale.getSaleTime())) {
                        amount = amount.add(shopSale.getTurnover());
                    }
                }
                sales.add(amount);
            }
            map.put("saleDates", dates);
            map.put("sales", sales);
            List<ProductVO> productVOS =
                    productInfoService.getShopProduct(shopId);

            map.put("productVOS", productVOS);
        }

        return new ModelAndView("admin/business/shopDetails",map);
    }

    @GetMapping("/show/product/{productId}")
    @ApiOperation(value = "展示商品日销量")
    @ResponseBody
    public ResultVO getProductECharts(@PathVariable("productId") String productId) {
        List<ShopSale> shopSales = saleService.getShopSaleByProductId(productId);
        Map<String, Object> map = new HashMap<>(3);
        if (shopSales.size() == 0) {
            map.put("date", null);
        }else {
            List<LocalDate> dates = new ArrayList<>();
            List<BigDecimal> sales = new ArrayList<>();
            for (ShopSale shopSale : shopSales) {
                dates.add(shopSale.getSaleTime());
                sales.add(shopSale.getTurnover());
            }

            map.put("date", dates);
            map.put("sale",sales);
            map.put("name", shopSales.get(0).getProductName());
        }

        return ResultVoUtil.success(map);
    }

}
