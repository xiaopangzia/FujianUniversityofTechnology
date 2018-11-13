package com.cheng.schoolsell.utils;

import com.cheng.schoolsell.enums.UserResultVOEnum;
import com.cheng.schoolsell.vo.ResultVO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-04
 * Time: 上午10:13
 * 返回api工具类
 */
public class ResultVoUtil {

    public static ResultVO success(Object object) {

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;

    }

    public static ResultVO success(UserResultVOEnum userResultVOEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(userResultVOEnum.getCode());
        resultVO.setMsg(userResultVOEnum.getMessage());
        return resultVO;
    }

    public static ResultVO error(Integer code, String msg,Object object) {
        ResultVO resultVO= new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO error(Integer code, String msg) {
        return error(code, msg,null);

    }

}
