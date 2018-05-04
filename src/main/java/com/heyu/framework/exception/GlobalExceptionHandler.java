package com.heyu.framework.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.heyu.framework.entity.ResultModel;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = CommonException.class)
	@ResponseBody
	public ResultModel<String> jsonErrorHandler(CommonException e){
		ResultModel<String> result = new ResultModel<>(1,e.getMsg());
		return result;
		
	}
	
	@ExceptionHandler(value = PageException.class)
	public ModelAndView pageErrorHandler(HttpServletRequest request,PageException e) {
		ModelAndView model = new ModelAndView();
		
		model.setViewName("error");
		model.addObject("exception",e);
		model.addObject("url", request.getRequestURL());
		return model;
	}
	
}
