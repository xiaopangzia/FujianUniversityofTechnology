package com.cheng.schoolsell.service.impl;

import com.cheng.schoolsell.entity.BusinessInfo;
import com.cheng.schoolsell.form.BusinessForm;
import com.cheng.schoolsell.repository.SellerRepository;
import com.cheng.schoolsell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: BinCher
 * Date: 2018-08-23
 * Time: 上午9:09
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public List<BusinessInfo> findAll() {
        return sellerRepository.findAll();
    }

    @Override
    public BusinessInfo save(BusinessInfo businessInfo) {
        return sellerRepository.save(businessInfo);
    }

    @Override
    public Optional<BusinessInfo> findById(String id) {
        return sellerRepository.findById(id);
    }

    @Override
    public Boolean repeatByPhone(String phone) {
        Integer count = sellerRepository.countBusinessInfoByBusinessPhone(phone);
        if (count == 0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Optional<BusinessInfo> businessLoginByPhoneAndPwd(BusinessForm businessForm) {

        Optional<BusinessInfo> businessInfo = sellerRepository.findByBusinessPhoneAndBusinessPwd(
                businessForm.getBusinessPhone(),
                businessForm.getBusinessPwd());
        return businessInfo;
    }

}
