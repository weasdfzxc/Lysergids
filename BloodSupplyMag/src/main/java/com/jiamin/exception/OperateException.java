package com.jiamin.exception;

public class OperateException extends Exception {

	public OperateException(String message)
	{
		super("OperateException-"+message);
	}
	
	public OperateException(String message, Throwable cause)
	{
		super("OperateException-"+message,cause);
	}
	
}
