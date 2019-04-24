package cn.wakeupeidolon.label.comment.dao;

import cn.wakeupeidolon.label.comment.entity.QQUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Wang Yu
 */
@Repository
public interface QQUserDao extends JpaRepository<QQUser, Long> {
    @Query
    int countByOpenId(String openId);
    @Query
    QQUser findByOpenId(String openId);
    @Query
    QQUser findByUserId(String userId);
}
