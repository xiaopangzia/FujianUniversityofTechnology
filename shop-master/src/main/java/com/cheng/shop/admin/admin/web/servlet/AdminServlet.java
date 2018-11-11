package com.cheng.shop.admin.admin.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import com.cheng.shop.admin.admin.domain.Admin;
import com.cheng.shop.admin.admin.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends BaseServlet{
    private AdminService adminService = new AdminService();

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin form = CommonUtils.toBean(request.getParameterMap(), Admin.class);
        Admin admin=adminService.login(form);
        if (admin == null) {
            request.setAttribute("msg", "用户名或密码错误");
            return "f:/adminjsps/login.jsp";
        }

        request.getSession().setAttribute("admin", admin);
        return "f:/adminjsps/admin/index.jsp";
    }
    
}
