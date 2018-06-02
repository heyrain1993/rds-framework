package com.heyu.framework.dao;

import com.heyu.framework.model.SysRole;
import org.apache.ibatis.annotations.Mapper;

import com.heyu.framework.dao.CurdDao;
import com.heyu.framework.model.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPermissionDao extends CurdDao<SysPermission>{

    List<SysPermission> findByUserId(@Param("userId") String userId);
}
