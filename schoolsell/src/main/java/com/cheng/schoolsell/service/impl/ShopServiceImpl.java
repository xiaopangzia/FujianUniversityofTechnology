package com.cheng.schoolsell.service.impl;

import com.cheng.schoolsell.constant.ShopConstant;
import com.cheng.schoolsell.entity.Shop;
import com.cheng.schoolsell.enums.BusinessResultEnum;
import com.cheng.schoolsell.exception.BusinessException;
import com.cheng.schoolsell.form.BusinessShopForm;
import com.cheng.schoolsell.repository.ShopRepository;
import com.cheng.schoolsell.service.ShopService;
import com.cheng.schoolsell.utils.UploadUtil;
import com.cheng.schoolsell.vo.UserShopVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: BinCher
 * Date: 2018-08-23
 * Time: 上午9:16
 */
@Service
@Slf4j
public class ShopServiceImpl implements ShopService {

    @Autowired
    private UploadUtil uploadUtil;

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Optional<Shop> findById(String id) {
        return shopRepository.findById(id);
    }

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Override
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Boolean repeatByPhone(String phone) {
        Integer count = shopRepository.countShopByShopPhone(phone);
        if (count == 0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void updateShopStatus(String shopId) {

        Optional<Shop> shop = shopRepository.findById(shopId);
        if (!shop.isPresent()) {
            log.error("修改商铺状态：商铺不存在,shopId={}",shopId);
            throw new BusinessException(BusinessResultEnum.BUSINESS_SHOP_EXIST);
        }

        if (0 == shop.get().getShopStatus()) {
            shop.get().setShopStatus(1);
        } else if (1 == shop.get().getShopStatus()) {
            shop.get().setShopStatus(0);
        }

        shopRepository.save(shop.get());

    }

    @Override
    public void updateShopMsg(BusinessShopForm businessShopForm,String shopId) {

        Optional<Shop> shop = shopRepository.findById(shopId);
        if (!shop.isPresent()) {
            log.error("修改商铺信息：商铺不存在,shopId={}", shopId);
            throw new BusinessException(BusinessResultEnum.BUSINESS_SHOP_EXIST);
        }

        String shopHour = String.join("-",
                businessShopForm.getStartTime(),
                businessShopForm.getEndTime());

        BeanUtils.copyProperties(businessShopForm, shop.get());
        shop.get().setShopHour(shopHour);
        shopRepository.save(shop.get());
    }

    @Override
    public void updateShopLogo(MultipartFile shopLogo, String shopId) {

        Optional<Shop> shop = shopRepository.findById(shopId);
        if (!shop.isPresent()) {
            log.error("修改商铺信息：商铺不存在,shopId={}", shopId);
            throw new BusinessException(BusinessResultEnum.BUSINESS_SHOP_EXIST);
        }
        String oldLogo = shop.get().getShopLogo();

        try {
            String img = uploadUtil.uploadFileNOS(shopLogo);
            shop.get().setShopLogo(img);
        } catch (IOException e) {
            log.error("修改商铺LOGO:图片上传失败={}",e.getMessage());
            throw new BusinessException("图片上传失败",
                    "/sell/business/shop/img/"+shopId);
        }

        shopRepository.save(shop.get());

        if (!oldLogo.equals(ShopConstant.SHOPIMG)){
            if (!uploadUtil.deleteFileNOS(oldLogo)) {
                log.info("修改商铺信息:未查到图片,name={}",oldLogo);
            }

        }

    }

    @Override
    public List<UserShopVO> getAllShop() {

        List<Shop> shopList = shopRepository.findAll();
        List<UserShopVO> userShopVOList = new ArrayList<>();
        for (Shop shop : shopList) {
            if (shop.getShopStatus() == 1) {
                UserShopVO userShopVO = new UserShopVO();
                BeanUtils.copyProperties(shop, userShopVO);
                userShopVOList.add(userShopVO);
            }
        }
        return userShopVOList;
    }

    @Override
    public List<UserShopVO> getShopByRegion(String regionId) {
        List<Shop> shopList =
                shopRepository.getShopByRegionIdAndShopStatus(regionId, 1);
        List<UserShopVO> userShopVOList = new ArrayList<>();
        for (Shop shop : shopList) {
            UserShopVO userShopVO = new UserShopVO();
            BeanUtils.copyProperties(shop, userShopVO);
            userShopVOList.add(userShopVO);
        }
        return userShopVOList;
    }

    @Override
    public List<UserShopVO> searchShopByShopName(String shopName) {
        List<Shop> shopList =
                shopRepository.getShopByShopNameLikeAndShopStatus("%"+shopName+"%",1);
        List<UserShopVO> userShopVOList = new ArrayList<>();
        for (Shop shop : shopList) {
            UserShopVO userShopVO = new UserShopVO();
            BeanUtils.copyProperties(shop,userShopVO);
            userShopVOList.add(userShopVO);
        }
        return userShopVOList;
    }

    @Override
    public List<Shop> getOrderMasterShop(List<String> shopIds) {
        return shopRepository.findByShopIdIn(shopIds);
    }
}
