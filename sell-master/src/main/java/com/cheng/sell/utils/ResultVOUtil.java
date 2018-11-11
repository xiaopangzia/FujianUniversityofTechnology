package com.cheng.sell.utils;

import com.cheng.sell.vo.ResultVO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 返回工具类
 * @author cheng
 * Date: 2018-07-01
 * Time: 下午9:54
 */
@SuppressWarnings("unchecked")
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
