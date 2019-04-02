package cn.wakeupeidolon.label.comment.service.impl;

import cn.wakeupeidolon.label.comment.dao.CommodityDao;
import cn.wakeupeidolon.label.comment.entity.Commodity;
import cn.wakeupeidolon.label.comment.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author Wang Yu
 */
@Service
public class CommodityServiceImpl implements CommodityService{
    
    private final CommodityDao dao;
    
    @Autowired
    public CommodityServiceImpl(CommodityDao dao) {
        this.dao = dao;
    }
    
    @Override
    public Commodity save(Commodity bean) {
        return dao.save(bean);
    }
    
    @Override
    public Integer batchSave(List<Commodity> beanList) {
        return dao.saveAll(beanList).size();
    }
    
    @Override
    public Commodity update(Commodity bean) {
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
    public Commodity findById(Long id) {
        return dao.findById(id).orElse(null);
    }
    
    @Override
    public List<Commodity> findAll() {
        return dao.findAll();
    }
}
