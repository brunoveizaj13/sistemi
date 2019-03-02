package com.brunoveizaj.sistemi.ui.api.security;

import java.util.List;




public class ApiException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	
	int httpCode;
	String title;
	int severity;
	String message;
	String messageEng;

	List<String> errors;
	
	

	public ApiException()
	{
		
	}



	public ApiException(String message, String messageEng, int httpCode, int severity, String detail,
			List<String> errors) {
		super();
		this.message = message;
		this.messageEng = messageEng;
		this.httpCode = httpCode;
		this.severity = severity;
		this.title = detail;
		this.errors = errors;
	}



	public int getHttpCode() {
		return httpCode;
	}



	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public int getSeverity() {
		return severity;
	}



	public void setSeverity(int severity) {
		this.severity = severity;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getMessageEng() {
		return messageEng;
	}



	public void setMessageEng(String messageEng) {
		this.messageEng = messageEng;
	}



	public List<String> getErrors() {
		return errors;
	}



	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	
	
	

}
