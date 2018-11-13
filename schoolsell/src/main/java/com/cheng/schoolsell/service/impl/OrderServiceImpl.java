package com.cheng.schoolsell.service.impl;

import com.cheng.schoolsell.entity.*;
import com.cheng.schoolsell.form.UserOrderUpdateForm;
import com.cheng.schoolsell.repository.*;
import com.cheng.schoolsell.vo.BusinessOrderVO;
import com.google.common.collect.Lists;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.math.BigDecimal;
import java.util.function.Function;

import com.cheng.schoolsell.enums.UserResultVOEnum;
import com.cheng.schoolsell.exception.UserException;
import com.cheng.schoolsell.form.UserOrderDetailForm;
import com.cheng.schoolsell.service.OrderService;
import com.cheng.schoolsell.utils.KeyUtil;
import com.cheng.schoolsell.vo.OrderDetailVO;
import com.cheng.schoolsell.vo.OrderMasterVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-05
 * Time: 下午3:13
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository masterRepository;

    @Autowired
    private OrderDetailRepository detailRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String orderCreate(List<UserOrderDetailForm> userOrderDetailFormList,
                              String shopId, User user) {

        List<String> productIds = new ArrayList<>();
        for (UserOrderDetailForm userOrderDetailForm : userOrderDetailFormList) {
            productIds.add(userOrderDetailForm.getProductId());
        }
        List<ProductInfo> productInfoList = productInfoRepository.
                findProductInfosByProductIdInAndProductStatus(productIds, 1);

        String orderId = KeyUtil.genUniqueKey();
        String orderName = productInfoList.get(0).getProductName() + "等"
                + productInfoList.size() + "件商品";

        List<OrderDetail> detailList = new ArrayList<>();

        BigDecimal allAmount = new BigDecimal(0);
        allAmount.setScale(2);
        for (ProductInfo productInfo : productInfoList) {
            Integer quantity = 0;
            for (UserOrderDetailForm userOrderDetailForm : userOrderDetailFormList) {
                if (userOrderDetailForm.getProductId().equals(productInfo.getProductId())) {
                    quantity = userOrderDetailForm.getQuantity();
                }
            }

            BigDecimal amount = productInfo.getProductPrice().multiply(new BigDecimal(quantity));

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setShopId(shopId);
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(productInfo.getProductId());
            orderDetail.setProductName(productInfo.getProductName());
            orderDetail.setProductPrice(productInfo.getProductPrice());
            orderDetail.setProductQuantity(quantity);
            orderDetail.setProductImg(productInfo.getProductImg());
            orderDetail.setProductAmount(amount);
            allAmount = allAmount.add(amount);
            detailList.add(orderDetail);
        }

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setShopId(shopId);
        orderMaster.setOrderName(orderName);
        orderMaster.setUserId(user.getUserId());
        orderMaster.setUsername(user.getUsername());
        orderMaster.setPhone(user.getPhone());
        orderMaster.setAddress(user.getAddress());
        orderMaster.setOrderAmount(allAmount);
        orderMaster.setOrderStatus(0);
        orderMaster.setOrderMessage("");

        masterRepository.save(orderMaster);
        detailRepository.saveAll(detailList);

        return orderId;
    }

    @Override
    public Page<OrderMaster> getAllOrderMaster(String userId, Integer page) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page, 5, sort);
        return masterRepository.findByUserId(userId, pageable);
    }

    @Override
    public OrderMasterVO getOrderDetail(String orderId, String userId) {

        Optional<OrderMaster> orderMaster = masterRepository.findById(orderId);
        if (!orderMaster.isPresent()) {
            log.error("用户显示订单详情：订单不存在,orderId={}", orderId);
            throw new UserException(UserResultVOEnum.USER_ORDER_EXIST);
        }

        if (!orderMaster.get().getUserId().equals(userId)) {
            log.error("用户显示订单详情：订单不存在,orderId={}", orderId);
            throw new UserException(UserResultVOEnum.USER_ORDER_EXIST);
        }

        String shopId = orderMaster.get().getShopId();
        Shop shop = shopRepository.findById(shopId).get();

        OrderMasterVO orderMasterVO = new OrderMasterVO();
        BeanUtils.copyProperties(orderMaster.get(), orderMasterVO);
        orderMasterVO.setShopName(shop.getShopName());
        orderMasterVO.setShopImg(shop.getShopLogo());
        orderMasterVO.setShopPhone(shop.getShopPhone());

        List<OrderDetailVO> orderDetailVOS = new ArrayList<>();

        List<OrderDetail> orderDetails =
                detailRepository.findByOrderId(orderId);

        for (OrderDetail orderDetail : orderDetails) {
            OrderDetailVO orderDetailVO = new OrderDetailVO();
            BeanUtils.copyProperties(orderDetail, orderDetailVO);
            orderDetailVOS.add(orderDetailVO);
        }
        orderMasterVO.setOrderDetailVOs(orderDetailVOS);

        return orderMasterVO;
    }

    @Override
    public void updateOrderMsg(UserOrderUpdateForm userOrderUpdateForm, String orderId) {

        Optional<OrderMaster> orderMaster = masterRepository.findById(orderId);
        if (!orderMaster.isPresent()) {
            log.error("修改订单信息：订单不存在,orderId = {}", orderId);
            throw new UserException(UserResultVOEnum.USER_ORDER_EXIST);
        }

        orderMaster.get().setUsername(userOrderUpdateForm.getUsername());
        orderMaster.get().setPhone(userOrderUpdateForm.getPhone());
        orderMaster.get().setAddress(userOrderUpdateForm.getAddress());
        if (!StringUtils.isEmpty(userOrderUpdateForm.getOrderMessage())) {
            orderMaster.get().setOrderMessage(userOrderUpdateForm.getOrderMessage());
        } else {
            orderMaster.get().setOrderMessage("");
        }
        masterRepository.save(orderMaster.get());
    }

    @Override
    public void updateOrderStatus(String orderId, Integer code) {
        OrderMaster orderMaster = masterRepository.findById(orderId).get();
        Integer status = orderMaster.getOrderStatus();
        if (code.equals(status)) {
            log.error("修改订单状态：订单状态相同,不需要修改,code={}", code);
            throw new UserException(UserResultVOEnum.USER_STATUS_SAME);
        }

        if (status == 2) {
            log.error("修改订单状态：订单状态为已取消,无法修改,code={}", code);
            throw new UserException(UserResultVOEnum.USER_STATUS_IS_CANCEL);
        }

        if (status == 1 && code == 2) {
            log.error("修改订单状态：订单状态为已支付,用户无法取消,code={}", code);
            throw new UserException(UserResultVOEnum.USER_STATUS_NOT_CANCEL);
        }

        orderMaster.setOrderStatus(code);
        masterRepository.save(orderMaster);

    }

    @Override
    public Page<BusinessOrderVO> getAllOrderByShopAndPage(String shopId, Integer page) {

        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page - 1, 10, sort);

        Page<OrderMaster> orderMasterPage =
                masterRepository.findByShopId(shopId, pageable);

        List<OrderMaster> orderMasters = orderMasterPage.getContent();
        List<BusinessOrderVO> businessOrderVOS = new ArrayList<>();
        List<String> orderIds = new ArrayList<>();
        for (OrderMaster orderMaster : orderMasters) {
            orderIds.add(orderMaster.getOrderId());
            BusinessOrderVO businessOrderVO = new BusinessOrderVO();
            BeanUtils.copyProperties(orderMaster, businessOrderVO);
            businessOrderVOS.add(businessOrderVO);
        }

        List<OrderDetail> orderDetails = detailRepository.findByOrderIdIn(orderIds);

        for (BusinessOrderVO businessOrderVO : businessOrderVOS) {
            List<OrderDetailVO> orderDetailVOS = new ArrayList<>();
            for (OrderDetail orderDetail : orderDetails) {
                if (businessOrderVO.getOrderId().equals(orderDetail.getOrderId())) {
                    OrderDetailVO orderDetailVO = new OrderDetailVO();
                    BeanUtils.copyProperties(orderDetail, orderDetailVO);
                    orderDetailVOS.add(orderDetailVO);
                }
            }
            businessOrderVO.setOrderDetailVOS(orderDetailVOS);
        }

        Page<BusinessOrderVO> businessOrderVOPage = new Page<BusinessOrderVO>() {
            @Override
            public Iterator<BusinessOrderVO> iterator() {
                return null;
            }

            @Override
            public int getTotalPages() {
                return orderMasterPage.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return orderMasterPage.getTotalElements();
            }

            @Override
            public <U> Page<U> map(Function<? super BusinessOrderVO, ? extends U> function) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 10;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<BusinessOrderVO> getContent() {

                return businessOrderVOS;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }
        };
        return businessOrderVOPage;
    }

    @Override
    public void updateBusinessOrderStatus(String orderId) {
        OrderMaster orderMaster = masterRepository.findById(orderId).get();
        if (orderMaster.getOrderStatus() != 2) {
            orderMaster.setOrderStatus(2);
        }
        masterRepository.save(orderMaster);
    }

    @Override
    public Map<String, Object> getOrderNumDay(String shopId) {
        Map<String, Object> map = new HashMap<>(2);
        List<LocalDate> dates = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();

        String shopName = shopRepository.findById(shopId).get().getShopName();

        List<OrderMaster> orderMasters =
                masterRepository.
                        findByShopIdAndOrderStatusOrderByCreateTimeAsc(shopId, 1);

        if (orderMasters.size() == 0) {
            map.put("dates", null);
        } else {
            for (OrderMaster orderMaster : orderMasters) {
                Instant instant = orderMaster.getCreateTime().toInstant();
                ZoneId zoneId = ZoneId.systemDefault();
                LocalDate localDate = instant.atZone(zoneId).toLocalDate();

                if (!dates.contains(localDate)) {
                    dates.add(localDate);
                }
            }

            for (LocalDate date : dates) {
                Integer num = 0;
                for (OrderMaster orderMaster : orderMasters) {
                    Instant instant = orderMaster.getCreateTime().toInstant();
                    ZoneId zoneId = ZoneId.systemDefault();
                    LocalDate localDate = instant.atZone(zoneId).toLocalDate();
                    if (date.equals(localDate)) {
                        num++;
                    }
                }
                nums.add(num);
            }

            map.put("dates", dates);
            map.put("nums", nums);
        }
        map.put("shopName", shopName);

        return map;
    }

    @Override
    public Map<String, Object> getAdminIndexMsg() {

        Map<String, Object> map = new HashMap<>(5);
        // 获取用户数
        long user = userRepository.count();
        // 获取商铺数量
        long shop = shopRepository.count();
        map.put("user", user);
        map.put("shop", shop);

        LocalDate one = LocalDate.now().minusDays(7);
        LocalDate two = LocalDate.now();
        List<OrderMaster> orderMasters =
                masterRepository.countSeverDayOrder(one, two, 1);

        if (orderMasters.size() == 0) {
            map.put("dates", null);
        } else {
            List<String> dates = new ArrayList<>();
            List<BigDecimal> amounts = new ArrayList<>();
            List<Integer> nums = new ArrayList<>();
            for (OrderMaster orderMaster : orderMasters) {
                String t = orderMaster.getCreateTime().toString();
                String date = t.substring(0, t.lastIndexOf(" "));
                if (!dates.contains(date)) {
                    dates.add(date);
                }
            }

            for (String date : dates) {
                Integer num = 0;
                BigDecimal amount = new BigDecimal(0).setScale(2);
                for (OrderMaster orderMaster : orderMasters) {
                    String t = orderMaster.getCreateTime().toString();
                    String da = t.substring(0, t.lastIndexOf(" "));
                    if (date.equals(da)) {
                        num++;
                        amount = amount.add(orderMaster.getOrderAmount());
                    }
                }

                amounts.add(amount);
                nums.add(num);
            }

            map.put("dates", dates);
            map.put("amounts", amounts);
            map.put("nums", nums);

        }

        return map;
    }

}
