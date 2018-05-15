package com.heyu.framework.model;

import javax.validation.constraints.NotBlank;
import com.heyu.framework.model.DataEntity;

public class SysRole extends DataEntity<SysRole>{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
			
		/**
	 * 角色名称
	 */
		
	private String name;
		/**
	 * 角色类型
	 */
		
	private String type;
		/**
	 * 创建者
	 */
			
		/**
	 * 创建时间
	 */
			
		/**
	 * 更新者
	 */
			
		/**
	 * 更新时间
	 */
			
		/**
	 * 备注
	 */
			
		/**
	 * 删除标记
	 */
			
		
			
			/**
	 * 角色名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setName(String name) {
		this.name = name;
	}
			/**
	 * 角色类型
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setType(String type) {
		this.type = type;
	}
				
				
				
				
				
				
		
}
