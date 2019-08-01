package com.cheng.schoolsell.service;

import com.cheng.schoolsell.entity.OrderMaster;
import com.cheng.schoolsell.entity.User;
import com.cheng.schoolsell.form.UserOrderDetailForm;
import com.cheng.schoolsell.form.UserOrderUpdateForm;
import com.cheng.schoolsell.vo.BusinessOrderVO;
import com.cheng.schoolsell.vo.OrderMasterVO;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-05
 * Time: 下午3:12
 */
@Transactional(rollbackOn = RuntimeException.class)
public interface OrderService  {

    /**
     * 创建订单
     * @param userOrderDetailFormList
     * @param shopId
     * @param user
     * @return
     */
    String orderCreate(List<UserOrderDetailForm> userOrderDetailFormList,
                                  String shopId, User user);

    /**
     * 用户分页查询所有订单
     * @param userId
     * @param page
     * @return
     */
    Page<OrderMaster> getAllOrderMaster(String userId,Integer page);

    /**
     * 获取订单详细信息
     *
     * @param orderId
     * @param userId
     * @return
     */
    OrderMasterVO getOrderDetail(String orderId, String userId);

    /**
     * 修改订单信息
     * @param userOrderUpdateForm
     * @param orderId
     */
    void updateOrderMsg(UserOrderUpdateForm userOrderUpdateForm,String orderId);

    /**
     * 修改订单的状态
     * @param orderId
     * @param code
     * @param saleId
     * @return
     */
    String updateOrderStatus(String orderId, Integer code,String saleId);

    /**
     * 商铺分页获取订单
     * @param shopId
     * @param page
     * @return
     */
    Page<BusinessOrderVO> getAllOrderByShopAndPage(String shopId, Integer page);

    /**
     * 商户修改订单状态
     * @param orderId
     * @throws PayPalRESTException
     */
    void updateBusinessOrderStatus(String orderId) throws PayPalRESTException;

    /**
     * 查询商铺订单
     * @param shopId
     * @return
     */
    Map<String, Object> getOrderNumDay(String shopId);

    /**
     * 获取管理员首页需要的图表信息
     * @return
     */
    Map<String, Object> getAdminIndexMsg();

    /**
     * 查询订单金额
     * @param orderId
     * @return
     */
    OrderMaster getOrderAmount(String orderId);
}
