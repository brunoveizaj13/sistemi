package com.brunoveizaj.sistemi.ui.services;

import java.util.List;

import com.brunoveizaj.sistemi.ui.api.clients.EmployeeClient;
import com.brunoveizaj.sistemi.ui.forms.EmployeeSx;
import com.brunoveizaj.sistemi.ui.models.EmployeeDTO;
import com.brunoveizaj.sistemi.ui.models.EmployeePeriodDTO;

public class EmployeeService {

	
	
	public List<EmployeePeriodDTO> getEmploymentPeriods(String nid)
	{
		return new EmployeeClient().getEmploymentPeriods(nid);
	}
	
	public List<EmployeeDTO> getEmployment(String nid)
	{
		return new EmployeeClient().getEmployment(nid);
	}
	
	public List<EmployeeDTO> getEmployees(String nipt, Integer year, Integer month)
	{
		return new EmployeeClient().getEmployees(nipt, year, month);
	}
	
	public List<EmployeeDTO> searchEmployee(EmployeeSx req)
	{
		return new EmployeeClient().getEmployment(req);
	}
	
	
	
	
}
