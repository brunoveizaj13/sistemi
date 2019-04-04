package com.brunoveizaj.sistemi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeePeriodDTO {
	
	private Integer id;
    private String nid;
    private String nipt;
    private String subject;
    private String profession;
    private Integer minSalary;
    private Integer maxSalary;
    private Integer avgSalary;
    private Integer fromDate;
    private Integer toDate;

}
