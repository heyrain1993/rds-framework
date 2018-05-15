package com.heyu.framework.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.heyu.framework.model.ResultModel;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * json异常处理器
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = CommonException.class)
	@ResponseBody
	public ResultModel<String> jsonErrorHandler(CommonException e){
		ResultModel<String> result = new ResultModel<>(1,e.getMsg());
		return result;
		
	}
	
	/**
	 * 页面异常处理器
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = PageException.class)
	public ModelAndView pageErrorHandler(PageException e) {
		ModelAndView model = new ModelAndView();
		
		model.setViewName("templates/error");
		model.addObject("code", e.getCode());
		model.addObject("msg", e.getMsg());
		model.addObject("message",e.getMessage());
		return model;
	}
	
}
