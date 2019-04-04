package com.brunoveizaj.sistemi.ui.models;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartmentPositionDTO implements Serializable {
	
	Integer id;
	PersonDTO person;
	DepartmentDTO department;
	DepartmentFunctionDTO function;
	Date startDate;
	Date endDate;
	boolean status;
	

}
