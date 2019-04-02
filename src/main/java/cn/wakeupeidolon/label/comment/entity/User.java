package cn.wakeupeidolon.label.comment.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Wang Yu
 */
@Entity
@Table(name = "tb_user")
public class User implements BaseBean{
    private Long id;
    private String username;
    private String password;
    private String email;
    private String realName;
    private Date lastLoginTime;
    private Integer gender;
    private Byte isAdmin;
    private Date createDate;
    private Date updateDate;
    
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Basic
    @Column(name = "real_name")
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    @Basic
    @Column(name = "last_login_time")
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    
    @Basic
    @Column(name = "gender")
    public Integer getGender() {
        return gender;
    }
    
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    
    @Basic
    @Column(name = "is_admin")
    public byte getIsAdmin() {
        return isAdmin;
    }
    
    public void setIsAdmin(byte isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    @Basic
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    @Basic
    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isAdmin == user.isAdmin &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(realName, user.realName) &&
                Objects.equals(lastLoginTime, user.lastLoginTime) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(createDate, user.createDate) &&
                Objects.equals(updateDate, user.updateDate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, realName, lastLoginTime, gender, isAdmin, createDate, updateDate);
    }
}
