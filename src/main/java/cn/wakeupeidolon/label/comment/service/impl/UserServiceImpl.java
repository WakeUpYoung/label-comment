package cn.wakeupeidolon.label.comment.service.impl;

import cn.wakeupeidolon.label.comment.dao.UserDao;
import cn.wakeupeidolon.label.comment.entity.User;
import cn.wakeupeidolon.label.comment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * @author Wang Yu
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDao dao;
    
    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }
    
    @Override
    public User save(User bean) {
        bean.setIsAdmin((byte)0);
        bean.setCreateDate(new Date());
        bean.setUpdateDate(new Date());
        bean.setId(null);
        return dao.save(bean);
    }
    
    @Override
    public Integer batchSave(List<User> beanList) {
        return dao.saveAll(beanList).size();
    }
    
    @Override
    public User update(User bean) {
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
    public User findById(Long id) {
        return dao.findById(id).orElse(null);
    }
    
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }
    
    @Override
    public boolean checkEmail(String email) {
        return dao.countByEmail(email) == 0;
    }
}
