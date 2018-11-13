package com.cheng.schoolsell.controller.admin;

import com.cheng.schoolsell.constant.CookieConstant;
import com.cheng.schoolsell.entity.Admin;
import com.cheng.schoolsell.enums.AdminResultEnum;
import com.cheng.schoolsell.exception.AdminException;
import com.cheng.schoolsell.form.AdminForm;
import com.cheng.schoolsell.service.AdminService;
import com.cheng.schoolsell.utils.AdminResultMapUtil;
import com.cheng.schoolsell.utils.CookieUtil;
import com.cheng.schoolsell.utils.KeyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 下午3:33
 */
@Controller
@Slf4j
@RequestMapping("/admin")
@Api(tags = "管理员登录相关")
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    @ApiOperation(value = "跳转到管理员登录界面")
    public ModelAndView toAdminLogin() {
        return new ModelAndView("admin/login");
    }

    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public ModelAndView adminLogin(@Valid AdminForm adminForm,
                                   BindingResult bindingResult,
                                   HttpServletRequest request,
                                   HttpServletResponse response,
                                   Map<String, Object>map) {

        if (bindingResult.hasErrors()) {
            log.error("管理员登录，登录失败={}",
                    bindingResult.getFieldError().getDefaultMessage());
            throw new AdminException(AdminResultEnum.NAME_PASSWORD_EMPTY);
        }

        Optional<Admin> admin = adminService.adminLogin(
                adminForm.getAdminUser(),
                adminForm.getAdminPwd());

        if (!admin.isPresent()) {
            log.error("管理员登录，管理员名或密码错误={}", adminForm.toString());
            throw new AdminException(AdminResultEnum.ADMIN_LOGIN_ERROR);
        }

        String tokenName = KeyUtil.getUUID();

        // 设置cookie
        CookieUtil.set(response, CookieConstant.ADMINTOKEN, tokenName, CookieConstant.COOKIEMAXAGE);

        //设置session 同步到Redis
        request.getSession().setAttribute(tokenName, admin.get().getAdminId());
        String adminToken = KeyUtil.genUniqueKey();
        admin.get().setToken(adminToken);
        adminService.save(admin.get());

        return new ModelAndView("admin/common/success",
                AdminResultMapUtil.success(AdminResultEnum.ADMIN_LOGIN_SUCCESS,adminToken));
    }

    @GetMapping("/logout")
    @ApiOperation(value = "管理员退出")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {

        Cookie cookie = CookieUtil.get(request, CookieConstant.ADMINTOKEN);
        if (cookie != null) {
            request.getSession().removeAttribute(cookie.getValue());
            CookieUtil.set(response,CookieConstant.ADMINTOKEN,null,0);
        }

        return new ModelAndView("admin/common/success",
                AdminResultMapUtil.success(AdminResultEnum.ADMIN_LOGOUT_SUCCESS));
    }


}
