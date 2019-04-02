package cn.wakeupeidolon.label.comment.controller.vo.response;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.sql.Timestamp;

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
    private Timestamp createDate;
    private Timestamp updateDate;
    
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
    
    public Timestamp getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    
    public Timestamp getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }
}
