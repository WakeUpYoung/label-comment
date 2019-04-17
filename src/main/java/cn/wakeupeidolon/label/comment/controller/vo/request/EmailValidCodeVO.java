package cn.wakeupeidolon.label.comment.controller.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Wang Yu
 */
@ApiModel("邮箱验证码")
public class EmailValidCodeVO implements Serializable {
    @NotBlank(message = "邮箱不能为空")
    @NotNull(message = "邮箱不能为空")
    @ApiModelProperty("邮箱")
    private String email;
    @NotBlank(message = "验证码不能为空")
    @NotNull(message = "验证码不能为空")
    @ApiModelProperty("邮箱验证码")
    private String validCode;
    
    public String getValidCode() {
        return validCode;
    }
    
    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
