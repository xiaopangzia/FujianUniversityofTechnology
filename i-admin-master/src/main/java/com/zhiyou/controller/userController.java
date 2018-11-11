package com.zhiyou.controller;

import com.zhiyou.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("/user")
public class userController {

    List<User> list = new ArrayList<>();

    @GetMapping("/list")
    public String list(Model model, HttpSession session) {
        /*if (session.getAttribute("username")==null){
            return "redirect:/login";
        }*/
        model.addAttribute("list", list);
        return "user/user_list";
    }

    @GetMapping("/add")
    public String add() {
        return "user/user_add";
    }

    //重定向
    @PostMapping("/add")
    public String add(User user) {
        /**
         * 产生uuid
         * replace把“-”换成“”
         * toUpperCase换成大写
         */
        user.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        list.add(user);
        return "redirect:/user/list";
    }

/*    1.@GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        String id = request.getParameter("id");
        ListIterator<User> iterator = list.listIterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId().equals(id)) {
                list.remove(user);
                break;
            }
        }
        return "redirect:/user/list";
    }*/

    //@Pathvariable获取链接的数据
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        ListIterator<User> iterator = list.listIterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId().equals(id)) {
                list.remove(user);
                break;
            }
        }
        return "redirect:/user/list";
    }
}
