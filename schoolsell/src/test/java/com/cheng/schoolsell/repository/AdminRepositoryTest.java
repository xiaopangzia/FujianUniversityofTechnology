package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.Admin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 下午9:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository repository;

    @Test
    public void findByAdminUserAAndAdminPwd() {

        Optional<Admin> admin = repository.findByAdminUserAndAdminPwd("admin", "pwd");
        Assert.assertTrue(admin.isPresent());
    }
}
