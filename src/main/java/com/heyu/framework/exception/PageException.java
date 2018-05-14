package com.heyu.framework.exception;

public class PageException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String code = "500";
	
	private String msg = "error";
	
	public PageException() {
		super();
	}
	
	public PageException(String msg) {
		super();
		this.msg = msg;
	}
	
	public PageException(String code,String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
