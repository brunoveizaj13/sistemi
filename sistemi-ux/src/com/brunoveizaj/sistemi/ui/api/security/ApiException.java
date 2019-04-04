package com.brunoveizaj.sistemi.ui.api.security;

import java.util.List;

import lombok.Getter;
import lombok.Setter;



@Getter @Setter
public class ApiException extends RuntimeException {


private static final long serialVersionUID = 1L;
	
	int httpCode;
	String title;
	int severity;
	String message;

	List<String> errors;
	
	

	public ApiException()
	{
		
	}



	public ApiException(String message, int httpCode, int severity, String detail, List<String> errors) {
		super();
		this.message = message;
		this.httpCode = httpCode;
		this.severity = severity;
		this.title = detail;
		this.errors = errors;
	}
	
	
	
	
	
	
	

}
