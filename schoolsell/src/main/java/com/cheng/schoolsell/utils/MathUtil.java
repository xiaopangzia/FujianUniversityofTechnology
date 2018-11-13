package com.cheng.schoolsell.utils;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: cheng
 * Date: 2018-07-09
 * Time: 上午9:53
 */
public class MathUtil {

    private static final Double Money_Range = 0.01;

    /**
     * 比较2个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1-d2);
        if (result < Money_Range) {
            return true;
        }else {
            return false;
        }
    }

}
