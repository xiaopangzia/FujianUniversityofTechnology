package com.zhiyou.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class Spring implements WebMvcConfigurer {

    //注册一个拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //添加一个拦截器
        registry.addInterceptor(new HandlerInterceptor() {

            //一个请求过来前做什么
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

                HttpSession session=request.getSession();
                if (session.getAttribute("username")==null) {
                    //如果用户没登录，重定向
                    response.sendRedirect("/login");
                    return false;
                }
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

            }
            //addPathPatterns拦截，excludePathPatterns排除拦截
        }).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
