package com.cheng.schoolsell.convert;

import com.cheng.schoolsell.dto.UserDTO;
import com.cheng.schoolsell.entity.User;
import com.cheng.schoolsell.vo.UserVO;
import org.springframework.beans.BeanUtils;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-05
 * Time: 下午4:43
 * user数据转换
 */
public class UserConvert {

    public static UserDTO userToUserDTO(User user) {
        UserDTO userDTO= new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

}
