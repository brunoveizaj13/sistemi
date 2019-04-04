package com.brunoveizaj.sistemi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brunoveizaj.sistemi.assemblers.Assembler;
import com.brunoveizaj.sistemi.dto.EmployeeDTO;
import com.brunoveizaj.sistemi.dto.EmployeePeriodDTO;
import com.brunoveizaj.sistemi.entities.Employee;
import com.brunoveizaj.sistemi.forms.EmployeeSx;
import com.brunoveizaj.sistemi.service.EmployeeService;
import com.brunoveizaj.sistemi.service.TokenService;


@RestController
@RequestMapping("/api/employee")
public class EmployeeAPI {

	
	@Autowired
	EmployeeService employeeService;
	@Autowired 
	TokenService tokenService;
	
	
	@RequestMapping(value="/getEmploymentPeriods/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getEmploymentPeriods(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		String uname = tokenService.getUsername(token);
		
		List<EmployeePeriodDTO> list = new Assembler().employmentPeriodListToDto(employeeService.getEmploymentPeriods(nid, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getEmployment/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getEmployment(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		String uname = tokenService.getUsername(token);
		
		List<EmployeeDTO> list = new Assembler().employeeListToDto(employeeService.getEmployment(nid, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getEmployees/{nipt}/{year}/{month}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getEmployees(@RequestHeader(value="Authorization") String token, @PathVariable String nipt, @PathVariable Integer year, @PathVariable Integer month)
	{
		String uname = tokenService.getUsername(token);
		
		List<EmployeeDTO> list = new Assembler().employeeListToDto(employeeService.getEmployees(nipt, year, month, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/searchEmployee", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> searchEmployee(@RequestHeader(value="Authorization") String token,@RequestBody EmployeeSx req)
	{
		String uname = tokenService.getUsername(token);
				
		List<Employee> list = employeeService.searchEmployee(req,uname);
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(new Assembler().employeeListToDto(list),HttpStatus.OK);
		
	}
	
	
	
	
}
