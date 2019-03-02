package com.brunoveizaj.sistemi.ui.models.map;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QvMap implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Integer qvId;
	String qvCode;
	String center;
	String shape;
	
	
	

}
