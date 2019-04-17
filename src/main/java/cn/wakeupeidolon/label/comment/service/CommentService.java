package cn.wakeupeidolon.label.comment.service;

import cn.wakeupeidolon.label.comment.controller.vo.request.LabelVO;
import cn.wakeupeidolon.label.comment.entity.Comment;

import java.util.List;

/**
 * @author Wang Yu
 */
public interface CommentService extends BaseService<Comment, Long>{
    /**
     * 根据用户回传信息更新可信度
     */
    int updateBelievable(LabelVO vo);
    
    /**
     * 根据limit返回评论
     */
    List<Comment> findCommentLimit(long min, long total);
    
    long countAll();
}
