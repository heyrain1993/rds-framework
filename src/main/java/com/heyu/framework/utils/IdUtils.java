package com.heyu.framework.utils;

import java.util.UUID;

/**
 * 生成ID
 */
public class IdUtils {

    /**
     * 生成UUID，并去掉中间的"_"
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
