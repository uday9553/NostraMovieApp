package com.uday.exception;

import org.springframework.http.HttpStatus;

public class JsonParseException extends BaseException {
	private static final long serialVersionUID = 1L;

	public JsonParseException(String userMessage) {
		super(userMessage);
	}

	public JsonParseException(String userMessage, String systemMessage, HttpStatus httpStatus) {
		super(userMessage, systemMessage, httpStatus);
	}

	public JsonParseException(String userMessage, HttpStatus httpStatus, Throwable cause) {
		super(userMessage, httpStatus, cause);
	}

	public JsonParseException(String userMessage, Throwable cause) {
		super(userMessage, cause);
	}

}