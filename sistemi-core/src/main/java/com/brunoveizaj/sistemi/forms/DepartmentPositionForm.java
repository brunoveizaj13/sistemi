package com.brunoveizaj.sistemi.forms;

import java.util.Date;



import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartmentPositionForm {

	Integer id;
	String personNid;
	Integer departmentId;
	Integer functionId;
	Date startDate;
	Date endDate;
	
	
	
}
