package com.cheng.schoolsell.service;

import com.cheng.schoolsell.entity.Shop;
import com.cheng.schoolsell.form.BusinessShopForm;
import com.cheng.schoolsell.vo.ProductVO;
import com.cheng.schoolsell.vo.UserShopVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: BinCher
 * Date: 2018-08-23
 * Time: 上午9:15
 */
@Transactional(rollbackOn = RuntimeException.class)
public interface ShopService {

    /**
     * 根据ID查询商铺
     *
     * @param id
     * @return
     */
    Optional<Shop> findById(String id);

    /**
     * 查询所有店铺
     *
     * @return
     */
    List<Shop> findAll();

    /**
     * 保存/更新商铺
     *
     * @param shop
     * @return
     */
    Shop save(Shop shop);

    /**
     * 手机号是否重复
     *
     * @param phone
     * @return
     */
    Boolean repeatByPhone(String phone);

    /**
     * 修改商店状态：营业、休息
     *
     * @param shopId
     */
    void updateShopStatus(String shopId);

    /**
     * 修改商铺的基本信息
     *
     * @param businessShopForm
     * @param shopId
     */
    void updateShopMsg(BusinessShopForm businessShopForm, String shopId);

    /**
     * 修改商铺图片
     *
     * @param shopLogo
     * @param shopId
     */
    void updateShopLogo(MultipartFile shopLogo, String shopId);

    /**
     * 用户端获取所有店铺
     *
     * @return
     */
    List<UserShopVO> getAllShop();

    /**
     * 通过区域分类获取商铺
     *
     * @param regionId
     * @return
     */
    List<UserShopVO> getShopByRegion(String regionId);

    /**
     * 模糊搜索商铺
     *
     * @param shopName
     * @return
     */
    List<UserShopVO> searchShopByShopName(String shopName);

    /**
     * 获取订单列表的商铺
     * @param shopIds
     * @return
     */
    List<Shop> getOrderMasterShop(List<String> shopIds);

}
