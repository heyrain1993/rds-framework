package com.heyu.framework.service;

import java.util.List;

import com.heyu.framework.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heyu.framework.model.Page;
import com.heyu.framework.service.BaseService;
import com.heyu.framework.utils.ValidatorUtil;
import com.heyu.framework.dao.SysRoleDao;
import com.heyu.framework.model.SysRole;
/**
 * 
 * @author heyu
 * @email 614457294@qq.com
 * @date 2018-05-16 14:41:55
 *
 */
@Service
public class SysRoleService extends BaseService{
	@Autowired
	protected SysRoleDao sysRoleDao;

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public SysRole findById(String id) {
		return this.sysRoleDao.findById(id);
	}


	/**
	 * 查询实体-不分页
	 * @param sysRole
	 * @return
	 */
	public List<SysRole> findList(SysRole sysRole){
		return this.sysRoleDao.findAllList(sysRole);
	}

	/**
	 * 查询实体-分页
	 * 根据page中的pageNo和pageSize获取记录以及总记录数等
	 * 再将实体记录设置进page，并返回page
	 * @param page
	 * @param sysRole
	 * @return
	 */
	public Page<SysRole> findPage(Page<SysRole> page, SysRole sysRole){
		sysRole.setPage(page);
		List<SysRole> list = this.sysRoleDao.findList(sysRole);
		page.setList(list);
		return page;
	}

	/**
	 * 保存实体-判断是否是新纪录(新纪录：调用preInsert；旧记录：调用preUpdate)
	 * @param sysRole
	 * @return
	 */
	public int save(SysRole sysRole) {
		if(sysRole.isNewRecord()){
			sysRole.preInsert();
			ValidatorUtil.validateEntity(sysRole);
			return this.sysRoleDao.insert(sysRole);

		}else{
			sysRole.preUpdate();
			ValidatorUtil.validateEntity(sysRole);
			return this.sysRoleDao.update(sysRole);
		}
	}

	/**
	 * 删除实体
	 * @param sysRole
	 * @return
	 */
	public int delete(String id) {
		return this.sysRoleDao.delete(id);
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
		if (this.sysRoleDao.deleteBatch(ids) == 0){
			return false;
		}
		return true;
	}

    public List<SysRole> findByUserId(String userId) {
		if (StringUtils.isEmpty(userId)){
			return null;
		}
		return sysRoleDao.findByUserId(userId);

    }
}
