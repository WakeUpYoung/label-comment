package cn.wakeupeidolon.label.comment.service.impl;

import cn.wakeupeidolon.label.comment.controller.vo.request.LabelVO;
import cn.wakeupeidolon.label.comment.dao.CommentDao;
import cn.wakeupeidolon.label.comment.entity.Comment;
import cn.wakeupeidolon.label.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * @author Wang Yu
 */
@Service
public class CommentServiceImpl implements CommentService{
    
    private final CommentDao dao;
    
    @Autowired
    public CommentServiceImpl(CommentDao dao) {
        this.dao = dao;
    }
    
    @Transactional
    @Override
    public int updateBelievable(LabelVO vo) {
        Comment comment = dao.findByIdLock(vo.getCommentId());
        int believable = comment.getBelievable();
        int fake = comment.getFake();
        if (vo.getWhetherBelievable()){
            believable ++;
        }else {
            fake ++;
        }
        return dao.updateBelievable(vo.getCommentId(), believable, fake, new Date());
    }
    
    @Override
    public List<Comment> findCommentLimit(long min, long total) {
        return dao.findCommentLimit(min, total);
    }
    
    @Override
    public long countAll() {
        return dao.count();
    }
    
    @Override
    public Comment save(Comment bean) {
        return dao.save(bean);
    }
    
    @Override
    public Integer batchSave(List<Comment> beanList) {
        return dao.saveAll(beanList).size();
    }
    
    @Override
    public Comment update(Comment bean) {
        if (!ObjectUtils.isEmpty(bean.getId())){
            return dao.save(bean);
        }
        return null;
    }
    
    @Override
    public Boolean delete(Long id) {
        if (ObjectUtils.isEmpty(id)){
            return Boolean.FALSE;
        }
        dao.deleteById(id);
        return Boolean.TRUE;
    }
    
    @Override
    public Comment findById(Long id) {
        return dao.findById(id).orElse(null);
    }
    
    @Override
    public List<Comment> findAll() {
        return dao.findAll();
    }
}
