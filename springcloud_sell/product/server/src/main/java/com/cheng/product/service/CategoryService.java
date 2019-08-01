package com.cheng.product.service;

import com.cheng.product.dataobject.ProductCategory;

import java.util.List;
import java.util.Set;

/**
 * 类目
 * Created by 廖师兄
 * 2017-12-09 22:06
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(Set<Integer> categoryTypeList);
}
