package cn.wakeupeidolon.label.comment.controller.vo.request;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Wang Yu
 */
@ApiModel("用于登录")
public class LoginVO implements Serializable {
    
    @NotNull
    @NotBlank
    @Email(message = "邮箱格式非法")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    @NotNull(message = "密码不能为空")
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
