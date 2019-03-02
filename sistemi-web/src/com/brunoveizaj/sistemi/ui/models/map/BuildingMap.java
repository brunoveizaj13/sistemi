package com.brunoveizaj.sistemi.ui.models.map;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BuildingMap implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Integer buildingId;
	String buildingNo;
	String buildingCode;
	Integer hasData;
	String center;
	String shape;
	
	
	

}
