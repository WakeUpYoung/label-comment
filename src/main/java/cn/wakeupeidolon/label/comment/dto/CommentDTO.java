package cn.wakeupeidolon.label.comment.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Wang Yu
 * 商品和评论连接对象
 */
public class CommentDTO implements Serializable {
    private Long commentId;
    private Long commodityId;
    private String premiereComment;
    private String appendComment;
    private String commodityName;
    private Double commodityRate;
    private Double favorableRate;
    private Integer totalComment;
    private Integer type;
    
    public Long getCommentId() {
        return commentId;
    }
    
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    
    public Long getCommodityId() {
        return commodityId;
    }
    
    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }
    
    public String getPremiereComment() {
        return premiereComment;
    }
    
    public void setPremiereComment(String premiereComment) {
        this.premiereComment = premiereComment;
    }
    
    public String getAppendComment() {
        return appendComment;
    }
    
    public void setAppendComment(String appendComment) {
        this.appendComment = appendComment;
    }
    
    public String getCommodityName() {
        return commodityName;
    }
    
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    
    public Double getCommodityRate() {
        return commodityRate;
    }
    
    public void setCommodityRate(Double commodityRate) {
        this.commodityRate = commodityRate;
    }
    
    public Double getFavorableRate() {
        return favorableRate;
    }
    
    public void setFavorableRate(Double favorableRate) {
        this.favorableRate = favorableRate;
    }
    
    public Integer getTotalComment() {
        return totalComment;
    }
    
    public void setTotalComment(Integer totalComment) {
        this.totalComment = totalComment;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
}
