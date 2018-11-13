package com.cheng.schoolsell.aspect;

import com.cheng.schoolsell.constant.CookieConstant;
import com.cheng.schoolsell.entity.Admin;
import com.cheng.schoolsell.enums.AdminResultEnum;
import com.cheng.schoolsell.exception.AdminException;
import com.cheng.schoolsell.service.AdminService;
import com.cheng.schoolsell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-09
 * Time: 上午8:33
 */
@Aspect
@Component
@Slf4j
public class VerifyAdminLoginAspect {

    @Autowired
    private AdminService adminService;

    @Pointcut("execution(public * com.cheng.schoolsell.controller.admin.Admin*.*(..))" +
            "&& !execution(public * com.cheng.schoolsell.controller.admin.AdminLoginController.*(..))")
    public void verifyAdmin() {}

    @Before("verifyAdmin()")
    public void verifyAdminLogin() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String token = request.getParameter("token");
        if ("".equals(token)) {
            log.error("请求中没有token,请求路径为={}",request.getRequestURL());
            throw new AdminException(AdminResultEnum.ADMIN_NOT_LOGIN);
        }

        Cookie cookie = CookieUtil.get(request, CookieConstant.ADMINTOKEN);
        if (cookie == null||"".equals(cookie.getValue())) {
            log.error("cookie查不到信息");
            throw new AdminException(AdminResultEnum.ADMIN_NOT_LOGIN);
        }

        String sessionName = cookie.getValue();
        Integer adminId;
        try {
            adminId = (Integer) request.getSession().getAttribute(sessionName);
        } catch (Exception e) {
            log.error("未找到session");
            throw new AdminException(AdminResultEnum.ADMIN_NOT_LOGIN);
        }

        if (adminId == null) {
            log.error("session中没有值");
            throw new AdminException(AdminResultEnum.ADMIN_NOT_LOGIN);
        }

        Optional<Admin> admin = adminService.findById(adminId);
        if (!admin.isPresent() || !admin.get().getToken().equals(token)) {
            log.error("数据库数据不匹配");
            throw new AdminException(AdminResultEnum.ADMIN_NOT_LOGIN);
        }
    }

    @After("verifyAdmin()")
    public void resultToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Cookie cookie = CookieUtil.get(request, CookieConstant.ADMINTOKEN);
        if (cookie==null||"".equals(cookie.getValue())) {
            throw new AdminException(AdminResultEnum.ADMIN_NOT_LOGIN);
        }
        Integer adminId = (Integer) request.getSession().getAttribute(cookie.getValue());

        if (StringUtils.isEmpty(adminId)) {
            throw new AdminException(AdminResultEnum.ADMIN_NOT_LOGIN);
        }
        Optional<Admin> admin = adminService.findById(adminId);

        if (!admin.isPresent()) {
            throw new AdminException(AdminResultEnum.ADMIN_EXIST);
        }
        String token = request.getParameter("token");
        String updateTime = String.valueOf(admin.get().getUpdateTime());
        String newUpdateTime = String.format(updateTime.substring(0, updateTime.lastIndexOf(".")));
        request.setAttribute("updateTime", newUpdateTime);
        request.setAttribute("token", token);
        request.setAttribute("admin",admin.get());
    }
}
