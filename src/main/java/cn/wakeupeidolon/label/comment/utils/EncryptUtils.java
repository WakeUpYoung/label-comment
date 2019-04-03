package cn.wakeupeidolon.label.comment.utils;

import org.springframework.util.Assert;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Wang Yu
 */
public class EncryptUtils {
    private EncryptUtils(){
    }
    
    public static String md5(String msg){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        byte[] digest = md.digest(msg.getBytes());
        md.update(digest);
        BigInteger bigInteger = new BigInteger(md.digest(digest));
        return bigInteger.toString(16);
    }
}
