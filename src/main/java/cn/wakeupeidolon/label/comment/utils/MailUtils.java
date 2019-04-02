package cn.wakeupeidolon.label.comment.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @author Wang Yu
 * 发送邮件
 */
public class MailUtils {
    
    private MailUtils(){}
    
    public static boolean sendEmail(String to){
    
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
            return false;
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
            message.setSubject("This is the Subject Line!");
        
            // 发送 HTML 消息, 可以插入html标签
            message.setContent("<h1>This is actual message</h1>",
                    "text/html" );
        
            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully!");
        }catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }
}
