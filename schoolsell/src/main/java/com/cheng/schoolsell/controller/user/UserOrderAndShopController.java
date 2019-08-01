package com.cheng.schoolsell.controller.user;
import com.cheng.schoolsell.entity.OrderMaster;
import com.cheng.schoolsell.entity.Shop;
import com.cheng.schoolsell.entity.User;
import com.cheng.schoolsell.enums.PaypalPaymentIntent;
import com.cheng.schoolsell.enums.PaypalPaymentMethod;
import com.cheng.schoolsell.enums.UserResultVOEnum;
import com.cheng.schoolsell.exception.UserException;
import com.cheng.schoolsell.form.UserOrderDetailForm;
import com.cheng.schoolsell.form.UserOrderUpdateForm;
import com.cheng.schoolsell.service.*;
import com.cheng.schoolsell.utils.CookieUtil;
import com.cheng.schoolsell.utils.ResultVoUtil;
import com.cheng.schoolsell.utils.WebSocketUtil;
import com.cheng.schoolsell.vo.OrderMasterAllVO;
import com.cheng.schoolsell.vo.OrderMasterVO;
import com.cheng.schoolsell.vo.ResultVO;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-22
 * Time: 上午10:38
 */
@Controller
@RequestMapping("/user/order")
@Api(tags = "用户订单相关操作")
@Slf4j
public class UserOrderAndShopController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private PaypalService paypalService;

    @GetMapping("/index")
    @ApiOperation(value = "跳转到订单页")
    public ModelAndView userOrder(HttpServletRequest request,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  Map<String, Object> map) {

        // 获取userId
        String userId = CookieUtil.getSessionValue(request, "user");

        if (page == null || page == 0) {
            page = 1;
        }

        Page<OrderMaster> orderMasterPage =
                orderService.getAllOrderMaster(userId, page - 1);
        Integer pageAmount = orderMasterPage.getTotalPages();

        List<OrderMaster> orderMasters = orderMasterPage.getContent();

        List<String> shopIds = new ArrayList<>();
        for (OrderMaster orderMaster : orderMasters) {
            shopIds.add(orderMaster.getShopId());
        }

        List<Shop> shopList = shopService.getOrderMasterShop(shopIds);

        List<OrderMasterAllVO> orderMasterAllVOS = new ArrayList<>();

        for (OrderMaster orderMaster : orderMasters) {
            for (Shop shop : shopList) {
                if (shop.getShopId().equals(orderMaster.getShopId())) {
                    OrderMasterAllVO orderMasterAllVO = new OrderMasterAllVO();
                    orderMasterAllVO.setOrderId(orderMaster.getOrderId());
                    orderMasterAllVO.setShopId(shop.getShopId());
                    orderMasterAllVO.setOrderName(orderMaster.getOrderName());
                    orderMasterAllVO.setShopName(shop.getShopName());
                    orderMasterAllVO.setShopImg(shop.getShopLogo());
                    orderMasterAllVO.setOrderStatus(orderMaster.getOrderStatus());
                    orderMasterAllVO.setOrderAmount(orderMaster.getOrderAmount());
                    orderMasterAllVO.setCreateTime(orderMaster.getCreateTime());
                    orderMasterAllVOS.add(orderMasterAllVO);
                }
            }

        }

        if (orderMasters.size() != 0) {
            map.put("voList", orderMasterAllVOS);
            map.put("page", page);
            map.put("total", pageAmount);
        } else {
            map.put("voList", null);
        }
        return new ModelAndView("user/orderMaster", map);
    }

    @PostMapping("/create/{shopId}")
    @ApiOperation(value = "创建订单")
    @ResponseBody
    public ResultVO orderCreate(@RequestBody List<UserOrderDetailForm> userOrderDetailFormList,
                                             @PathVariable("shopId") String shopId,
                                             HttpServletRequest request) {

        // 获取userId
        String userId = CookieUtil.getSessionValue(request, "user");

        List<String> productIds = new ArrayList<>();
        for (UserOrderDetailForm userOrderDetailForm : userOrderDetailFormList) {
            if (userOrderDetailForm.getProductId() == null) {
                log.error("创建订单:商品ID为空");
                throw new UserException(UserResultVOEnum.USER_PRODUCT_EXIST);
            }
            if (userOrderDetailForm.getQuantity() == null
                    || userOrderDetailForm.getQuantity() == 0) {
                log.error("创建订单:商品数量为空");
                throw new UserException(UserResultVOEnum.USER_PRODUCT_QUANTITY_ERROR);
            }
            productIds.add(userOrderDetailForm.getProductId());
        }

        if (!productInfoService.getShopProductIs(productIds, shopId)) {
            log.error("创建订单:商品错乱,不属于同一个商铺,productIds={}", productIds);
            throw new UserException(UserResultVOEnum.USER_ORDER_PRODUCT_ERROR);
        }

        User user = userService.findById(userId).get();

        if (StringUtils.isEmpty(user.getAddress())) {
            log.error("创建订单:用户地址为空");
            throw new UserException(UserResultVOEnum.USER_CREATE_ADDRESS_EXIST);
        }

        String orderId = orderService.
                orderCreate(userOrderDetailFormList, shopId, user);

        return ResultVoUtil.success(orderId);
    }

    @GetMapping("/detail/{orderId}")
    @ApiOperation(value = "去订单详情")
    public ModelAndView shopDetail(@PathVariable("orderId") String orderId,
                                   Map<String, Object> map) {
        map.put("orderId", orderId);
        return new ModelAndView("user/orderDetail", map);
    }


    @GetMapping("/getDetail/{orderId}")
    @ApiOperation(value = "订单详情")
    @ResponseBody
    public ResultVO shopGetDetail(HttpServletRequest request,
                                  @PathVariable("orderId") String orderId) {

        // 获取userId
        String userId = CookieUtil.getSessionValue(request, "user");

        OrderMasterVO orderMasterVO =
                orderService.getOrderDetail(orderId, userId);
        return ResultVoUtil.success(orderMasterVO);
    }

    @PostMapping("/update/{orderId}")
    @ApiOperation(value = "修改订单信息")
    @ResponseBody
    public ResultVO updateOrderMsg(@PathVariable("orderId") String orderId,
                                   @Valid UserOrderUpdateForm userOrderUpdateForm,
                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("修改订单信息:信息为空,error={}",
                    bindingResult.getFieldError().getDefaultMessage());
            throw new UserException(
                    UserResultVOEnum.USER_ORDER_UPDATE_MESSAGE_ERROR);
        }
        orderService.updateOrderMsg(userOrderUpdateForm, orderId);



        return ResultVoUtil.success(UserResultVOEnum.USER_ORDER_UPDATE_MESSAGE_SUCCESS);
    }

    @PostMapping("/status/{orderId}")
    @ApiOperation(value = "修改订单状态")
    @ResponseBody
    public ResultVO updateOrderStatus(@PathVariable("orderId") String orderId,
                                      @RequestParam("code") Integer code) {

        String shopId = orderService.updateOrderStatus(orderId, code,null);

        return ResultVoUtil.success(UserResultVOEnum.USER_ORDER_CANCEL_SUCCESS);

    }

    @GetMapping("/pay/{orderId}")
    public String payOrder(@PathVariable("orderId") String orderId,
                           HttpServletRequest request) {



        OrderMaster orderMaster = orderService.getOrderAmount(orderId);
        if (orderMaster.getOrderStatus().equals(0)) {

            //todo 线上和开发
            //开发环境
            String cancelUrl = "http://127.0.0.1/sell/user/order/detail/" + orderId;
            String successUrl = "http://127.0.0.1/sell/user/order/success/" + orderId;

            //线上环境
            /*String cancelUrl = "https://antice.top/sell/user/order/detail/" + orderId;
            String successUrl = "https://antice.top/sell/user/order/success/" + orderId;*/

            try {
                Payment payment = paypalService.createPayment(
                        orderMaster.getOrderAmount().doubleValue(),
                        "USD",
                        PaypalPaymentMethod.paypal,
                        PaypalPaymentIntent.sale,
                        "订单号:" + orderMaster.getOrderId()
                                +",总价:"+orderMaster.getOrderAmount()+"元",
                        cancelUrl,
                        successUrl);
                for(Links links : payment.getLinks()){
                    if(("approval_url").equals(links.getRel())){
                        return "redirect:" + links.getHref();
                    }
                }
            } catch (PayPalRESTException e) {
                log.error(e.getMessage());
            }
        }
        return "redirect:/user/order/cancel" + orderId;
    }

    @GetMapping("/success/{orderId}")
    public String successPay(@PathVariable("orderId") String orderId,
                             @RequestParam("paymentId") String paymentId,
                             @RequestParam("PayerID") String payerId) {
        
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (("approved").equals(payment.getState())) {
                String saleId = payment.getTransactions()
                        .get(0).getRelatedResources()
                        .get(0).getSale().getId();

                String shopId = orderService.updateOrderStatus(orderId, 1,saleId);
                WebSocketUtil.sendInfo("有新订单啦;订单号为："+orderId, shopId);
                return "redirect:/user/order/paySuccess/" + orderId;
            }
        } catch (PayPalRESTException e) {
            log.error("修改订单状态,msg={}",e.getMessage());
        } catch (IOException e) {
            log.error("修改订单状态,msg={}",e.getMessage());
        }
        return "redirect:/user/order/cancel/" + orderId;
    }

    @GetMapping("/paySuccess/{orderId}")
    public ModelAndView successShow(@PathVariable("orderId") String orderId,
                                    Map<String, Object> map) {
        map.put("code", 2);
        map.put("url", "/sell/user/order/detail/" + orderId);
        return new ModelAndView("user/orderMsg", map);
    }

    @GetMapping("/cancel/{orderId}")
    public ModelAndView orderCancel(@PathVariable("orderId") String orderId,
                                    Map<String,Object> map) {

        OrderMaster orderMaster = orderService.getOrderAmount(orderId);

        if (!orderMaster.getOrderStatus().equals(0)) {
            map.put("code", 0);
        }else{
            map.put("code", 1);
        }
        map.put("url", "/sell/user/order/detail/" + orderId);

        return new ModelAndView("user/orderMsg",map);
    }

}
