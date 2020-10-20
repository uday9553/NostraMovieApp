package com.uday.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String userMessage;
	private final String systemMessage;
	private final HttpStatus httpStatus;

	public BaseException(String userMessage) {
		super(userMessage);
		this.userMessage = userMessage;
		this.systemMessage="";
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public String getSystemMessage() {
		return systemMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public BaseException(String userMessage, Throwable cause) {
		super(cause);
		this.systemMessage=cause.getLocalizedMessage();
		this.userMessage = userMessage;
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}

	public BaseException(String userMessage, HttpStatus httpStatus,Throwable cause) {
		super();
		this.userMessage = userMessage;
		this.systemMessage = cause.getLocalizedMessage();
		this.httpStatus = httpStatus;
	}

	public BaseException(String userMessage, String systemMessage, HttpStatus httpStatus) {
		super();
		this.userMessage = userMessage;
		this.systemMessage = systemMessage;
		this.httpStatus = httpStatus;
	}
	

}