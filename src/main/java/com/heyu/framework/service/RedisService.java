package com.heyu.framework.service;

import com.heyu.framework.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService<K,V> {

    private RedisTemplate<K,V> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        this.redisTemplate = redisTemplate;
    }

    /**
     * 将数据存入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(K key,V value){
        boolean result = false;
        try {
            ValueOperations<K,V> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            result = true;

        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        return  result;
    }

    /**
     * 将数据存入缓存，并设置有效期，单位为秒
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public boolean set(K key,V value,Long expire){
        boolean result = false;
        try {
            ValueOperations<K,V> operations = redisTemplate.opsForValue();
            operations.set(key,value,expire,TimeUnit.SECONDS);
            result = true;

        }catch (Exception e){
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        }
        return  result;
    }

    /**
     * 从缓存中获取数据
     * @param key
     * @return
     */
    public Object get(K key){
        Object result = null;
        ValueOperations<K,V> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 根据key从缓存中删除该key-value
     * @param key
     */
    public void delete(K key){
        if(exists(key)){
            redisTemplate.delete(key);
        }
    }

    /**
     * 批量删除
     * @param pattern
     */
    public void deletePattern(K pattern){
        Set<K> keys = redisTemplate.keys(pattern);
        if (keys != null && keys.size() > 0){
            redisTemplate.delete(keys);
        }
    }
    /**
     * 判断缓存中 是否存在该key
     * @param key
     * @return
     */
    public boolean exists(K key){
        return redisTemplate.hasKey(key);
    }

    public void expire(K key,Long expireTime,TimeUnit unit){
        redisTemplate.expire(key,expireTime,unit);
    }
}
