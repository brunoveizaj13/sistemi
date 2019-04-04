package com.brunoveizaj.sistemi.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartmentPositionDTO {
	
	Integer id;
	PersonDTO person;
	DepartmentDTO department;
	DepartmentFunctionDTO function;
	Date startDate;
	Date endDate;
	boolean status;
	

}
