package cn.wakeupeidolon.label.comment.controller.vo.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Wang Yu
 */
public class PasswordVO implements Serializable {
    @NotBlank
    @NotNull
    @ApiModelProperty("用户ID")
    private String id;
    @NotBlank
    @NotNull
    @ApiModelProperty("新密码")
    private String password;
    @NotBlank
    @NotNull
    @ApiModelProperty("邮箱")
    private String email;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
