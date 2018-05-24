package com.heyu.framework.model;

import javax.validation.constraints.NotBlank;
import com.heyu.framework.model.DataEntity;

public class SysMenu extends TreeEntity<SysMenu>{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
			
		/**
	 * 菜单名称
	 */
		
	private String name;
		/**
	 * 资源类型
	 */
		
	private String type;
		/**
	 * 资源路径
	 */
		
	private String url;

		/**
	 * 所有父级编号
	 */
		
	private String parentIds;
		/**
	 * 权限
	 */
		
	private String permission;
		/**
	 * 是否显示(0:显示；1:不显示)
	 */
		
	private String isShow;
		/**
	 * 排序
	 */
		
	private Integer sort;
		/**
	 * 创建者
	 */
			
		/**
	 * 创建时间
	 */
			
		/**
	 * 更新人
	 */
			
		/**
	 * 更新时间
	 */
			
		/**
	 * 备注
	 */
			
		/**
	 * 删除标记(0:正常；1:删除)
	 */
			
		
			
			/**
	 * 菜单名称
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
	 * 资源类型
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
			/**
	 * 资源路径
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
	 * 父编号
	 */
	public String getParentId() {
		return parentId;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
			/**
	 * 所有父级编号
	 */
	public String getParentIds() {
		return parentIds;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
			/**
	 * 权限
	 */
	public String getPermission() {
		return permission;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}
			/**
	 * 是否显示(0:显示；1:不显示)
	 */
	public String getIsShow() {
		return isShow;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
			/**
	 * 排序
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * ${column.comments}
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
				
				
				
				
				
				
		
}
