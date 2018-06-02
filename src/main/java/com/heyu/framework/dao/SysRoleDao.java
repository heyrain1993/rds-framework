package com.heyu.framework.dao;

import org.apache.ibatis.annotations.Mapper;

import com.heyu.framework.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleDao extends CurdDao<SysRole>{

    List<SysRole> findByUserId(@Param("userId") String userId);
}
