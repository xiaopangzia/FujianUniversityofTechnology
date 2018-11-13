package com.cheng.schoolsell.controller.user;

import com.cheng.schoolsell.entity.User;
import com.cheng.schoolsell.enums.UserResultVOEnum;
import com.cheng.schoolsell.exception.UserException;
import com.cheng.schoolsell.service.UserService;
import com.cheng.schoolsell.utils.CookieUtil;
import com.cheng.schoolsell.utils.KeyUtil;
import com.cheng.schoolsell.utils.ResultVoUtil;
import com.cheng.schoolsell.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-05
 * Time: 上午10:07
 * 用户控制层
 */
@Controller
@RequestMapping("/user")
@Api(tags = "用户登录相关操作")
@Slf4j
public class UserLoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ApiOperation(value = "跳转到登录页")
    public ModelAndView userLogin(@RequestParam(value = "url",required = false) String url,
                                  Map<String,Object> map){
        if (!StringUtils.isEmpty(url)) {
            map.put("url", url);
        }
        return new ModelAndView("user/login",map);
    }

    @GetMapping("/index")
    @ApiOperation(value = "跳转到主页")
    public ModelAndView userIndex(Map<String,Object> map,
                                  HttpServletRequest request){
        Cookie cookie = CookieUtil.get(request, "user");

        if (cookie != null) {
            String key = cookie.getValue();
            String id = String.valueOf(request.getSession().getAttribute(key));
            if (id != null) {
                Optional<User> user = userService.findById(id);
                if (user.isPresent()) {
                    map.put("id", user.get().getUserId());
                    map.put("name", user.get().getUsername());
                    map.put("img", user.get().getUserImg());
                    if (StringUtils.isEmpty(user.get().getAddress())) {
                        map.put("address", "请到个人中心添加地址,方便订购商品");
                    }
                }
            }
        }

        return new ModelAndView("user/index",map);
    }

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "用户登录")
    public ResultVO userLogin(@RequestParam("phone") String phone,
                              @RequestParam("password") String password,
                              HttpServletRequest request,
                              HttpServletResponse response) {

        Optional<User> user = userService.userLogin(phone, password);
        if (!user.isPresent()) {
            log.error("用户登录：用户不存在;phone={},password={}",phone,password);
            throw new UserException(UserResultVOEnum.USER_NO_EXIST);
        }

        /**
         * 保存到cookie的value和session的key中
         */
        String uuid = KeyUtil.genUniqueKey();
        /**
         * name:user
         * value:uuid
         */
        CookieUtil.set(response, "user", uuid, 60 * 60 * 12);
        /**
         * key:uuid
         * value:userId
         */
        request.getSession().setAttribute(uuid,user.get().getUserId());
        request.getSession().setMaxInactiveInterval(60 * 60 * 12);

        return ResultVoUtil.success(UserResultVOEnum.USER_LOGIN_SUCCESS);
    }

    @ApiOperation(value = "用户退出登录")
    @ResponseBody
    @GetMapping("/logout/{userId}")
    public ResultVO userLogout(@PathVariable("userId") String userId,
                               HttpServletResponse response,
                               HttpServletRequest request) {

        Cookie cookie = CookieUtil.get(request, "user");
        if (cookie != null) {
            request.getSession().removeAttribute(cookie.getValue());
        }
        CookieUtil.set(response, "user", "", 0);
        return ResultVoUtil.success(UserResultVOEnum.USER_LOGOUT_SUCCESS);
    }


}
