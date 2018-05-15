package com.heyu.framework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 增删改查dao层
 * @param <T>
 */
public interface CurdDao<T> {

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public T findById(@Param(value="id")String id);

	/**
	 * 查询实体列表-分页
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);

	/**
	 * 根据条件查询所有实体列表
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity);

	/**
	 * 查询所有实体
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 插入实体
	 * @param entity
	 * @return
	 */
	public int insert(T entity);

	/**
	 * 更新实体
	 * @param entity
	 * @return
	 */
	public int update(T entity);

	/**
	 * 删除实体
	 * @param id
	 * @return
	 */
	public int delete(@Param(value="id")String id);

	/**
	 * 根据ids批量删除实体
	 * @param ids
	 * @return
	 */
	public int deleteBatch(@Param(value="ids")String[] ids);
}
