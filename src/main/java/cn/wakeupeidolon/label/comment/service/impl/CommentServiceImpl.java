package cn.wakeupeidolon.label.comment.service.impl;

import cn.wakeupeidolon.label.comment.dao.CommentDao;
import cn.wakeupeidolon.label.comment.entity.Comment;
import cn.wakeupeidolon.label.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
