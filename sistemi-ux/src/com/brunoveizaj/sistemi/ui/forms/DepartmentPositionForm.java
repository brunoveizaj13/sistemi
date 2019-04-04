package com.brunoveizaj.sistemi.ui.forms;

import java.io.Serializable;
import java.util.Date;



import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DepartmentPositionForm implements Serializable {

	Integer id;
	String personNid;
	Integer departmentId;
	Integer functionId;
	Date startDate;
	Date endDate;
	
	
	
}
