package com.cheng.schoolsell.controller.user;

import com.cheng.schoolsell.entity.RegionCategory;
import com.cheng.schoolsell.entity.Shop;
import com.cheng.schoolsell.entity.User;
import com.cheng.schoolsell.enums.UserResultVOEnum;
import com.cheng.schoolsell.exception.UserException;
import com.cheng.schoolsell.service.*;
import com.cheng.schoolsell.utils.CookieUtil;
import com.cheng.schoolsell.utils.ResultVoUtil;
import com.cheng.schoolsell.vo.ProductAllVO;
import com.cheng.schoolsell.vo.ResultVO;
import com.cheng.schoolsell.vo.UserShopVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-19
 * Time: 下午3:27
 */
@Api(tags = "用户端未登录与登录用户公共控制器")
@RestController
@Slf4j
@RequestMapping("/user")
public class UserCommonController {

    @Autowired
    private RegionCategoryService regionCategoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductInfoService productInfoService;

    @ApiOperation(value = "获取所有区域分类")
    @GetMapping("/region")
    @Cacheable(cacheNames = "region")
    public ResultVO getAllRegion() {
        List<RegionCategory> regionCategoryList = regionCategoryService.findAll();

        for (RegionCategory regionCategory : regionCategoryList) {
            regionCategory.setRegionType(null);
        }
        return ResultVoUtil.success(regionCategoryList);

    }

    @ApiOperation(value = "获取所有店铺")
    @GetMapping("/all")
    public ResultVO getAllShop() {
        List<UserShopVO> userShopVOList = shopService.getAllShop();
        if (userShopVOList.size() == 0) {
            log.info("获取所有店铺:未发现营业中的商铺");
            throw new UserException(UserResultVOEnum.USER_SHOP_EXIST);
        }
        return ResultVoUtil.success(userShopVOList);
    }

    @ApiOperation(value = "通过区域分类获取所有店铺")
    @GetMapping("/region/{regionId}")
    @Cacheable(cacheNames = "regionShop",key = "#regionId")
    public ResultVO getShopByRegion(@PathVariable("regionId") String regionId) {
        List<UserShopVO> userShopVOList = shopService.getShopByRegion(regionId);
        if (userShopVOList.size() == 0) {
            log.info("获取所有店铺:未发现营业中的商铺");
            throw new UserException(UserResultVOEnum.USER_SHOP_EXIST);
        }
        return ResultVoUtil.success(userShopVOList);
    }

    @ApiOperation(value = "模糊搜索店铺")
    @GetMapping("/search/{shopName}")
    public ResultVO searchShop(@PathVariable("shopName") String shopName) {
        List<UserShopVO> userShopVOList = shopService.searchShopByShopName(shopName);
        if (userShopVOList.size() == 0) {
            log.info("获取所有店铺:未发现营业中的商铺");
            throw new UserException(UserResultVOEnum.USER_SHOP_EXIST);
        }
        return ResultVoUtil.success(userShopVOList);
    }

    @ApiOperation(value = "跳转到商铺点餐页面")
    @GetMapping("/shop/{shopId}")
    public ModelAndView getShop(@PathVariable("shopId") String shopId,
                                HttpServletRequest request,
                                Map<String,Object> map) {

        Cookie cookie = CookieUtil.get(request, "user");
        if (cookie != null && !"".equals(cookie.getValue())) {
            String key = cookie.getValue();
            String id = String.valueOf(request.getSession().getAttribute(key));
            Optional<User> user = userService.findById(id);
            if (user.isPresent()) {
                map.put("id", user.get().getUserId());
                map.put("name", user.get().getUsername());
                map.put("img", user.get().getUserImg());
            }
        }
        Optional<Shop> shop = shopService.findById(shopId);
        if (shop.isPresent()) {
            shop.get().setBusinessId("");
            shop.get().setRegionId("");
            map.put("shop", shop.get());
        }else {
            map.put("shop", null);
        }
        return new ModelAndView("user/shop",map);
    }

    @ApiOperation(value = "获取商铺的商品")
    @GetMapping("/shopMsg/{shopId}")
    @ResponseBody
    public ResultVO getShopMsg(@PathVariable("shopId") String shopId) {

        Optional<Shop> shop = shopService.findById(shopId);
        if (!shop.isPresent()||shop.get().getShopStatus()==0) {
            log.error("获取商铺的商品：商铺不存在,shopId={}",shopId);
            throw new UserException(UserResultVOEnum.USER_SHOP_EXIST_OR_UPDATE);
        }

        List<ProductAllVO> productAllVOList = productInfoService.getShopProductAndCategory(shopId);
        if (productAllVOList.size() == 0) {
            log.error("获取商铺的商品：该商铺没有美食productAllList.size()={}", productAllVOList.size());
            throw new UserException(UserResultVOEnum.USER_SHOP_PRODUCT_EXIST);
        }

        return ResultVoUtil.success(productAllVOList);
    }


}
