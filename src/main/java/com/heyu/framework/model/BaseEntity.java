package com.heyu.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heyu.framework.utils.StringUtils;
import org.hibernate.validator.constraints.Length;

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

	@JsonIgnore
	protected Page<T> page;

	@JsonIgnore
	protected boolean isNewRecord = true;

	public BaseEntity() {
		
	}
	
	public BaseEntity(String id) {
		this();
		this.id = id;
	}

	/**
	 * 判断是否为新纪录：id为空或isNewRecord为true表示新纪录
	 * @return
	 */
	public boolean isNewRecord() {
		return this.isNewRecord && StringUtils.isEmpty(getId());
	}

	public void setNewRecord(boolean newRecord) {
		isNewRecord = newRecord;
	}

	@Length(min = 1,max = 64)
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
