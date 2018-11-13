package com.cheng.schoolsell.controller.business;

import com.cheng.schoolsell.entity.BusinessInfo;
import com.cheng.schoolsell.enums.BusinessResultEnum;
import com.cheng.schoolsell.exception.BusinessException;
import com.cheng.schoolsell.service.OrderService;
import com.cheng.schoolsell.service.SellerService;
import com.cheng.schoolsell.utils.CookieUtil;
import com.cheng.schoolsell.utils.ResultVoUtil;
import com.cheng.schoolsell.vo.BusinessOrderVO;
import com.cheng.schoolsell.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-08
 * Time: 下午4:04
 */
@RequestMapping("/business/order")
@Controller
@Slf4j
@Api(tags = "商户端订单相关操作")
public class BusinessShopOrderController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    @ApiOperation(value = "跳转到订单页")
    public ModelAndView getOrderList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                     Map<String,Object> map,
                                     HttpServletRequest request) {

        if (page == null || page == 0) {
            page = 1;
        }

        String businessId = CookieUtil.getSessionValue(request, "business");

        Optional<BusinessInfo> businessInfo = sellerService.findById(businessId);
        String shopId = businessInfo.get().getShopId();
        if (StringUtils.isEmpty(shopId)){
            log.error("跳转到订单页:商铺为注册");
            throw new BusinessException(BusinessResultEnum.BUSINESS_SHOP_EXIST_TO);
        }

        Page<BusinessOrderVO> businessOrderVOPage =
                orderService.getAllOrderByShopAndPage(shopId, page);

        map.put("page", page);
        map.put("totals", businessOrderVOPage.getTotalPages());
        map.put("businessVO", businessOrderVOPage.getContent());

        return new ModelAndView("business/order/list",map);
    }

    @GetMapping("/cancel/{orderId}")
    @ApiOperation(value = "商户取消订单")
    @ResponseBody
    public ResultVO orderCancel(@PathVariable("orderId") String orderId) {

        orderService.updateBusinessOrderStatus(orderId);

        return ResultVoUtil.success("取消订单成功");
    }

}
