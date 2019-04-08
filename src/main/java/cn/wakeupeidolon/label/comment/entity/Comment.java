package cn.wakeupeidolon.label.comment.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Wang Yu
 */
@Entity
@Table(name = "tb_comment")
public class Comment implements BaseBean<Long>{
    private Long id;
    private Long commodityId;
    private String premiereComment;
    private String appendComment;
    private Integer believable;
    private Integer fake;
    private Date createDate;
    private Date updateDate;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Basic
    @Column(name = "commodity_id")
    public Long getCommodityId() {
        return commodityId;
    }
    
    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }
    
    @Basic
    @Column(name = "premiere_comment")
    public String getPremiereComment() {
        return premiereComment;
    }
    
    public void setPremiereComment(String premiereComment) {
        this.premiereComment = premiereComment;
    }
    
    @Basic
    @Column(name = "append_comment")
    public String getAppendComment() {
        return appendComment;
    }
    
    public void setAppendComment(String appendComment) {
        this.appendComment = appendComment;
    }
    
    @Basic
    @Column(name = "believable")
    public Integer getBelievable() {
        return believable;
    }
    
    public void setBelievable(Integer believable) {
        this.believable = believable;
    }
    
    @Basic
    @Column(name = "fake")
    public Integer getFake() {
        return fake;
    }
    
    public void setFake(Integer fake) {
        this.fake = fake;
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
        Comment comment = (Comment) o;
        return id == comment.id &&
                commodityId == comment.commodityId &&
                believable == comment.believable &&
                fake == comment.fake &&
                Objects.equals(premiereComment, comment.premiereComment) &&
                Objects.equals(appendComment, comment.appendComment) &&
                Objects.equals(createDate, comment.createDate) &&
                Objects.equals(updateDate, comment.updateDate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, commodityId, premiereComment, appendComment, believable, fake, createDate, updateDate);
    }
}
