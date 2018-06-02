package com.heyu.framework.service;

import java.util.List;

import com.heyu.framework.model.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heyu.framework.model.Page;
import com.heyu.framework.service.BaseService;
import com.heyu.framework.utils.ValidatorUtil;
import com.heyu.framework.dao.SysPermissionDao;
import com.heyu.framework.model.SysPermission;
/**
 * 
 * @author heyu
 * @email 614457294@qq.com
 * @date 2018-05-30 19:48:47
 *
 */
@Service
public class SysPermissionService extends BaseService{
	@Autowired
	protected SysPermissionDao sysPermissionDao;

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public SysPermission findById(String id) {
		return this.sysPermissionDao.findById(id);
	}


	/**
	 * 查询实体-不分页
	 * @param sysPermission
	 * @return
	 */
	public List<SysPermission> findList(SysPermission sysPermission){
		return this.sysPermissionDao.findAllList(sysPermission);
	}

	/**
	 * 查询实体-分页
	 * 根据page中的pageNo和pageSize获取记录以及总记录数等
	 * 再将实体记录设置进page，并返回page
	 * @param page
	 * @param sysPermission
	 * @return
	 */
	public Page<SysPermission> findPage(Page<SysPermission> page, SysPermission sysPermission){
		sysPermission.setPage(page);
		List<SysPermission> list = this.sysPermissionDao.findList(sysPermission);
		page.setList(list);
		return page;
	}

	/**
	 * 保存实体-判断是否是新纪录(新纪录：调用preInsert；旧记录：调用preUpdate)
	 * @param sysPermission
	 * @return
	 */
	public int save(SysPermission sysPermission) {
		if(sysPermission.isNewRecord()){
			sysPermission.preInsert();
			ValidatorUtil.validateEntity(sysPermission);
			return this.sysPermissionDao.insert(sysPermission);

		}else{
			sysPermission.preUpdate();
			ValidatorUtil.validateEntity(sysPermission);
			return this.sysPermissionDao.update(sysPermission);
		}
	}

	/**
	 * 删除实体
	 * @param sysPermission
	 * @return
	 */
	public int delete(String id) {
		return this.sysPermissionDao.delete(id);
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
		if (this.sysPermissionDao.deleteBatch(ids) == 0){
			return false;
		}
		return true;
	}

    public List<SysPermission> findByUserId(String userId) {
		if (userId == null){
			return null;
		}
		return sysPermissionDao.findByUserId(userId);
    }
}
