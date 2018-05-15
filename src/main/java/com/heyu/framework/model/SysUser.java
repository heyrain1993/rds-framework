package com.heyu.framework.model;

import javax.validation.constraints.NotBlank;
import com.heyu.framework.model.DataEntity;

public class SysUser extends DataEntity<SysUser>{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
			
		/**
	 * 真实姓名
	 */
		
	private String realName;
		/**
	 * 用户名
	 */
		
	private String username;
		/**
	 * 密码
	 */
		
	private String password;
		/**
	 * 头像
	 */
		
	private String portrait;
		/**
	 * 
	 */
		
	private String salt;
		/**
	 * 邮箱
	 */
		
	private String email;
		/**
	 * 手机号
	 */
		
	private String phone;
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
	 * 真实姓名
	 */
	public String getRealName() {
		return realName;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
			/**
	 * 用户名
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setUsername(String username) {
		this.username = username;
	}
			/**
	 * 密码
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setPassword(String password) {
		this.password = password;
	}
			/**
	 * 头像
	 */
	public String getPortrait() {
		return portrait;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
			/**
	 * 
	 */
	public String getSalt() {
		return salt;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
			/**
	 * 邮箱
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setEmail(String email) {
		this.email = email;
	}
			/**
	 * 手机号
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
				
				
				
				
				
				
		
}
