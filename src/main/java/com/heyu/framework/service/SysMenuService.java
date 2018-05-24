package com.heyu.framework.service;

import java.util.List;

import com.heyu.framework.utils.JsonUtils;
import com.heyu.framework.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heyu.framework.model.Page;
import com.heyu.framework.utils.ValidatorUtil;
import com.heyu.framework.dao.SysMenuDao;
import com.heyu.framework.model.SysMenu;
/**
 * 
 * @author heyu
 * @email 614457294@qq.com
 * @date 2018-05-23 14:06:11
 *
 */
@Service
public class SysMenuService extends TreeService<SysMenu>{

	@Autowired
	protected SysMenuDao sysMenuDao;

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public SysMenu findById(String id) {
		return this.sysMenuDao.findById(id);
	}


	/**
	 * 查询实体-不分页
	 * @param sysMenu
	 * @return
	 */
	public List<SysMenu> findList(SysMenu sysMenu){
		List<SysMenu> list = this.sysMenuDao.findAllList(sysMenu);
		List<SysMenu> treeList = build(list);
		System.out.println(JsonUtils.toString(treeList));
		return treeList;
	}

	/**
	 * 查询实体-分页
	 * 根据page中的pageNo和pageSize获取记录以及总记录数等
	 * 再将实体记录设置进page，并返回page
	 * @param page
	 * @param sysMenu
	 * @return
	 */
	public Page<SysMenu> findPage(Page<SysMenu> page, SysMenu sysMenu){
		List<SysMenu> lista = this.sysMenuDao.findAllList(sysMenu);
		List<SysMenu> treeList = build(lista);
		System.out.println(JsonUtils.toString(treeList));
		sysMenu.setPage(page);
		List<SysMenu> list = this.sysMenuDao.findList(sysMenu);
		page.setList(list);
		return page;
	}

	/**
	 * 保存实体-判断是否是新纪录(新纪录：调用preInsert；旧记录：调用preUpdate)
	 * @param sysMenu
	 * @return
	 */
	public int save(SysMenu sysMenu) {
		SysMenu parent = new SysMenu();
		/*
		创建顶级菜单：id&parentId为空，parentId直接赋值为0
		创建次级菜单：id为空，parentId不为空，parentId赋值为parent的id+parentIds
		修改时：id不为空，parentId和parentIds不需要更新
		 */
		if(sysMenu != null && !StringUtils.isEmpty(sysMenu.getParentId()) && StringUtils.isEmpty(sysMenu.getId())){//创建子级菜单
			parent = this.sysMenuDao.findById(sysMenu.getParentId());
			if(parent != null && parent.getParentIds() != null){
				sysMenu.setParentIds(parent.getParentIds() + "," + parent.getParentId());
			}else{
				sysMenu.setParentIds(sysMenu.getParentId());
			}
		}else if(sysMenu != null && StringUtils.isEmpty(sysMenu.getParentId())){//创建顶级菜单
			sysMenu.setParentId("0");
		}else{

		}
		if(sysMenu.isNewRecord()){
			sysMenu.preInsert();
			ValidatorUtil.validateEntity(sysMenu);
			return this.sysMenuDao.insert(sysMenu);

		}else{
			sysMenu.preUpdate();
			ValidatorUtil.validateEntity(sysMenu);
			return this.sysMenuDao.updateByPrimaryKeySelective(sysMenu);
		}
	}

	/**
	 * 删除实体
	 * @param id
	 * @return
	 */
	public int delete(String id) {
		return this.sysMenuDao.delete(id);
	}

	/**
	 * 根据ids批量删除实体
	 * @param ids
	 * @return
	 */
	public boolean deleteBatch(String[] ids) {
		if (ids == null || ids.length == 0){
			return false;
		}
		if (this.sysMenuDao.deleteBatch(ids) == 0){
			return false;
		}
		return true;
	}
}
