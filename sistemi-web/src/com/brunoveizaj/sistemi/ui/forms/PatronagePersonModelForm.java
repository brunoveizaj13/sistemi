package com.brunoveizaj.sistemi.ui.forms;

import java.io.Serializable;

import com.brunoveizaj.sistemi.ui.models.PersonDTO;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PatronagePersonModelForm implements Serializable {
	
	

	private static final long serialVersionUID = 1L;

	PersonDTO person;
	String phone;
	String email;
	boolean withFamily;
	Integer buildingId;
	
	
}
