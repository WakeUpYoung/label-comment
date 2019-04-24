package cn.wakeupeidolon.label.comment.service;

import cn.wakeupeidolon.label.comment.entity.User;

import java.util.Date;

/**
 * @author Wang Yu
 */
public interface UserService extends BaseService<User, String> {
    boolean checkEmail(String email);
    
    User findByEmail(String email);
    
    int updateLoginDate(Date loginTime, String id);
}
