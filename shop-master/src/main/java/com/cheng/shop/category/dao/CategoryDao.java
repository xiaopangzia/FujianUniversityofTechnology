package com.cheng.shop.category.dao;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import com.cheng.shop.category.domain.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.print.attribute.standard.NumberUp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryDao {
    private QueryRunner qr = new TxQueryRunner();

    /**
     * 把一个Map中的数据映射到Category中
     *
     * @param map
     * @return
     */
    private Category toCategory(Map<String, Object> map) {

        Category category = CommonUtils.toBean(map, Category.class);
        String pid = (String) map.get("pid");
        if (pid != null) {
            /**
             * 拦截pid
             * 把父分类设置给Category
             */
            Category parent = new Category();
            parent.setCid(pid);
            category.setParent(parent);
        }
        return category;
    }

    /**
     * 保存多个CategoryMap对象
     * 可以把多个Map(List<Map>)映射成多个Category(List<Category><)
     *
     * @param mapList
     * @return
     */
    private List<Category> toCategoryList(List<Map<String, Object>> mapList) {
        List<Category> categoryList = new ArrayList<Category>();
        for (Map<String, Object> map : mapList) {
            Category c = toCategory(map);
            categoryList.add(c);
        }
        return categoryList;
    }

    /**
     * 返回所有分类
     *
     * @return
     */
    public List<Category> findAll() throws SQLException {
        /**
         * 1.查询出所有一级分类
         */
        String sql = "select * from t_category where pid is null";
        List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
        List<Category> parents = toCategoryList(mapList);
        /**
         * 2.循环遍历所有的一级分类，为每个一级分类加载他的二级分类
         */
        for (Category parent : parents) {
            //查询出当前父分类的所有子分类
            List<Category> children = findByParent(parent.getCid());
            //设置给父分类
            parent.setChildren(children);
        }
        return parents;
    }

    /**
     * 通过父分类查询子分类
     *
     * @param pid
     * @return
     */
    public List<Category> findByParent(String pid) throws SQLException {
        String sql = "select * from t_category where pid=?";
        List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), pid);
        return toCategoryList(mapList);
    }

    /**
     * 添加一级分类或者二级分类
     *
     * @param category
     */
    public void add(Category category) throws SQLException {
        String sql = "insert into t_category (cid,cname,pid,`desc`) values (?,?,?,?)";

        /**
         * 判断是否为一级分类
         */
        String pid = null;
        if (category.getParent() != null) {
            pid = category.getParent().getCid();
        }
        Object[] params = {
                category.getCid(),
                category.getCname(),
                pid,
                category.getDesc()
        };
        qr.update(sql, params);
    }

    /**
     * 添加二级分类第一步
     * @return
     * @throws SQLException
     */
    public List<Category> findParents() throws SQLException {
        /**
         * 1.查询出所有一级分类
         */
        String sql = "select * from t_category where pid is null order by orderBy";
        List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
        return toCategoryList(mapList);
    }

    /**
     * 加载分类
     * @param cid
     * @return
     * @throws SQLException
     */
    public Category load(String cid) throws SQLException {
        String sql = "select * from t_category where cid=?";
        return toCategory(qr.query(sql, new MapHandler(), cid));
    }

    /**
     * 修改分类
     * @param category
     * @throws SQLException
     */
    public void edit(Category category) throws SQLException {
        String sql = "update t_category set cname=?,pid=?,`desc`=? where cid=?";
        String pid = null;
        if (category.getParent() != null) {
            pid = category.getParent().getCid();
        }
        Object[] params = {category.getCname(), pid, category.getDesc(),category.getCid()};
        qr.update(sql, params);
    }

    /**
     * 查询一级分类下二级分类的数量
     * @param pid
     * @return
     * @throws SQLException
     */
    public int findChildCountByPid(String pid) throws SQLException {
        String sql = "select count(*) from t_category where pid=?";
        Number count = (Number) qr.query(sql, new ScalarHandler(), pid);
        return count == null ? 0 : count.intValue();
    }

    /**
     * 查询二级分类下的图书数量
     * @param cid
     * @return
     * @throws SQLException
     */
    public int findBookCount(String cid) throws SQLException {
        String sql = "select count(*) from t_book where cid=?";
        Number count = (Number) qr.query(sql, new ScalarHandler(), cid);
        return count == null ? 0 : count.intValue();
    }

    /**
     * 删除分类
     * @param cid
     * @throws SQLException
     */
    public void delete(String cid) throws SQLException {
        String sql = "delete from t_category where cid=?";
        qr.update(sql, cid);
    }

}
