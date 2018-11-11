package com.cheng.sell.dataobject.dao;

import com.cheng.sell.dataobject.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-14
 * Time: 上午9:45
 */
public class ProductCategoryDao {

    @Autowired
    private ProductCategoryMapper mapper;

    public int insertByMapper(Map<String, Object> map){

        return mapper.insertByMapper(map);

    }

}
