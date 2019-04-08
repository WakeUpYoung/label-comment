package cn.wakeupeidolon.label.comment.controller.vo.request;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @author Wang Yu
 */
@ApiModel("用于登录")
public class LoginVO implements Serializable {
    
    private String username;
    
    private String password;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
