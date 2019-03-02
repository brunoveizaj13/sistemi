package com.brunoveizaj.sistemi.ui.forms;


import java.io.Serializable;

import com.brunoveizaj.sistemi.ui.models.PersonDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatronageForm implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	
	PersonDTO person;
	String phone;
	String email;
	Integer institutionId;
	Integer patronageTypeId;
	Integer buildingId;

}
