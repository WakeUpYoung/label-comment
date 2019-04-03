package cn.wakeupeidolon.label.comment.controller.vo.response;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Wang Yu
 */
@ApiModel("商品")
public class CommodityVO implements Serializable {
    
    private Long id;
    private String commodityName;
    private Double commodityRate;
    private Double favorableRate;
    private Integer totalComment;
    private String itemId;
    private Integer type;
    private Date createDate;
    private Date updateDate;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public String getItemId() {
        return itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Date getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
