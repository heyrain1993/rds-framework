package com.heyu.framework.model;

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
	
	protected Date createDate;//创建时间
	
	protected  String createBy;
	
	protected String updateBy;
	
	protected Date updateDate;//更新时间

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
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
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
		this.createDate = new Date();
		this.updateDate = this.createDate;
		this.isNewRecord = false;
	}
	
	@Override
	public void preUpdate() {
		this.updateDate = new Date();
	}
}
