package com.heyu.framework.entity;

import java.io.Serializable;
import java.util.Date;

import com.heyu.framework.utils.IdUtils;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class DataEntity<T> extends BaseEntity<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected String delFlag;//删除标记
	
	protected String remark;//备注
	
	protected Date createTime;//创建时间
	
	protected Date updateTime;//更新时间

	@JsonIgnore
	@Length(min = 1, max = 1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Length(min = 0,max = 255)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public DataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL; 
	}
	
	public DataEntity(String id) {
		super(id);
		this.delFlag =  DEL_FLAG_NORMAL;
	}
	
	@Override
	public void preInsert() {
		if(this.isNewRecord()){
			setId(IdUtils.uuid());
		}
		this.createTime = new Date();
		this.updateTime = this.createTime;
	}
	
	@Override
	public void preUpdate() {
		this.updateTime = new Date();
	}
}
