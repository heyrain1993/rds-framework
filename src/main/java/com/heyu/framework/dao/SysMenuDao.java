package com.heyu.framework.dao;

import org.apache.ibatis.annotations.Mapper;

import com.heyu.framework.dao.CurdDao;
import com.heyu.framework.model.SysMenu;

@Mapper
public interface SysMenuDao extends TreeDao<SysMenu>{
}
