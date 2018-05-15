package com.heyu.framework.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.heyu.framework.dao.CurdDao;
import com.heyu.framework.model.SysUser;

@Mapper
public interface SysUserDao extends CurdDao<SysUser>{

	SysUser findByUsername(@Param("username")String username);
}
