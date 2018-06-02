package com.heyu.framework.model;

import javax.validation.constraints.NotBlank;
import com.heyu.framework.model.DataEntity;

public class SysDict extends DataEntity<SysDict>{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
			
		/**
	 * 组编码
	 */
		
	private String parentCode;
		/**
	 * 编号
	 */
		
	private String code;
		/**
	 * 文本值
	 */
		
	private String value;
		/**
	 * 创建者
	 */
			
		/**
	 * 创建时间
	 */
			
		/**
	 * 修改人
	 */
			
		/**
	 * 修改时间
	 */
			
		/**
	 * 备注
	 */
			
		/**
	 * 删除标记
	 */
			
		
			
			/**
	 * 组编码
	 */
	public String getParentCode() {
		return parentCode;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
			/**
	 * 编号
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setCode(String code) {
		this.code = code;
	}
			/**
	 * 文本值
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setValue(String value) {
		this.value = value;
	}
				
				
				
				
				
				
		
}
