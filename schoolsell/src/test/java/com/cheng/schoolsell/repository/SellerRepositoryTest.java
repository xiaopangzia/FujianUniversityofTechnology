package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.BusinessInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-19
 * Time: 下午3:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerRepositoryTest {

    @Autowired
    private SellerRepository sellerRepository;

    @Test
    public void countBusinessInfoByBusinessPhone() {

        Integer count = sellerRepository.countBusinessInfoByBusinessPhone("13176666666");
        Assert.assertEquals(Integer.valueOf(1),count);
    }

    @Test
    public void findByBusinessPhoneAndBusinessPwd() {

        Optional<BusinessInfo> businessInfo = sellerRepository.findByBusinessPhoneAndBusinessPwd(
                "131766666661",
                "13176666666"
        );
        System.out.println(businessInfo.isPresent());
        /*Assert.assertNotNull(businessInfo);
*/
    }
}
