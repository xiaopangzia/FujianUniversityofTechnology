package com.cheng.schoolsell.service;

import com.cheng.schoolsell.dto.UserDTO;
import com.cheng.schoolsell.entity.User;
import com.cheng.schoolsell.form.UserExcel;
import com.cheng.schoolsell.form.UserPwdForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-04
 * Time: 下午4:50
 */
@Transactional(rollbackOn = RuntimeException.class)
public interface UserService {

    /**
     * 批量保存用户资料
     * @param userExcelList
     * @return 保存的数量
     */
    Integer saveAll(List<UserExcel> userExcelList);

    /**
     * 用户新增,修改资料
     * @param userDTO
     * @return
     */
    UserDTO save(UserDTO userDTO);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    Optional<User> findById(String id);

    /**
     * 查询所有用户
     * @param pageable
     * @return
     */
    Page<User> findAll(Pageable pageable);

    /**
     * 根据手机查重
     * @param phone
     * @return
     */
    Integer countUserByPhone(String phone);

    /**
     * 用户登录
     * @param phone
     * @param password
     * @return
     */
    Optional<User> userLogin(String phone, String password);

    /**
     * 用户修改密码
     * @param userPwdForm
     * @param userId
     * @return
     */
    Boolean updateUserPassword(UserPwdForm userPwdForm, String userId);

    /**
     * 用户端修改手机号
     * @param userId
     * @param phone
     */
    void updateUserPhone(String userId, String phone);

    /**
     * 用户修改名字
     * @param userId
     * @param name
     */
    void updateUsername(String userId, String name);

    /**
     * 用户修改头像
     * @param userId
     * @param userImg
     */
    void updateUserLogo(String userId, MultipartFile userImg);

    /**
     * 用户修改地址
     * @param userId
     * @param address
     */
    void updateUserAddress(String userId, String address);

}
