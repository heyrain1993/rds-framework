package com.heyu.framework.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;

public class JsonUtils {

    public static String toString(Object obj){
        if(obj !=  null){
            return JSON.toJSONString(obj);
        }
        return "{}";

    }
}
