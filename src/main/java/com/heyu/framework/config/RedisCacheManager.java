package com.heyu.framework.config;

import com.heyu.framework.service.RedisService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义shiro中的缓存管理器，使用redis缓存
 */
public class RedisCacheManager implements CacheManager {

    @Autowired
    private RedisService<String,Object> redisService;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new RedisCache<>(name,redisService);
    }

    public RedisService<String, Object> getRedisService() {
        return redisService;
    }

    public void setRedisService(RedisService<String, Object> redisService) {
        this.redisService = redisService;
    }
}
