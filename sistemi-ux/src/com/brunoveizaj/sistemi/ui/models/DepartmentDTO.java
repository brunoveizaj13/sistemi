package com.brunoveizaj.sistemi.ui.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartmentDTO implements Serializable {

	Integer id;
	String name;
	PartyStructureDTO structure;
	boolean singlePosition;
	DepartmentDTO parent;
	Integer area;
	Integer regionId;
	Integer municipalityId;
	Integer unitId;
	Integer qvId;
	String fraction;
	boolean expanded;
	String color;
	boolean status;
	
	
	
}
 