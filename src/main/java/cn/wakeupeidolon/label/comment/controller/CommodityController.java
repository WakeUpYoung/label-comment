package cn.wakeupeidolon.label.comment.controller;


import cn.wakeupeidolon.label.comment.controller.vo.response.CommodityVO;
import cn.wakeupeidolon.label.comment.domain.Result;
import cn.wakeupeidolon.label.comment.entity.Commodity;
import cn.wakeupeidolon.label.comment.service.CommodityService;
import cn.wakeupeidolon.label.comment.utils.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang Yu
 */
@RestController
@RequestMapping("/commodity")
@Api(tags = {"商品Controller"})
public class CommodityController {
    
    private final CommodityService commodityService;
    
    @Autowired
    public CommodityController(CommodityService commodityService) {
        this.commodityService = commodityService;
    }
    
    @ApiOperation("获取单个商品")
    @GetMapping("/{id}")
    public Result<CommodityVO> get(@PathVariable("id") Long id){
        Commodity commodity = commodityService.findById(id);
        CommodityVO vo = BeanMapper.map(commodity, CommodityVO.class);
        return Result.success(vo);
    }
    
}
