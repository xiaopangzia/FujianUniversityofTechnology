package com.cheng.schoolsell.service.impl;

import com.cheng.schoolsell.constant.ShopConstant;
import com.cheng.schoolsell.constant.UserConstant;
import com.cheng.schoolsell.convert.UserConvert;
import com.cheng.schoolsell.dto.UserDTO;
import com.cheng.schoolsell.entity.User;
import com.cheng.schoolsell.enums.UserResultVOEnum;
import com.cheng.schoolsell.exception.UserException;
import com.cheng.schoolsell.form.UserExcel;
import com.cheng.schoolsell.form.UserPwdForm;
import com.cheng.schoolsell.repository.UserRepository;
import com.cheng.schoolsell.service.UserService;
import com.cheng.schoolsell.utils.KeyUtil;
import com.cheng.schoolsell.utils.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-04
 * Time: 下午4:52
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UploadUtil uploadUtil;

    @Override
    public Integer saveAll(List<UserExcel> userExcelList) {
        List<User> userList = new ArrayList<>();
        for (UserExcel userExcel : userExcelList) {
            User user = new User();
            BeanUtils.copyProperties(userExcel, user);
            user.setUserId(KeyUtil.getUUID());
            user.setPassword(userExcel.getPhone());
            user.setUserImg(UserConstant.USERIMG);
            userList.add(user);
        }
        List<User> users = userRepository.saveAll(userList);
        return users.size();
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setUserImg(UserConstant.USERIMG);
        return UserConvert.userToUserDTO(userRepository.save(user));
    }

    @Override
    public Optional<User> findById(String userId){
        return userRepository.findById(userId);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Integer countUserByPhone(String phone) {
        return userRepository.countByPhone(phone);
    }

    @Override
    public Optional<User> userLogin(String phone, String password) {

        Optional<User> user = userRepository.findUserByPhoneAndPassword(phone, password);
        return user;
    }

    @Override
    public Boolean updateUserPassword(UserPwdForm userPwdForm, String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            log.error("用户修改密码：该用户不存在");
            throw new UserException(UserResultVOEnum.USER_NO_EXIST);
        }

        if (!user.get().getPassword().equals(userPwdForm.getPassword())) {
            log.error("用户修改密码：原密码错误");
            throw new UserException(UserResultVOEnum.USER_PWD_ERROR);
        }

        if (!userPwdForm.getNewPassword().
                equals(userPwdForm.getVerifyNewPassword())){
            log.error("用户修改密码：新密码与确认密码不一致");
            throw new UserException(UserResultVOEnum.USER_NEW_VERIFY_DIFFERENT);
        }

        user.get().setPassword(userPwdForm.getNewPassword());
        userRepository.save(user.get());

        return true;
    }

    @Override
    public void updateUserPhone(String userId, String phone) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            log.error("用户修改密码：用户不存在");
            throw new UserException(UserResultVOEnum.USER_NO_EXIST);
        }

        Integer num = userRepository.countByPhone(phone);
        if (num != 0) {
            log.error("用户修改密码：手机号重复");
            throw new UserException(UserResultVOEnum.USER_PHONE_EXIST);
        }
        user.get().setPhone(phone);
        userRepository.save(user.get());
    }

    @Override
    public void updateUsername(String userId, String name) {

        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            log.error("用户修改密用户名：用户不存在");
            throw new UserException(UserResultVOEnum.USER_NO_EXIST);
        }
        if (user.get().getUsername().equals(name)) {
            log.error("用户修改密用户名：用户名相同，不需要修改");
            throw new UserException(UserResultVOEnum.USER_NAME_THE_SAME);
        }
        user.get().setUsername(name);
        userRepository.save(user.get());
    }

    @Override
    public void updateUserLogo(String userId, MultipartFile userImg) {

        User user = userRepository.findById(userId).get();
        String oldUrl = user.getUserImg();
        try {
            String imgUrl = uploadUtil.uploadFileNOS(userImg);
            user.setUserImg(imgUrl);
        } catch (IOException e) {
            log.error("用户修改图片：网易云上传图片失败,msg={}",e.getMessage());
            throw new UserException(UserResultVOEnum.USER_UPLOADIMG_ERROR);
        }

        if (!ShopConstant.SHOPIMG.equals(oldUrl)) {
            uploadUtil.deleteFileNOS(oldUrl);
        }

        userRepository.save(user);
    }

    @Override
    public void updateUserAddress(String userId, String address) {
        if (StringUtils.isEmpty(address)) {
            log.error("用户修改地址:地址为空");
            throw new UserException(UserResultVOEnum.USER_ADDRESS_EXIST);
        }
        User user = userRepository.findById(userId).get();
        user.setAddress(address);
        userRepository.save(user);
    }

}
