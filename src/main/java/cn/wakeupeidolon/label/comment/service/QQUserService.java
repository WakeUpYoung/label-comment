package cn.wakeupeidolon.label.comment.service;

import cn.wakeupeidolon.label.comment.dto.UserDTO;
import cn.wakeupeidolon.label.comment.entity.QQUser;
import cn.wakeupeidolon.label.comment.entity.User;

/**
 * @author Wang Yu
 */
public interface QQUserService extends BaseService<QQUser, Long>{
    boolean hasUser(String openId);
    
    QQUser findByOpenId(String openId);
    
    UserDTO saveQQAndUser(User user, QQUser qq);
    
    QQUser findByUserId(String userId);
}
