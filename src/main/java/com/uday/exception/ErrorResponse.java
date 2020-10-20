package com.uday.exception;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ErrorResponse {

	public ErrorResponse() {
		
	}
	

	@JsonProperty
	private String status;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty
	private String reason;

	@JsonProperty
	private String message;
	
	public ErrorResponse( String status) {		
		this.status = status;
	}

	public ErrorResponse(String message, String status) {		
		this.message = message;
		this.status = status;
	}
	public ErrorResponse(String message, String status,String reason) {		
		this.reason=reason;
		this.message = message;
		this.status = status;
	}
}