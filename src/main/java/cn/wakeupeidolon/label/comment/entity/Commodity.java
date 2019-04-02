package cn.wakeupeidolon.label.comment.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Wang Yu
 */
@Entity
@Table(name = "tb_commodity")
public class Commodity implements BaseBean{
    private Long id;
    private String commodityName;
    private Double commodityRate;
    private Double favorableRate;
    private Integer totalComment;
    private String itemId;
    private Integer type;
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
    @Column(name = "commodity_name")
    public String getCommodityName() {
        return commodityName;
    }
    
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    
    @Basic
    @Column(name = "commodity_rate")
    public Double getCommodityRate() {
        return commodityRate;
    }
    
    public void setCommodityRate(Double commodityRate) {
        this.commodityRate = commodityRate;
    }
    
    @Basic
    @Column(name = "favorable_rate")
    public Double getFavorableRate() {
        return favorableRate;
    }
    
    public void setFavorableRate(Double favorableRate) {
        this.favorableRate = favorableRate;
    }
    
    @Basic
    @Column(name = "total_comment")
    public Integer getTotalComment() {
        return totalComment;
    }
    
    public void setTotalComment(Integer totalComment) {
        this.totalComment = totalComment;
    }
    
    @Basic
    @Column(name = "item_id")
    public String getItemId() {
        return itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    
    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
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
        Commodity commodity = (Commodity) o;
        return id == commodity.id &&
                Objects.equals(commodityName, commodity.commodityName) &&
                Objects.equals(commodityRate, commodity.commodityRate) &&
                Objects.equals(favorableRate, commodity.favorableRate) &&
                Objects.equals(totalComment, commodity.totalComment) &&
                Objects.equals(itemId, commodity.itemId) &&
                Objects.equals(type, commodity.type) &&
                Objects.equals(createDate, commodity.createDate) &&
                Objects.equals(updateDate, commodity.updateDate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, commodityName, commodityRate, favorableRate, totalComment, itemId, type, createDate, updateDate);
    }
}
