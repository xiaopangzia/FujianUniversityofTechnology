package com.zhiyou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 登录控制器
 * @author Cheng
 */
@Controller
public class LoginController {

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        //获取Cookie数据
        Cookie[] cookies=request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName=cookie.getName();
                if ("username".equals(cookieName)) {
                    String username = cookie.getValue();
                    model.addAttribute("username", username);
                }
                if ("password".equals(cookieName)) {
                    String password = cookie.getValue();
                    model.addAttribute("password", password);
                }
            }
        }
        //请求转发
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password,String rememberMe, Model model,HttpServletResponse response,HttpSession session) throws IOException {
        //校验用户名密码是否正确
        InputStream is=LoginController.class.getClassLoader().getResourceAsStream("user.properties");
        Properties prop = new Properties();
        prop.load(is);
        String _password = prop.getProperty(username);
        if (_password == null) {
            //用户名不存在
            model.addAttribute("error","用户名不存在！");
            return "login";
        }else {
            //md5加密
            String newPassword=DigestUtils.md5DigestAsHex(password.getBytes());
            //用户名正确
            //比较属性文件中存储的密码是否和用户输入的密码是否正确
            if (!_password.equals(newPassword)) {
                //密码错误
                model.addAttribute("error", "密码错误");
                return "/login";
            }else{
                //重定向到/index方法
                if ("on".equals(rememberMe)) {
                    //用户信息写入客户端浏览器
                    //定义一个usernameCk的Cookie
                    Cookie usernameCk = new Cookie("username", username);
                    //定义一个passwordCk的Cookie
                    Cookie passwordCk = new Cookie("password", password);
                    //将Cookie的内容写入浏览器
                    //设置cookie有效期
                    usernameCk.setMaxAge(1000*60*60*24);
                    passwordCk.setMaxAge(1000*60*60*24);
                    response.addCookie(usernameCk);
                    response.addCookie(passwordCk);
                }
                session.setAttribute("username", username);
                //登陆成功，重定向到系统首页
                return "redirect:/index";
            }
        }
        //重定向到系统主页

    }

    /*@PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        //用户名
        String username = request.getParameter("username");
        //密码
        String password = request.getParameter("password");
        //记住我
        String rememberMe = request.getParameter("rememberMe");
        if ("on".equals(rememberMe)) {
            //用户信息写入客户端浏览器
            //定义一个usernameCk的Cookie
            Cookie usernameCk = new Cookie("username", username);
            //定义一个passwordCk的Cookie
            Cookie passwordCk = new Cookie("password", password);
            //将Cookie的内容写入浏览器
            //设置cookie有效期
            usernameCk.setMaxAge(1000*60*60*24);
            passwordCk.setMaxAge(1000*60*60*24);
            response.addCookie(usernameCk);
            response.addCookie(passwordCk);
        }
        //校验用户名密码是否正确

        session.setAttribute("username", username);

        //登陆成功，跳转到index.html
        return "index";
    }*/

    @GetMapping("/loginOut")
    public String out(HttpSession session) {

        session.invalidate();
        //退出成功，重定向到/login
        return "redirect:/login";
    }

    @GetMapping("/index")
    public String index() {

        //请求转发
        return "index";
    }
}
