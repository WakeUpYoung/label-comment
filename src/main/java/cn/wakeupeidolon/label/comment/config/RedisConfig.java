package cn.wakeupeidolon.label.comment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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
    public JedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxActive);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout,null);
        LOG.info("JedisPool注入成功！");
        LOG.info("redis地址：" + host + ":" + port);
        return  jedisPool;
    }

}
