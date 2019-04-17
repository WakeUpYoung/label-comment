package cn.wakeupeidolon.label.comment.dao;

import cn.wakeupeidolon.label.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Date;
import java.util.List;

/**
 * @author Wang Yu
 */
@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {
    
    @Modifying
    @Query("update Comment set believable=:believable, fake=:fake , updateDate=:updateDate where id=:id")
    int updateBelievable(@Param("id") Long id , @Param("believable") int believable, @Param("fake") int fake, @Param("updateDate")Date updateDate);
    
    @Query(value = "select id, commodity_id, premiere_comment, append_comment, believable, fake, create_date, update_date" +
            " from tb_comment " +
            "limit :min, :total"
            , nativeQuery = true)
    List<Comment> findCommentLimit(@Param("min") long min, @Param("total") long total);
    
    @Query(value = "select id, commodity_id, premiere_comment, append_comment, believable, fake, create_date, update_date " +
            "from tb_comment where id = :id for update ", nativeQuery = true)
    Comment findByIdLock(@Param("id") Long id);
    
}
