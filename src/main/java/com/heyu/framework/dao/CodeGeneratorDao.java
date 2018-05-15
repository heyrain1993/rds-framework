package com.heyu.framework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.heyu.framework.model.ColumnEntity;
import com.heyu.framework.model.TableEntity;

@Mapper
public interface CodeGeneratorDao {

	public List<TableEntity> findTableList(@Param(value="tableName")String tableName);

	public List<ColumnEntity> findColumnEntity(@Param(value="tableName")String tableName);
}
