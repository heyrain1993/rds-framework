package com.heyu.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heyu.framework.model.Page;
import com.heyu.framework.service.BaseService;
import com.heyu.framework.utils.StringUtils;
import com.heyu.framework.utils.ValidatorUtil;
import com.heyu.framework.dao.SysUserDao;
import com.heyu.framework.exception.CommonException;
import com.heyu.framework.model.SysUser;
/**
 * 
 * @author heyu
 * @email 614457294@qq.com
 * @date 2018-05-15 16:20:57
 *
 */
@Service
public class SysUserService extends BaseService{
	@Autowired
	protected SysUserDao sysUserDao;

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public SysUser findById(String id) {
		return this.sysUserDao.findById(id);
	}

	/**
	 * 查询实体-不分页
	 * @param sysUser
	 * @return
	 */
	public List<SysUser> findList(SysUser sysUser){
		return this.sysUserDao.findAllList(sysUser);
	}

	/**
	 * 查询实体-分页
	 * 根据page中的pageNo和pageSize获取记录以及总记录数等
	 * 再将实体记录设置进page，并返回page
	 * @param page
	 * @param sysUser
	 * @return
	 */
	public Page<SysUser> findPage(Page<SysUser> page, SysUser sysUser){
		sysUser.setPage(page);
		List<SysUser> list = this.sysUserDao.findList(sysUser);
		page.setList(list);
		return page;
	}

	/**
	 * 保存实体-判断是否是新纪录(新纪录：调用preInsert；旧记录：调用preUpdate)
	 * @param sysUser
	 * @return
	 */
	public int save(SysUser sysUser) {
		if(sysUser.isNewRecord()){
			sysUser.preInsert();
			ValidatorUtil.validateEntity(sysUser);
			return this.sysUserDao.insert(sysUser);

		}else{
			sysUser.preUpdate();
			ValidatorUtil.validateEntity(sysUser);
			return this.sysUserDao.update(sysUser);
		}
	}

	/**
	 * 删除实体
	 * @param id
	 * @return
	 */
	public int delete(String id) {
		return this.sysUserDao.delete(id);
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
		if (this.sysUserDao.deleteBatch(ids) == 0){
			return false;
		}
		return true;
	}

	public SysUser findByUsername(String username) {
		if(StringUtils.isEmpty(username)) {
			throw new CommonException("用户名为空");
		}
		return this.sysUserDao.findByUsername(username);
	}
}
