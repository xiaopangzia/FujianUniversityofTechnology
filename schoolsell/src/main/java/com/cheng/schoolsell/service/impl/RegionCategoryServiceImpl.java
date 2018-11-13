package com.cheng.schoolsell.service.impl;

import com.cheng.schoolsell.entity.RegionCategory;
import com.cheng.schoolsell.repository.RegionCategoryRepository;
import com.cheng.schoolsell.service.RegionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-20
 * Time: 上午8:05
 */
@Service
public class RegionCategoryServiceImpl implements RegionCategoryService {

    @Autowired
    private RegionCategoryRepository regionCategoryRepository;

    @Override
    public List<RegionCategory> findAll() {
        return regionCategoryRepository.findAll();
    }

    @Override
    public RegionCategory save(RegionCategory regionCategory) {
        return regionCategoryRepository.save(regionCategory);
    }

    @Override
    public Optional<RegionCategory> findOne(String regionId) {
        return regionCategoryRepository.findById(regionId);
    }
}
