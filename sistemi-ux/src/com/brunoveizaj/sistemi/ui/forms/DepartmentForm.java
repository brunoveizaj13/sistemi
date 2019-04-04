package com.brunoveizaj.sistemi.ui.forms;

import lombok.Getter;
import lombok.Setter;




@Getter @Setter
public class DepartmentForm {

	
	String name;
	Integer parentId;
	boolean singlePosition;
	Integer area;
	Integer regionId;
	Integer municipalityId;
	Integer unitId;
	Integer qvId;
	String fraction;
	boolean expanded;
	String color;
	
}
