package com.cheng.mybatisgenerate.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author cheng
 * @date 2019-08-03
 * @description 通用mapper 继承该接口 默认实现多个sql方法
 */
@org.apache.ibatis.annotations.Mapper
public interface GeneralMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
