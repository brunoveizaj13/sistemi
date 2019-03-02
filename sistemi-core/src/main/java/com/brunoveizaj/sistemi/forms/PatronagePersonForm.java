package com.brunoveizaj.sistemi.forms;

import java.io.Serializable;
import java.util.List;

import com.brunoveizaj.sistemi.dto.PersonDTO;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PatronagePersonForm implements Serializable {
	
	

	private static final long serialVersionUID = 1L;

	PersonDTO patronage;
	List<PatronagePersonModelForm> personForms;
	
}
