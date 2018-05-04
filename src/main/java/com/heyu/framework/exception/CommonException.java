package com.heyu.framework.exception;

public class CommonException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	private int code = 500;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public CommonException() {
		super();
	}
	
	public CommonException(String msg) {
		this.msg = msg;
	}
	
	public CommonException(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}
}
