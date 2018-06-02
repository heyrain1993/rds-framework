package com.heyu.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heyu.framework.model.Page;
import com.heyu.framework.service.BaseService;
import com.heyu.framework.utils.ValidatorUtil;
import com.heyu.framework.dao.SysDictDao;
import com.heyu.framework.model.SysDict;
/**
 * 
 * @author heyu
 * @email 614457294@qq.com
 * @date 2018-05-25 15:09:58
 *
 */
@Service
public class SysDictService extends BaseService{
	@Autowired
	protected SysDictDao sysDictDao;

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public SysDict findById(String id) {
		return this.sysDictDao.findById(id);
	}


	/**
	 * 查询实体-不分页
	 * @param sysDict
	 * @return
	 */
	public List<SysDict> findList(SysDict sysDict){
		return this.sysDictDao.findAllList(sysDict);
	}

	/**
	 * 查询实体-分页
	 * 根据page中的pageNo和pageSize获取记录以及总记录数等
	 * 再将实体记录设置进page，并返回page
	 * @param page
	 * @param sysDict
	 * @return
	 */
	public Page<SysDict> findPage(Page<SysDict> page, SysDict sysDict){
		sysDict.setPage(page);
		List<SysDict> list = this.sysDictDao.findList(sysDict);
		page.setList(list);
		return page;
	}

	/**
	 * 保存实体-判断是否是新纪录(新纪录：调用preInsert；旧记录：调用preUpdate)
	 * @param sysDict
	 * @return
	 */
	public int save(SysDict sysDict) {
		if(sysDict.isNewRecord()){
			sysDict.preInsert();
			ValidatorUtil.validateEntity(sysDict);
			return this.sysDictDao.insert(sysDict);

		}else{
			sysDict.preUpdate();
			ValidatorUtil.validateEntity(sysDict);
			return this.sysDictDao.update(sysDict);
		}
	}

	/**
	 * 删除实体
	 * @param sysDict
	 * @return
	 */
	public int delete(String id) {
		return this.sysDictDao.delete(id);
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
		if (this.sysDictDao.deleteBatch(ids) == 0){
			return false;
		}
		return true;
	}
}
