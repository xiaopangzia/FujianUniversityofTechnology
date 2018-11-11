import javax.mail.MessagingException;
import javax.mail.Session;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import org.junit.Test;

import java.io.IOException;


/*
 *发邮件
 * mail.java activation.java
 */
public class MailTest {

    @Test
    public void send() throws IOException, MessagingException {
        /*
         * 1.登陆邮件服务器
         *  mailUtil.creatSession服务器 登录名 密码
         * 2.创建邮件对象
         * 发件人 收件人 主题 正文
         * 3.发 需要第一步的session 和第二步邮件对象
         */
        Session session=MailUtils.createSession("smtp.163.com","antices","antice s123");
        Mail mail=new Mail("antices@163.com","739237663@qq.com","测试邮件一封","111");
        MailUtils.send(session,mail);
    }
}
