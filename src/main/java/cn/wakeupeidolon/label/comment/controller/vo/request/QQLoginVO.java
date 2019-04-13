package cn.wakeupeidolon.label.comment.controller.vo.request;

import io.swagger.annotations.ApiModel;

/**
 * @author Wang Yu
 */
@ApiModel("QQ登录")
public class QQLoginVO {
    private String expiresIn;
    private String oauthConsumerKey;
    private String accessToken;
    private String openId;
    
    public String getExpiresIn() {
        return expiresIn;
    }
    
    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
    
    public String getOauthConsumerKey() {
        return oauthConsumerKey;
    }
    
    public void setOauthConsumerKey(String oauthConsumerKey) {
        this.oauthConsumerKey = oauthConsumerKey;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public String getOpenId() {
        return openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
