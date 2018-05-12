package com.heyu.framework.service;

import java.util.List;

import com.heyu.framework.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.heyu.framework.dao.CurdDao;
import com.heyu.framework.entity.DataEntity;

public class CurdService<D extends CurdDao<T>,T extends DataEntity<T>>{

	@Autowired
	protected D dao;

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public T findById(String id) {
		return this.dao.findById(id);
	}

	/**
	 * 查询实体类
	 * @param entity
	 * @return
	 */
	public T find(T entity) {
		return this.dao.find(entity);
	}

	/**
	 * 查询实体-不分页
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity){
		return this.dao.findAllList(entity);
	}

	/**
	 * 查询实体-分页
	 * 根据page中的pageNo和pageSize获取记录以及总记录数等
	 * 再将实体记录设置进page，并返回page
	 * @param page
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity){
		entity.setPage(page);
		List<T> list = this.dao.findList(entity);
		page.setList(list);
		return page;
	}

	/**
	 * 保存实体-判断是否是新纪录(新纪录：调用preInsert；旧记录：调用preUpdate)
	 * @param entity
	 * @return
	 */
	public int save(T entity) {
		if(entity.isNewRecord()){
			entity.preInsert();
			return this.dao.insert(entity);

		}else{
			entity.preUpdate();
			return this.dao.update(entity);
		}
	}

	/**
	 * 删除实体
	 * @param entity
	 * @return
	 */
	public int delete(T entity) {
		return this.dao.delete(entity);
	}

	/**
	 * 根据ids批量删除实体
	 * @param ids
	 * @return
	 */
	public boolean deleteBatch(String[] ids) {
		if (ids == null || ids.length == 0){
			return false;
		}
		if (this.dao.deleteBatch(ids) == 0){
			return false;
		}
		return true;
	}
}
