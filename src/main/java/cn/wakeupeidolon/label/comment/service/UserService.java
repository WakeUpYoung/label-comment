package cn.wakeupeidolon.label.comment.service;

import cn.wakeupeidolon.label.comment.entity.User;

/**
 * @author Wang Yu
 */
public interface UserService extends BaseService<User, Long> {
    User register(User user);
}
