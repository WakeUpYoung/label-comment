package cn.wakeupeidolon.label.comment.controller.vo.request;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Wang Yu
 */
@ApiModel("修改邮箱")
public class EmailModifyVO implements Serializable {
    
    @NotNull
    @NotBlank
    @Email
    private String email;
    
    @NotNull
    @NotBlank
    private String validCode;
    
    @NotBlank
    @NotNull
    private String id;
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getValidCode() {
        return validCode;
    }
    
    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
}
