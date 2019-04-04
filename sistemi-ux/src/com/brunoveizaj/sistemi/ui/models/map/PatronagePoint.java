package com.brunoveizaj.sistemi.ui.models.map;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatronagePoint implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	String nid;
	String fullName;
	Integer patronageId;
	Integer patronageTypeId;
	String patronageType;
	
	
	String point;
	
	

}