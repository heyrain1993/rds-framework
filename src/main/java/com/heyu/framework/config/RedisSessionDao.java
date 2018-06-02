package com.heyu.framework.config;

import com.heyu.framework.service.RedisService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class RedisSessionDao extends EnterpriseCacheSessionDAO {

    @Autowired
    private RedisService<String,Object>  redisService;

    private static final String PREFIX = "shiro_session";

    private static Long expire = 3600L;

    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        String key = PREFIX + session.getId().toString();
        if(!redisService.exists(key)){
            redisService.set(key,session);
        }
        redisService.expire(key,expire,TimeUnit.SECONDS);

    }

    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        redisService.delete(PREFIX + session.getId());
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        redisService.set(PREFIX + sessionId.toString(),session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {

        Session session = super.doReadSession(serializable);
        if(session == null){
            session = (Session) redisService.get(PREFIX + serializable.toString());
        }
        return session;
    }

}
