package cn.wakeupeidolon.label.comment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Wang Yu
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    
    @Value("${config.redis.host}")
    private String host;
    @Value("${config.redis.port}")
    private int port;
    @Value("${config.redis.timeout}")
    private int timeout;
    @Value("${config.redis.password}")
    private String password;
    @Value("${config.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${config.redis.pool.min-idle}")
    private int minIdle;
    @Value("${config.redis.pool.max-wait-millis}")
    private int maxWaitMillis;
    @Value("${config.redis.pool.max-active}")
    private int maxActive;
    
    
    private Logger LOG = LoggerFactory.getLogger(RedisConfig.class);
    
    @Bean
    public ShardedJedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxActive);
        JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port);
        jedisShardInfo.setPassword(password);
        List<JedisShardInfo> list = new LinkedList<>();
        list.add(jedisShardInfo);
        LOG.info("ShardedJedisPool注入成功 ---- redis地址: " + host + ":" + port);
        return new ShardedJedisPool(jedisPoolConfig, list);
    }

}
