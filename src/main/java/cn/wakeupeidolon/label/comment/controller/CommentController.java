package cn.wakeupeidolon.label.comment.controller;

import cn.wakeupeidolon.label.comment.controller.vo.request.LabelVO;
import cn.wakeupeidolon.label.comment.domain.Result;
import cn.wakeupeidolon.label.comment.dto.CommentDTO;
import cn.wakeupeidolon.label.comment.entity.Comment;
import cn.wakeupeidolon.label.comment.entity.Commodity;
import cn.wakeupeidolon.label.comment.service.CommentService;
import cn.wakeupeidolon.label.comment.service.CommodityService;
import cn.wakeupeidolon.label.comment.utils.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wang Yu
 */
@Api(tags = {"评论"})
@RestController
@RequestMapping("/comment")
public class CommentController {
    
    private final CommentService commentService;
    private final CommodityService commodityService;
    
    @Autowired
    public CommentController(CommentService commentService, CommodityService commodityService) {
        this.commentService = commentService;
        this.commodityService = commodityService;
    }
    
    @ApiOperation("为评论数据增加标签")
    @PostMapping("/label")
    public Result<Boolean> label(@RequestBody @Validated LabelVO labelVO){
        int count = commentService.updateBelievable(labelVO);
        return Result.success(count >= 1);
    }
    
    @ApiOperation("获取随机的评论详情列表")
    @GetMapping("/random/{count}")
    public Result<List<CommentDTO>> getRandomCommentList(@PathVariable("count") Long count){
        count = count > 20 ? 20 : count;
        long countAll = commentService.countAll();
        long randomLong = RandomUtils.nextLong(0, countAll - count);
        // 查询随机出来的评论列表
        List<Comment> commentList = commentService.findCommentLimit(randomLong, count);
        Set<Long> idSet = commentList.stream().map(Comment::getCommodityId).collect(Collectors.toSet());
        Map<Long, Commodity> commodityMap = new HashMap<>();
        for (Long id : idSet) {
            Commodity commodity = commodityService.findById(id);
            commodityMap.put(id, commodity);
        }
        // 转化DTO
        List<CommentDTO> dtoList = commentList.stream().map(comment -> {
            CommentDTO dto = new CommentDTO();
            dto.setCommentId(comment.getId());
            dto.setCommodityId(comment.getCommodityId());
            dto.setPremiereComment(comment.getPremiereComment());
            dto.setAppendComment(comment.getAppendComment());
            dto.setCommodityName(commodityMap.get(comment.getCommodityId()).getCommodityName());
            dto.setCommodityRate(commodityMap.get(comment.getCommodityId()).getCommodityRate());
            dto.setFavorableRate(commodityMap.get(comment.getCommodityId()).getFavorableRate());
            dto.setType(commodityMap.get(comment.getCommodityId()).getType());
            dto.setTotalComment(commodityMap.get(comment.getCommodityId()).getTotalComment());
            return dto;
        }).collect(Collectors.toList());
        return Result.success(dtoList);
    }
    
}
