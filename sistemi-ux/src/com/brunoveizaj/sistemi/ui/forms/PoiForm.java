package com.brunoveizaj.sistemi.ui.forms;

import java.io.Serializable;

import com.brunoveizaj.sistemi.ui.models.PersonDTO;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PoiForm implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	
	PersonDTO person;
	String email;
	String phone;
	Integer buildingId;
	Integer poiTypeId;
	Integer qvId;
	Integer unitId;
	Integer municipalityId;
	Integer regionId;
	

}
