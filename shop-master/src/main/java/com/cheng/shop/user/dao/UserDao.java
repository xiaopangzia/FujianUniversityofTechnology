package com.cheng.shop.user.dao;

/*
 *用户模块持久层
 */

import cn.itcast.jdbc.TxQueryRunner;
import com.cheng.shop.user.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.SQLException;

public class UserDao {
    private QueryRunner qr=new TxQueryRunner();
    /**
     * 校验用户名是否注册
     */
    public boolean ajaxValidateLoginname(String loginname) throws SQLException {
        String sql="select count(1) from t_user where loginname=?";
        Number number=(Number)qr.query(sql,new ScalarHandler(),loginname);
        return number.intValue()==0;
    }

    /**
     * 校验Email是否注册
     */
    public boolean ajaxValidateEmail(String email) throws SQLException {
        String sql="select count(1) from t_user where email=?";
        Number number=(Number)qr.query(sql,new ScalarHandler(),email);
        System.out.println(number);
        return number.intValue()==0;
    }

    /**
     * 添加用户数据
     * @param user
     */
    public void add(User user) throws SQLException{
        String sql="insert into t_user values(?,?,?,?,?,?)";
        Object[] params={
                user.getUid(),
                user.getLoginname(),
                user.getLoginpass(),
                user.getEmail(),
                user.isStatus(),
                user.getActivationCode()
        };
        qr.update(sql,params);
    }

    /**
     * 通过激活码查询用户
     * @param code
     * @return
     * @throws SQLException
     */
    public User findByCode(String code) throws SQLException{
        String sql="select * from t_user where activationCode=?";
        return qr.query(sql,new BeanHandler<User>(User.class),code);
    }

    /**
     * 修改激活状态
     * @param uid
     * @param status
     * @throws SQLException
     */
    public void updateStatus(String uid,boolean status) throws SQLException {
        String sql="update t_user set status=? where uid=?";
        qr.update(sql,status,uid);
    }

    /**
     * 按用户名和密码查询用户
     * @param loginname
     * @param loginpass
     * @return
     */
    public User findByLoginnameandLoginpass(String loginname,String loginpass) throws SQLException{
        String sql="select * from t_user where loginname=? and loginpass=?";
        return qr.query(sql,new BeanHandler<User>(User.class),loginname,loginpass);
    }

    /**
     * 按uid和password查询
     * @param uid
     * @param password
     * @return
     */
    public boolean findByUidAndPassword(String uid,String password) throws SQLException {
        String sql="select count(*) from t_user where uid=? and loginpass=?";
        Number number= (Number) qr.query(sql, new ScalarHandler(),uid,password);
        return number.intValue()>0;
    }

    /**
     * 修改密码
     * @param uid
     * @param password
     * @throws SQLException
     */
    public void updatePassword(String uid,String password) throws SQLException {
        String sql="update t_user set loginpass=? where uid=?";
        qr.update(sql,password,uid);
    }
}
