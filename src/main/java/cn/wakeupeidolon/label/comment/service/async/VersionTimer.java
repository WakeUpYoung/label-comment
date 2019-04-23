package cn.wakeupeidolon.label.comment.service.async;

import cn.wakeupeidolon.label.comment.config.RedisConfig;
import cn.wakeupeidolon.label.comment.domain.Version;
import cn.wakeupeidolon.label.comment.utils.VersionUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

/**
 * @author Wang Yu
 */
@Component
@EnableScheduling
@Async("asyncExecutor")
public class VersionTimer {
    
    private final RedisConfig redisConfig;
    
    private static final Logger LOG = LoggerFactory.getLogger(VersionTimer.class);
    
    @Autowired
    public VersionTimer(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }
    
    @Scheduled(cron = "59 59 23 ? * *")
    public void updateVersion(){
        try{
            Version version = VersionUtils.get();
            if (version != null){
                ShardedJedis jedis = redisConfig.redisPoolFactory().getResource();
                Version jedisVersion = new Version();
                String jedisVersionJson = jedis.get("version");
                if (jedisVersionJson != null){
                    jedisVersion = JSONObject.parseObject(jedisVersionJson, Version.class);
                }
                // 如果在内存中的version不等于最新的version
                if (!version.getLasted().equals(jedisVersion.getLasted())){
                    //    更新内存中的version
                    jedis.set("version", JSONObject.toJSONString(version));
                    LOG.info("检测到已更新版本, 同步版本成功");
                }
            }else {
                LOG.warn("version文件为空!");
            }
        }catch (Exception e){
            LOG.error(e.getMessage());
        }
    }
}
