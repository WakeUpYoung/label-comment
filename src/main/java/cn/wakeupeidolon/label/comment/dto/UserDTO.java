package cn.wakeupeidolon.label.comment.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Wang Yu
 */
public class UserDTO implements Serializable {
    private String id;
    private String username;
    private String email;
    private String realName;
    private Date lastLoginTime;
    private Integer gender;
    private Byte isAdmin;
    private Date createDate;
    private String nickname;
    private String figureurlQqSmall;
    private String figureurlQqBig;
    
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
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getFigureurlQqSmall() {
        return figureurlQqSmall;
    }
    
    public void setFigureurlQqSmall(String figureurlQqSmall) {
        this.figureurlQqSmall = figureurlQqSmall;
    }
    
    public String getFigureurlQqBig() {
        return figureurlQqBig;
    }
    
    public void setFigureurlQqBig(String figureurlQqBig) {
        this.figureurlQqBig = figureurlQqBig;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
}
