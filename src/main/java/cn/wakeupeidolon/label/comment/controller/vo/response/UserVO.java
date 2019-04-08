package cn.wakeupeidolon.label.comment.controller.vo.response;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Wang Yu
 */
@ApiModel("用户")
public class UserVO implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String realName;
    private Date lastLoginTime;
    private Integer gender;
    private Byte isAdmin;
    private Date createDate;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    
    public Integer getGender() {
        return gender;
    }
    
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    
    public Byte getIsAdmin() {
        return isAdmin;
    }
    
    public void setIsAdmin(Byte isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
