package com.heyu.framework.config;

import com.heyu.framework.service.RedisService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;

public class RedisCache<K,V> implements Cache<K,V> {

    @Autowired
    private RedisService redisService;

    private String cacheKey;

    private static Long expire = 3600L;

    private static final String PREFIX = "SHIRO_CACHE";

    public RedisCache(String name,RedisService redisService){
        this.redisService = redisService;
        this.cacheKey = PREFIX + name;
    }

    public K getCacheKey(Object k) {
        return (K)(this.cacheKey+k);
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    @Override
    public V get(K k) throws CacheException {
        return (V) redisService.get(getCacheKey(k));
    }

    @Override
    public V put(K k, V v) throws CacheException {
        redisService.set(getCacheKey(k),v,expire);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        V v = (V) redisService.get(getCacheKey(k));
        redisService.delete(getCacheKey(k));
        return v;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
