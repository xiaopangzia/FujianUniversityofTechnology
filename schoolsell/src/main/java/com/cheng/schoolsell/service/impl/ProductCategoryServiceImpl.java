package com.cheng.schoolsell.service.impl;

import com.cheng.schoolsell.entity.ProductCategory;
import com.cheng.schoolsell.entity.ProductInfo;
import com.cheng.schoolsell.repository.ProductCategoryRepository;
import com.cheng.schoolsell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-27
 * Time: 上午10:12
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findShopAllProductCategory(String shopId) {
        return productCategoryRepository.findProductCategoryByShopId(shopId);
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public Optional<ProductCategory> findProductCategoryById(String categoryId) {
        return productCategoryRepository.findById(categoryId);
    }

    @Override
    public Boolean countProductCategoryByType(Integer categoryType,String shopId) {
        Integer count = productCategoryRepository
                .countProductCategoryByCategoryTypeAndShopId(categoryType,shopId);
        if (count == 0) {
            return true;
        }
        return false;
    }

}
