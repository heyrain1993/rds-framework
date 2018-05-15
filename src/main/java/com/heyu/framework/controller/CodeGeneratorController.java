package com.heyu.framework.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.heyu.framework.config.GeneratorConfig;
import com.heyu.framework.model.ColumnEntity;
import com.heyu.framework.model.TableEntity;
import com.heyu.framework.service.CodeGeneratorService;

@Controller
@RequestMapping(value = "/gencode")
public class CodeGeneratorController {

	@Autowired
	private CodeGeneratorService codeGeneratorService;
	
	/**
	 * 获取所有表信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "tables", method = RequestMethod.GET)
	public String findTableList(Model model) {
		List<TableEntity> tables = codeGeneratorService.findTableEntity();
		model.addAttribute("tables", tables);
		return "admin/genCode/tableList";
	}
	
	/**
	 * 获取所有对应表的字段信息
	 * @param model
	 * @param tableName
	 * @return
	 */
	@RequestMapping(value = "tableInfo", method = RequestMethod.GET)
	public String tableInfo(Model model,@RequestParam("tableName")String tableName) {
		List<ColumnEntity> columns = codeGeneratorService.findColumnEntity(tableName);
		model.addAttribute("columns", columns);
		model.addAttribute("tableName", tableName);
		return "admin/genCode/columnList";
	}
	
	/**
	 * 配置代码生成基础信息
	 * @param model
	 * @param tableName
	 * @return
	 */
	@RequestMapping(value = "tableInfo", method = RequestMethod.POST)
	public String genCode(Model model,@RequestParam("tableName")String tableName) {
		//List<ColumnEntity> columns = codeGeneratorService.findColumnEntity(tableName);
		model.addAttribute("tableName", tableName);
		GeneratorConfig config = new GeneratorConfig();
		model.addAttribute("config", config);
		return "admin/genCode/columnForm";
	}
	
	/**
	 * 配置代码生成基础信息
	 * @param tableName 表名
	 * @param config 基础配置
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "download", method = RequestMethod.POST)
	public void download(@RequestParam("tableName")String tableName,GeneratorConfig config,
			HttpServletRequest request,HttpServletResponse response) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(64);
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		codeGeneratorService.download(tableName,config,zip);
		IOUtils.closeQuietly(zip);
		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\"gen_code.zip\"");  
        response.addHeader("Content-Length", "" + outputStream.toByteArray().length);  
        response.setContentType("application/octet-stream; charset=UTF-8"); 
        try {
			IOUtils.write(outputStream.toByteArray(), response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		//return "redirect:gencode/tables";
	}
}
