package com.brunoveizaj.sistemi.ui.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubjectSx implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	String nipt;
	String name;
	String administrator;
	String legalForm;
	String notName;
	Integer municipalityId;
	Boolean skipRaport;
	Integer firstResult;
	Integer maxResult;
	

}
