package cn.wakeupeidolon.label.comment.controller;

import cn.wakeupeidolon.label.comment.config.RedisConfig;
import cn.wakeupeidolon.label.comment.domain.Result;
import cn.wakeupeidolon.label.comment.domain.Version;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

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
    public Result<Version> lasted(){
        Jedis jedis = redisConfig.redisPoolFactory().getResource();
        String version = jedis.get("version");
        Version lastedVersion = JSON.parseObject(version, Version.class);
        if (lastedVersion != null){
            return Result.success(lastedVersion);
        }
        return Result.error();
    }

}
