package com.heyu.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.heyu.framework.dao.CurdDao;
import com.heyu.framework.entity.DataEntity;
import com.heyu.framework.entity.Page;

public class CurdService<D extends CurdDao<T>,T extends DataEntity<T>>{

	@Autowired
	protected D dao;
	
	public T findById(String id) {
		return this.dao.findById(id);
	}
	
	public T find(T entity) {
		return this.dao.find(entity);
	}
	
	public List<T> findList(T entity){
		return this.dao.findList(entity);
	}
	
	public List<T> findPage(Page<T> page,T entity){
		entity.setPage(page);
		return this.dao.findList(entity);
	}
	
	public int insert(T entity) {
		return this.dao.insert(entity);
	}
	
	public int update(T entity) {
		return this.dao.update(entity);
	}
	
	public int delete(T entity) {
		return this.dao.delete(entity);
	}
	
	public int deleteBatch(String[] ids) {
		return this.dao.deleteBatch(ids);
	}
}
