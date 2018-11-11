package com.cheng.shop.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class loginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        Object user = request.getSession().getAttribute("sessionUser");
        if (user == null) {
            request.setAttribute("code", "error");
            request.setAttribute("msg", "您未登录，请登录！");
            request.getRequestDispatcher("jsps/msg.jsp").forward(request, resp);
        }else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
