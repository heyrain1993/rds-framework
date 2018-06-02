package com.heyu.framework.model;

import javax.validation.constraints.NotBlank;
import com.heyu.framework.model.DataEntity;

public class SysPermission extends DataEntity<SysPermission>{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
			
		/**
	 * url地址
	 */
		
	private String url;
		/**
	 * url描述
	 */
		
	private String name;
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
	 * url地址
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setUrl(String url) {
		this.url = url;
	}
			/**
	 * url描述
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
				
				
				
				
				
				
		
}
