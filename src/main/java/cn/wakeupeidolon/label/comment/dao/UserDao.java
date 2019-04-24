package cn.wakeupeidolon.label.comment.dao;

import cn.wakeupeidolon.label.comment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author Wang Yu
 */
@Repository
public interface UserDao extends JpaRepository<User, String> {
    @Query
    int countByEmail(String email);
    
    @Query
    User findByEmail(String email);
    
    @Modifying
    @Query("update User set lastLoginTime=:loginTime where id=:id")
    int updateUserLoginDate(@Param("loginTime") Date loginTime,@Param("id") String id);
}
