package cn.wakeupeidolon.label.comment.service;

import java.util.List;

/**
 * @author Wang Yu
 */
public interface BaseService<T, ID> {
    T save(T bean);
    
    Integer batchSave(List<T> beanList);
    
    T update(T bean);
    
    Boolean delete(ID id);
    
    T findById(ID id);
    
    List<T> findAll();
}
