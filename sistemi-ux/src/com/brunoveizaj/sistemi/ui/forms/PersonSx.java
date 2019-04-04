package com.brunoveizaj.sistemi.ui.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonSx implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
	
	
	String name;
	String surname;
	String fatherName;
	String motherName;
	String nid;
	String maidenName;
	Long familyId;
	String dob;
	Integer fromAge;
	Integer toAge;
	String gender;
	String pob;
	
	Integer regionId;
	Integer municipalityId;
	Integer unitId;
	Integer qvId;
	String votingListNo;
	String fraction;
	
	Integer firstResult;
	Integer maxResult;
	

}
