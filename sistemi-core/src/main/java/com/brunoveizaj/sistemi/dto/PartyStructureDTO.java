package com.brunoveizaj.sistemi.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PartyStructureDTO {

	Integer id;
	String name;
	Date startDate;
	Date endDate;
	String type;
	boolean active;
	
	
}
