package com.heyu.framework.service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.heyu.framework.utils.StringUtils;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heyu.framework.config.GeneratorConfig;
import com.heyu.framework.dao.CodeGeneratorDao;
import com.heyu.framework.model.ColumnEntity;
import com.heyu.framework.model.TableEntity;
import com.heyu.framework.utils.DateUtils;
import com.heyu.framework.utils.PropertiesUtils;

@Service
public class CodeGeneratorService {

	@Autowired
	private CodeGeneratorDao codeGeneratorDao;
	/**
	 * 查询所有的数据表列表
	 * @return
	 */
	public List<TableEntity> findTableEntity(){
		return codeGeneratorDao.findTableList(null);
	}

	/**
	 * 查询对应的所有列信息
	 * @param tableName
	 * @return
	 */
	public List<ColumnEntity> findColumnEntity(String tableName) {
		if(tableName == null || "".equals(tableName)) {
			return null;
		}
		return codeGeneratorDao.findColumnEntity(tableName);
	}

	public void download(String tableName, GeneratorConfig config, ZipOutputStream zip) {
		List<TableEntity> tableEntitys = codeGeneratorDao.findTableList(tableName);
		TableEntity tableEntity = null;
		if(tableEntitys != null && tableEntitys.size() != 0) {
			tableEntity = tableEntitys.get(0);
		}else {
			return;
		}
		tableEntity.setClassName(StringUtils.firstToUpper(StringUtils.underlineToHump(tableEntity.getTableName())));
		tableEntity.setClassname(StringUtils.underlineToHump(tableEntity.getTableName()));
		//设置列信息
		List<ColumnEntity> columns = tableEntity.getColumns();
		if(columns == null || columns.size() == 0) {
			return;
		}
		for(ColumnEntity column:columns) {
			column.setAttrname(StringUtils.underlineToHump(column.getColumnName()));
			column.setAttrName(StringUtils.firstToUpper(StringUtils.underlineToHump(column.getColumnName())));
			column.setAttrType(PropertiesUtils.getString(column.getDataType(), "generateProp.properties"));
		}
		
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		engine.init();
		
		Map<String,Object> map = new HashMap<>();
		map.put("tableName", tableEntity.getTableName());
		map.put("comments", tableEntity.getComment());
		map.put("className", tableEntity.getClassName());
		map.put("classname", tableEntity.getClassname());
		//map.put("pathName", tableEntity.getClassname().toLowerCase());
		map.put("columns", tableEntity.getColumns());
		map.put("package", config.getBasePackage());
		map.put("moduleName", config.getModuleName());
		map.put("email", config.getEmail());
		map.put("author", config.getAuthor());
		map.put("datetime",DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
		VelocityContext context = new VelocityContext(map);
		List<String> templates = new ArrayList<>();
		
		templates.add("velocity/Entity.java.vm");
		templates.add("velocity/Dao.java.vm");
		templates.add("velocity/Dao.xml.vm");
		templates.add("velocity/Service.java.vm");
		templates.add("velocity/Controller.java.vm");
		templates.add("velocity/list.html.vm");
		templates.add("velocity/form.html.vm");
		for(String template:templates) {
			StringWriter writer = new StringWriter();
			Template  t = engine.getTemplate(template, "UTF-8");
			t.merge(context, writer);
			if("velocity/form.html.vm".equals(template)) {
				System.out.println(writer.toString());
			}
			try {
				//添加到zip
				zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), 
						config.getBasePackage(), config.getModuleName())));
				IOUtils.write(writer.toString(), zip, "UTF-8");
				IOUtils.closeQuietly(writer);
				zip.closeEntry();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getFileName(String template, String className, String packageName, String moduleName) {
		String packagePath = "src" + File.separator  +"main" + File.separator + "java" + File.separator;
		if (!StringUtils.isEmpty(packageName)) {
			packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
		}

		if (template.contains("Entity.java.vm" )) {
			return packagePath + "model" + File.separator + className + ".java";
		}

		if (template.contains("Dao.java.vm" )) {
			return packagePath + "dao" + File.separator + className + "Dao.java";
		}

		if (template.contains("Service.java.vm" )) {
			return packagePath + "service" + File.separator + className + "Service.java";
		}

		if (template.contains("Controller.java.vm" )) {
			return packagePath + "controller" + File.separator + className + "Controller.java";
		}

		if (template.contains("Dao.xml.vm" )) {
			return "src"+ File.separator +"main" + File.separator + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Dao.xml";
		}

		if (template.contains("list.html.vm" )) {
			return "src"+ File.separator + "main" + File.separator + "resources" + File.separator + "templates" + File.separator
					+ "modules" + File.separator + moduleName + File.separator + className.toLowerCase() + "List.html";
		}

		if (template.contains("form.html.vm" )) {
			return "src"+ File.separator + "main" + File.separator + "resources" + File.separator + "templates" + File.separator 
					+ "modules" + File.separator + moduleName + File.separator + className.toLowerCase() + "Form.html";
		}

		return null;
	}
}
