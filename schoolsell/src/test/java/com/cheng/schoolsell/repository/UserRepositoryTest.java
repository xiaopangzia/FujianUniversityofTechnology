package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.User;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Optional;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-04
 * Time: 下午4:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void countByPhone() {

        Integer count = repository.countByPhone("13100000000");
        System.out.println(count);

    }

    @Test
    public void findUserByPhoneAndPasswordTest() {
        String phone = String.valueOf(131);
        String password = String.valueOf(131);
        Optional<User> user = repository.findUserByPhoneAndPassword(phone, password);
        Assert.assertEquals(true,user.isPresent());
    }
}
