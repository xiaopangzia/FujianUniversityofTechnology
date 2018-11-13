package com.cheng.schoolsell.repository;

import com.cheng.schoolsell.entity.RegionCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-20
 * Time: 上午8:00
 */
public interface RegionCategoryRepository extends JpaRepository<RegionCategory, String> {

}
