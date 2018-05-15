package com.heyu.framework.model;

import java.io.Serializable;

public class ResultModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer code = 0;
	
	private String msg;
	
	private T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public ResultModel() {
		
	}
	
	public ResultModel(String msg) {
		this.msg = msg;
	}
	
	public ResultModel(Integer code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public ResultModel(Integer code,String msg,T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
}
