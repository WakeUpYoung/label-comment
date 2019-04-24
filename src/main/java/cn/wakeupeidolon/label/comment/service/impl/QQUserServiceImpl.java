package cn.wakeupeidolon.label.comment.service.impl;

import cn.wakeupeidolon.label.comment.dao.QQUserDao;
import cn.wakeupeidolon.label.comment.dto.UserDTO;
import cn.wakeupeidolon.label.comment.entity.QQUser;
import cn.wakeupeidolon.label.comment.entity.User;
import cn.wakeupeidolon.label.comment.service.QQUserService;
import cn.wakeupeidolon.label.comment.service.UserService;
import cn.wakeupeidolon.label.comment.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wang Yu
 */
@Service
public class QQUserServiceImpl implements QQUserService {
    private final QQUserDao dao;
    private final UserService userService;
    
    @Autowired
    public QQUserServiceImpl(QQUserDao dao, UserService userService) {
        this.dao = dao;
        this.userService = userService;
    }
    
    @Override
    public boolean hasUser(String openId) {
        int i = dao.countByOpenId(openId);
        return i != 0;
    }
    
    @Override
    public QQUser save(QQUser bean) {
        return dao.save(bean);
    }
    
    @Override
    public Integer batchSave(List<QQUser> beanList) {
        return dao.saveAll(beanList).size();
    }
    
    @Override
    public QQUser update(QQUser bean) {
        if (bean.getId() == null){
            return null;
        }
        return dao.save(bean);
    }
    
    @Override
    public Boolean delete(Long aLong) {
        dao.deleteById(aLong);
        return Boolean.TRUE;
    }
    
    @Override
    public QQUser findById(Long aLong) {
        return dao.findById(aLong).orElse(null);
    }
    
    @Override
    public List<QQUser> findAll() {
        return dao.findAll();
    }
    
    @Override
    public QQUser findByOpenId(String openId) {
        return dao.findByOpenId(openId);
    }
    
    @Override
    @Transactional
    public UserDTO saveQQAndUser(User user, QQUser qq) {
        User save = userService.save(user);
        qq.setUserId(save.getId());
        QQUser qqSave = this.save(qq);
        UserDTO dto = BeanMapper.map(save, UserDTO.class);
        dto.setNickname(qqSave.getNickname());
        dto.setFigureurlQqSmall(qqSave.getFigureurlQqSmall());
        dto.setFigureurlQqBig(qqSave.getFigureurlQqBig());
        return dto;
    }
    
    @Override
    public QQUser findByUserId(String userId) {
        return dao.findByUserId(userId);
    }
}
