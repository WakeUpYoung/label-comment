package cn.wakeupeidolon.label.comment.service.async;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @author Wang Yu
 * 发送邮件
 */
@Component
public class EmailAsync {
    
    /**
     * 发送邮件
     * @param to 收件人
     * @param title 标题
     * @param content 内容
     */
    @Async("asyncExecutor")
    public void sendEmail(String to, String title, String content){
    
        // 发件人电子邮箱
        String from = "wangyu_onepach@qq.com";
    
        // 指定发送邮件的主机为 localhost
        String host = "smtp.qq.com";
    
        // 获取系统属性
        Properties properties = System.getProperties();
    
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        try {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    
        // 获取默认的 Session 对象。
        Session session = Session.getDefaultInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("wangyu_onepach@qq.com", "zpidbktcsqucbiaa");
            }
        });
    
        try{
            // 创建默认的 MimeMessage 对象。
            MimeMessage message = new MimeMessage(session);
        
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
        
            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
    
            // Set Subject: 标题
            message.setSubject(title, "UTF-8");
        
            // 发送 HTML 消息, 可以插入html标签
            message.setContent(content, "text/html;charset=UTF-8");
        
            // 发送消息
            Transport.send(message);
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
