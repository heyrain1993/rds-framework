package com.heyu.framework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CurdDao<T> {
	public T findById(@Param(value="id")String id);
	
	public T find(T entity);
	
	public List<T> findList(T entity);
	
	public List<T> findAllList(T entity);
	
	public List<T> findAll();
	
	public int insert(T entity);
	
	public int update(T entity);
	
	public int delete(T entity);
	
	public int deleteBatch(@Param(value="ids")String[] ids);
}
