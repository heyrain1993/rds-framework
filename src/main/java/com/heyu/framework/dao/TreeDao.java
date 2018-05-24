package com.heyu.framework.dao;

import com.heyu.framework.model.TreeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TreeDao<T extends TreeEntity> extends CurdDao<T>{

    public void save(T t);

    public T findById(@Param("id") String id);
}
