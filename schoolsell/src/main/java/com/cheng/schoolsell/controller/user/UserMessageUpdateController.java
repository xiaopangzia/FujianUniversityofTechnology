package com.cheng.schoolsell.controller.user;

import com.cheng.schoolsell.entity.User;
import com.cheng.schoolsell.enums.UserResultVOEnum;
import com.cheng.schoolsell.exception.UserException;
import com.cheng.schoolsell.form.UserPwdForm;
import com.cheng.schoolsell.service.UserService;
import com.cheng.schoolsell.utils.CookieUtil;
import com.cheng.schoolsell.utils.ResultVoUtil;
import com.cheng.schoolsell.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 上午8:36
 * 用户信息修改
 */
@Controller
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户信息修改相关操作")
public class UserMessageUpdateController {

    @Autowired
    private UserService userService;

    @GetMapping("/msg/{id}")
    @ApiOperation(value = "跳转到用户中心")
    public ModelAndView userCenter(@PathVariable("id") String userId,
                                   Map<String,Object> map) {
        Optional<User> user = userService.findById(userId);
        user.get().setPassword("");
        map.put("user", user.get());
        return new ModelAndView("user/user",map);
    }

    @PostMapping("/pwd/{userId}")
    @ApiOperation(value = "修改密码")
    @ResponseBody
    public ResultVO updatePwd(@PathVariable("userId") String userId,
                              @Valid UserPwdForm userPwdForm,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("用户修改密码：格式错误={}",
                    bindingResult.getFieldError().getDefaultMessage());
            throw new UserException(UserResultVOEnum.USER_PWD_LENGTH_ERROR);
        }

        userService.updateUserPassword(userPwdForm, userId);
        return ResultVoUtil.success(UserResultVOEnum.USER_PWD_UPDATE_SUCCESS);
    }

    @ApiOperation(value = "修改手机号")
    @PostMapping("/phone/{userId}")
    @ResponseBody
    public ResultVO userUpdatePhone(@PathVariable("userId") String userId,
                                    @RequestParam("phone") String phone) {
        if (StringUtils.isEmpty(phone)) {
            log.error("用户修改手机号：手机号不能为空");
            throw new UserException(UserResultVOEnum.USER_UPDATE_PHONE_EXIST);
        }

        userService.updateUserPhone(userId, phone);
        return ResultVoUtil.success(UserResultVOEnum.USER_PHONE_UPDATE_SUCCESS);
    }

    @ApiOperation(value = "修改用户名")
    @PostMapping("/name/{userId}")
    @ResponseBody
    public ResultVO userUpdateName(@PathVariable("userId") String userId,
                                   @RequestParam("username") String username) {

        if (StringUtils.isEmpty(username)) {
            log.error("用户修改用户名:用户名不能为空");
            throw new UserException(UserResultVOEnum.USER_NAME_EXIST);
        }

        userService.updateUsername(userId, username);
        return ResultVoUtil.success(UserResultVOEnum.USER_UPDATE_NAME_SUCCESS);

    }

    @ApiOperation(value = "修改用户Logo")
    @PostMapping("/logo/{userId}")
    @ResponseBody
    public ResultVO userUpdateLogo(@PathVariable("userId") String userId,
                                   @RequestParam("userImg") MultipartFile userImg) {
        System.out.println(userImg);
        userService.updateUserLogo(userId, userImg);
        return ResultVoUtil.success(UserResultVOEnum.USER_UPDATE_LOGO_SUCCESS);

    }

    @ApiOperation(value = "修改用户地址")
    @PostMapping("/address/{userId}")
    @ResponseBody
    public ResultVO userUpdateAddress(@PathVariable("userId") String userId,
                                      @RequestParam("address") String address) {
        userService.updateUserAddress(userId,address);
        return ResultVoUtil.success(UserResultVOEnum.USER_UPDATE_ADDRESS_SUCCESS);
    }

}
