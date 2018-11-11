package com.cheng.shop.user.service;

import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import com.cheng.shop.user.dao.UserDao;
import com.cheng.shop.user.domain.User;
import com.cheng.shop.user.service.exception.UserException;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;

/*
 *用户模块业务层
 */
public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * 用户名注册校验
     *
     * @param loginname
     * @return
     */
    public boolean ajaxValidateLoginname(String loginname) {
        try {
            return userDao.ajaxValidateLoginname(loginname);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Email注册验证
     *
     * @param email
     * @return
     */
    public boolean ajaxValidateEmail(String email) {
        try {
            return userDao.ajaxValidateEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 注册新用户
     *
     * @param user
     */
    public void regist(User user) {

        /**
         * 1.对user进行数据补全
         */
        user.setUid(CommonUtils.uuid());
        user.setActivationCode(CommonUtils.uuid() + CommonUtils.uuid());
        user.setStatus(false);

        try {

            /**
             * 2.向数据库添加记录
             */
            userDao.add(user);

            /**
             * 3.向用户注册邮箱地址发送“激活”邮件
             */
            //读取email模版中数据
            Properties props = new Properties();
            props.load(this.getClass().getClassLoader()
                    .getResourceAsStream("email_template.properties"));
            String host = props.getProperty("host");//获取邮件服务器地址
            String username = props.getProperty("username");//获取用户名
            String password = props.getProperty("password");//获取密码
            String from = props.getProperty("from");//获取发件人地址
            String to = user.getEmail();//获取收件人地址
            String subject = props.getProperty("subject");//获取主题
            //获取内容模版，替换其中的激活码
            //MessaheFormat.format方法会把第一个参数的｛0｝使用第二个参数替换
            String content = MessageFormat.format(props.getProperty("content"),
                    user.getActivationCode());
            //发送邮件
            Session session = MailUtils.createSession(host, username, password);
            Mail mail = new Mail(from, to, subject, content);
            MailUtils.send(session, mail);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 激活功能
     *
     * @param code
     */
    public void activation(String code) throws UserException {
        /**
         * 1.通过激活码查询用户
         * 2.如果User为null，说明是无效激活码，抛出异常，给出异常信息（“无效激活码”）
         * 3.查看用户状态是否为true，如果为true，抛出异常，给出异常信息（“您已经激活过了，请不要二次激活！”）
         * 4.修改用户状态为true
         */
        try {
            User user = userDao.findByCode(code);
            if (user == null) {
                throw new UserException("无效激活码！");
            }
            if (user.isStatus()) {
                throw new UserException("您已经激活过了，请不要二次激活！");
            }
            //修改状态
            userDao.updateStatus(user.getUid(), true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 登录功能
     *
     * @param user
     * @return
     */
    public User login(User user) {
        try {
            return userDao.findByLoginnameandLoginpass(user.getLoginname(), user.getLoginpass());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改密码
     *
     * @param uid
     * @param oldpass
     * @param newpass
     */
    public void updatePassword(String uid, String oldpass, String newpass) throws UserException {
        try {
            /**
             * 1.校验旧密码
             */
            boolean bool = userDao.findByUidAndPassword(uid, oldpass);
            if (!bool) {
                throw new UserException("旧密码错误！");
            }

            /**
             * 2.修改密码
             */
            userDao.updatePassword(uid, newpass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
