package com.brunoveizaj.sistemi.ui.models.map;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MunicipalityMap implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Integer municipalityId;
	String name;
	String center;
	String shape;
	
	
}