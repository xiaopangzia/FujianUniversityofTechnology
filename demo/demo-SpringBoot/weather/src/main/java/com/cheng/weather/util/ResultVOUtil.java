package com.cheng.weather.util;

import com.cheng.weather.vo.ResultVO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-15
 * Time: 下午2:31
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setStatus("200");
        resultVO.setMsg("成功");
        resultVO.setResult(object);
        return resultVO;
    }


    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error() {
        ResultVO resultVO = new ResultVO();
        resultVO.setStatus("400");
        resultVO.setMsg("失败");
        return resultVO;
    }

}
