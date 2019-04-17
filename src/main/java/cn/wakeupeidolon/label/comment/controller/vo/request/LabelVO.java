package cn.wakeupeidolon.label.comment.controller.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author Wang Yu
 */
@ApiModel("标签VO")
public class LabelVO {
    @ApiModelProperty("评论ID")
    @NotNull
    private Long commentId;
    @ApiModelProperty("是否可信")
    @NotNull
    private Boolean whetherBelievable;
    
    public Long getCommentId() {
        return commentId;
    }
    
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    
    public Boolean getWhetherBelievable() {
        return whetherBelievable;
    }
    
    public void setWhetherBelievable(Boolean whetherBelievable) {
        this.whetherBelievable = whetherBelievable;
    }
}
