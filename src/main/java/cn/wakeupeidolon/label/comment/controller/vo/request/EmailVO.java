package cn.wakeupeidolon.label.comment.controller.vo.request;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Wang Yu
 */
@ApiModel("邮箱")
public class EmailVO implements Serializable {
    @NotNull
    @NotBlank
    @Email(message = "邮箱格式不正确")
    private String email;
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
