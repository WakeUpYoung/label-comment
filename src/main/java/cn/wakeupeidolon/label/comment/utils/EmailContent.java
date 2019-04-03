package cn.wakeupeidolon.label.comment.utils;

/**
 * @author Wang Yu
 */
public class EmailContent {
    private EmailContent(){
    }
    
    /**
     * 邮箱验证
     * @param code 验证码
     * @return html
     */
    public static String validation(String code){
        return "\t<p>感谢您注册Labeling Comment</p>\n" +
                "\t<p>您的验证码为:</p>\n" +
                "\t<p style=\"font-size: 30px;color: #3A5FCD;\">" + code + "</p>\n" +
                "\t<p>30分钟内有效</p>" +
                "\t<p>这封邮件由系统发出，请勿回复。</p>";
    }
}