package com.cheng.user.controller;

import com.cheng.user.VO.ResultVO;
import com.cheng.user.constant.CookieConstant;
import com.cheng.user.constant.RedisConstant;
import com.cheng.user.dataobject.UserInfo;
import com.cheng.user.enums.ResultEnum;
import com.cheng.user.enums.RoleEnum;
import com.cheng.user.service.UserInfoService;
import com.cheng.user.utils.CookieUtils;
import com.cheng.user.utils.ResultVOUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author cheng
 * @date 2019-07-28
 * @description
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response) {
        UserInfo userInfo = userInfoService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_ERROR);
        }
        if (userInfo.getRole() != RoleEnum.BUYER.getCode()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        CookieUtils.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                           HttpServletResponse response,
                           HttpServletRequest request) {
        Cookie cookie = CookieUtils.get(request, CookieConstant.TOKEN);

        if (cookie != null) {
            String redisOpenid = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN, cookie.getValue()));
            if (!StringUtils.isEmpty(redisOpenid) && openid.equals(redisOpenid)) {
                return ResultVOUtil.success();
            }
        }

        UserInfo userInfo = userInfoService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_ERROR);
        }
        if (userInfo.getRole() != RoleEnum.SELLER.getCode()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(
                String.format(RedisConstant.TOKEN, uuid), openid,
                RedisConstant.expire, TimeUnit.SECONDS);

        CookieUtils.set(response, CookieConstant.TOKEN, uuid, CookieConstant.expire);

        return ResultVOUtil.success();
    }

}
