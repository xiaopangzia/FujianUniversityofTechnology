package com.cheng.user.utils;

import com.cheng.user.VO.ResultVO;
import com.cheng.user.enums.ResultEnum;

/**
 * Created by 廖师兄
 * 2017-12-09 22:53
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success() {
        return success("");
    }

    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(resultEnum.getMessage());
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg("失败");
        return resultVO;
    }
}
