package com.brunoveizaj.sistemi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunoveizaj.sistemi.dao.EmployeeDAO;
import com.brunoveizaj.sistemi.entities.Employee;
import com.brunoveizaj.sistemi.entities.EmploymentPeriod;
import com.brunoveizaj.sistemi.forms.EmployeeSx;

@Service
public class EmployeeService {

	@Autowired EmployeeDAO employeeDAO;
	
	
	
	public List<EmploymentPeriod> getEmploymentPeriods(String nid, String uname)
	{
		return employeeDAO.getEmploymentPeriods(nid);
	}
	
	public List<Employee> getEmployment(String nid, String uname)
	{
		EmployeeSx req = new EmployeeSx();
		req.setNid(nid);
		return employeeDAO.searchEmployees(req);
	}
	
	public List<Employee> getEmployees(String nipt, Integer year, Integer month, String uname)
	{
		EmployeeSx req = new EmployeeSx();
		req.setNipt(nipt);
		req.setMonth(month);
		req.setYear(year);
		return employeeDAO.searchEmployees(req);
	}
	
	public List<Employee> searchEmployee(EmployeeSx req, String uname)
	{
		return employeeDAO.searchEmployees(req);
	}
	
	
}
