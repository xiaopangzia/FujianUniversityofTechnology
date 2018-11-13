package com.cheng.schoolsell.controller.admin;

import com.cheng.schoolsell.dto.UserDTO;
import com.cheng.schoolsell.entity.User;
import com.cheng.schoolsell.enums.AdminResultEnum;
import com.cheng.schoolsell.exception.AdminException;
import com.cheng.schoolsell.form.UserExcel;
import com.cheng.schoolsell.service.SaleService;
import com.cheng.schoolsell.service.UserService;
import com.cheng.schoolsell.utils.AdminResultMapUtil;
import com.cheng.schoolsell.utils.ExcelUtil;
import com.cheng.schoolsell.utils.KeyUtil;
import com.cheng.schoolsell.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-21
 * Time: 上午9:17
 */
@Controller
@RequestMapping("/admin/user")
@Slf4j
@Api(tags = "管理员端用户管理")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SaleService saleService;

    @GetMapping("/list")
    @ApiOperation(value = "查看用户列表")
    public ModelAndView adminUserList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                      @RequestParam(value = "size",defaultValue = "10") Integer size,
                                      Map<String ,Object> map){
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(page-1, size);

        Page<User> userPage = userService.findAll(pageRequest);
        List<UserVO> userVOList = new ArrayList<>();
        for (User user : userPage.getContent()) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            userVOList.add(userVO);
        }

        map.put("userList", userVOList);
        map.put("page", page);
        map.put("totals", userPage.getTotalPages());
        return new ModelAndView("admin/user/list",map);
    }

    @GetMapping("/save")
    @ApiOperation(value = "跳转到保存单个用户信息页")
    public ModelAndView adminUserSave() {
        return new ModelAndView("admin/user/save");
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存单个用户信息")
    public ModelAndView adminUserSave(UserDTO userDTO,
                                      Map<String,Object> map) {

        String token = userDTO.getToken();
        if (StringUtils.isEmpty(userDTO.getUsername())) {
            throw new AdminException(AdminResultEnum.ADMIN_USER_USERNAME_EMPTY,token);
        }
        if (StringUtils.isEmpty(userDTO.getPhone())) {
            throw new AdminException(AdminResultEnum.ADMIN_USER_PHONE_EMPTY,token);
        }

        if (userService.countUserByPhone(userDTO.getPhone()) != 0) {
            throw new AdminException(AdminResultEnum.ADMIN_USER_PHONE_EXIST,token);
        }

        userDTO.setPassword(userDTO.getPhone());
        userDTO.setUserId(KeyUtil.getUUID());
        userService.save(userDTO);

        return new ModelAndView("admin/common/success",
                AdminResultMapUtil.success(
                        AdminResultEnum.ADMIN_USER_SUCCESS,userDTO.getToken()));
    }

    @GetMapping("/saveList")
    @ApiOperation(value = "跳转到批量保存用户信息页")
    public ModelAndView saveList(){
        return new ModelAndView("admin/user/saveList");
    }

    @PostMapping("/saveList")
    @ApiOperation(value = "批量保存用户信息")
    public ModelAndView saveList(@RequestParam("token") String token,
                                 @RequestParam("file") MultipartFile file,
                                 Map<String,Object> map){
        List<UserExcel> userExcelList = ExcelUtil.importExcel(file, 1, 1, UserExcel.class,token);
        Integer num = userService.saveAll(userExcelList);
        map.put("url", "/sell/admin/user/list?token=" + token);
        map.put("msg", "添加" + num + "个用户成功");
        return new ModelAndView("admin/common/success");
    }

    @GetMapping("/detail/{userId}")
    @ApiOperation(value = "用户消费详情等")
    public ModelAndView getDetail(@PathVariable("userId") String userId) {

        Map<String,Object> map = saleService.adminGetUserSale(userId);

        String username = userService.findById(userId).get().getUsername();
        map.put("username", username);
        return new ModelAndView("admin/user/detail",map);
    }

}
