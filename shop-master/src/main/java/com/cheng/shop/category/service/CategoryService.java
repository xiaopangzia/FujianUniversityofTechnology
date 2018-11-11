package com.cheng.shop.category.service;

import com.cheng.shop.category.dao.CategoryDao;
import com.cheng.shop.category.domain.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao=new CategoryDao();

    /**
     * 查询所有分类
     * @return
     */
    public List<Category> findAll(){
        try {
            return categoryDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 添加一级分类
     * @param category
     */
    public void add(Category category) {
        try {
            categoryDao.add(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询一级分类
     * @return
     */
    public List<Category> findParents() {
        try {
            return categoryDao.findParents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加二级分类
     * @param category
     */
    public void addChild(Category category) {
        try {
            categoryDao.add(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载分类
     * @param cid
     * @return
     */
    public Category load(String cid) {
        try {
            return categoryDao.load(cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改分类
     * @param category
     */
    public void edit(Category category){
        try {
            categoryDao.edit(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int findBookCount(String cid) {
        try {
            return categoryDao.findBookCount(cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询一级分类下的二级分类数量
     * @param pid
     * @return
     */
    public int findChildCountByPid(String pid) {
        try {
            return categoryDao.findChildCountByPid(pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除分类
     * @param cid
     */
    public void delete(String cid) {
        try {
            categoryDao.delete(cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> findByParent(String pid) {
        try {
            return categoryDao.findByParent(pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> findChildren(String pid) {
        try {
            return categoryDao.findByParent(pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
