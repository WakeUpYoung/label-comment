package cn.wakeupeidolon.label.comment.dao;

import cn.wakeupeidolon.label.comment.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wang Yu
 */
@Repository
public interface CommodityDao extends JpaRepository<Commodity, Long> {

}
