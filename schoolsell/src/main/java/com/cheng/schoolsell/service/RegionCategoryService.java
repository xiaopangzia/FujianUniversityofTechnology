package com.cheng.schoolsell.service;

import com.cheng.schoolsell.entity.RegionCategory;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-20
 * Time: 上午8:01
 */
public interface RegionCategoryService {

    /**
     * 查询所有区域分类
     * @return
     */
    List<RegionCategory> findAll();

    /**
     * 新增/修改区域分类信息
     * @param regionCategory
     * @return
     */
    RegionCategory save(RegionCategory regionCategory);

    /**
     * 通过id查询一个区域分类
     * @param regionId
     * @return
     */
    Optional<RegionCategory> findOne(String regionId);
}
