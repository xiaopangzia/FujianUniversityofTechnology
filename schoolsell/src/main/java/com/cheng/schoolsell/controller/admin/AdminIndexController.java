package com.cheng.schoolsell.controller.admin;

import com.cheng.schoolsell.service.AdminService;
import com.cheng.schoolsell.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 下午10:34
 */
@Controller
@Slf4j
@RequestMapping("/admin")
@Api(tags = "管理员主页")
public class AdminIndexController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/index")
    @ApiOperation(value = "跳转到管理员主页")
    public ModelAndView adminIndex(){

        Map<String,Object> map = orderService.getAdminIndexMsg();
        return new ModelAndView("admin/index",map);
    }



}
