package com.cheng.schoolsell.form;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: BinCher
 * Date: 2018-09-05
 * Time: 下午4:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserExcel {

    @Excel(name = "姓名")
    private String username;

    @Excel(name = "电话")
    private String phone;
}
