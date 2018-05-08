package com.heyu.framework.entity;

import java.io.Serializable;

public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 删除标记:(0:正常;1:删除)
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	
	public static final String DEL_FLAG_DELETE = "1";
	
	/**
	 * 实体编号:唯一标识
	 */
	protected String id;
	
	protected Page<T> page;
	
	public BaseEntity() {
		
	}
	
	public BaseEntity(String id) {
		this();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

	/**
	 * 插入之前执行方法
	 */
	public abstract void preInsert();
	
	/**
	 * 更新之前执行方法
	 */
	public abstract void preUpdate();
}
