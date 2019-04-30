package cn.wakeupeidolon.label.comment.controller;

import cn.wakeupeidolon.label.comment.config.RedisConfig;
import cn.wakeupeidolon.label.comment.domain.Result;
import cn.wakeupeidolon.label.comment.domain.Version;
import cn.wakeupeidolon.label.comment.utils.VersionUtils;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

/**
 * @author Wang Yu
 */
@RestController
@RequestMapping("/version")
@Api(tags = {"版本控制"})
public class VersionController {
    
    private final RedisConfig redisConfig;
    
    @Autowired
    public VersionController(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }
    
    @GetMapping("/lasted")
    @ApiOperation("获取最新版本信息")
    public Result<Version> lasted(){
        try(ShardedJedis jedis = redisConfig.redisPoolFactory().getResource()){
            String version = jedis.get("version");
            Version lastedVersion = JSON.parseObject(version, Version.class);
            if (lastedVersion != null){
                return Result.success(lastedVersion);
            }
        }catch (RuntimeException e){
            return Result.error();
        }
        return Result.error();
    }
    
    @GetMapping("/update")
    @ApiOperation("立即更新")
    public Result<Version> update(){
        try(ShardedJedis jedis = redisConfig.redisPoolFactory().getResource()){
            Version version = VersionUtils.get();
            String json = JSON.toJSONString(version);
            jedis.set("version", json);
            return Result.success(version);
        }catch (RuntimeException e){
            return Result.error();
        }
    }

}
