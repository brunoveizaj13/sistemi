package com.brunoveizaj.sistemi.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeSx implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	String nid;
	String nipt;
	String name;
	String surname;
	String subject;
	Integer year;
	Integer month;

	Integer firstResult;
	Integer maxResult;

}
