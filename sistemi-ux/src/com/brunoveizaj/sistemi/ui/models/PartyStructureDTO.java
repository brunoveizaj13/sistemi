package com.brunoveizaj.sistemi.ui.models;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PartyStructureDTO implements Serializable {

	Integer id;
	String name;
	Date startDate;
	Date endDate;
	String type;
	boolean active;
	
	
}
