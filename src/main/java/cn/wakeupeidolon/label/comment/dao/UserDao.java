package cn.wakeupeidolon.label.comment.dao;

import cn.wakeupeidolon.label.comment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Wang Yu
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query
    int countByEmail(String email);
}
