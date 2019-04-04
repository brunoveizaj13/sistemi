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
import com.brunoveizaj.sistemi.dto.DepartmentDTO;
import com.brunoveizaj.sistemi.dto.DepartmentFunctionDTO;
import com.brunoveizaj.sistemi.dto.DepartmentPositionDTO;
import com.brunoveizaj.sistemi.forms.DepartmentForm;
import com.brunoveizaj.sistemi.forms.DepartmentPositionForm;
import com.brunoveizaj.sistemi.service.DepartmentService;
import com.brunoveizaj.sistemi.service.TokenService;

@RestController
@RequestMapping("/api/department")
public class DepartmentAPI {
	
	
	@Autowired DepartmentService departmentService;
	@Autowired TokenService tokenService;
	
	
	@RequestMapping(value="/getRootDepartment", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getRootDepartment(@RequestHeader(value="Authorization") String token)
	{
		tokenService.getUsername(token);
		
		DepartmentDTO d = new Assembler().toDto(departmentService.getRootDepartment());
				
		return new ResponseEntity<>(d,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getMunicipalityRootDepartment/{munId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getMunicipalityRootDepartment(@RequestHeader(value="Authorization") String token, @PathVariable Integer munId)
	{
		tokenService.getUsername(token);
		
		DepartmentDTO d = new Assembler().toDto(departmentService.getMunicipalityRootDepartment(munId));
				
		return new ResponseEntity<>(d,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getChildDepartments/{deptId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getChildDepartments(@RequestHeader(value="Authorization") String token, @PathVariable Integer deptId)
	{
		tokenService.getUsername(token);
		
		List<DepartmentDTO> list = new Assembler().departmentListToDto(departmentService.getChildDepartments(deptId));
				
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getDepartmentPositions/{deptId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getDepartmentPositions(@RequestHeader(value="Authorization") String token, @PathVariable Integer deptId)
	{
		tokenService.getUsername(token);
		
		List<DepartmentPositionDTO> list = new Assembler().departmentPositionListToDto(departmentService.getDepartmentPositions(deptId));
				
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getDepartmentPositionsHistory/{deptId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getDepartmentPositionsHistory(@RequestHeader(value="Authorization") String token, @PathVariable Integer deptId)
	{
		tokenService.getUsername(token);
		
		List<DepartmentPositionDTO> list = new Assembler().departmentPositionListToDto(departmentService.getDepartmentPositionsHistory(deptId));
				
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getPersonParyHistory/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getPersonParyHistory(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		tokenService.getUsername(token);
		
		List<DepartmentPositionDTO> list = new Assembler().departmentPositionListToDto(departmentService.getPersonParyHistory(nid));
				
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/listDepartmentFunctions", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> listDepartmentFunctions(@RequestHeader(value="Authorization") String token)
	{
		tokenService.getUsername(token);
		
		List<DepartmentFunctionDTO> list = new Assembler().departmentFunctionListToDto(departmentService.listDepartmentFunctions());
				
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/registerDepartment", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerDepartment(@RequestHeader(value="Authorization") String token, @RequestBody DepartmentForm payload)
	{
			String uname = tokenService.getUsername(token);
			
			DepartmentDTO dto = new Assembler().toDto(departmentService.registerDepartment(payload, uname));
			
			return new ResponseEntity<>(dto, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/registerDepartmentPosition", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerDepartmentPosition(@RequestHeader(value="Authorization") String token, @RequestBody DepartmentPositionForm payload)
	{
			String uname = tokenService.getUsername(token);
			
			DepartmentPositionDTO dto = new Assembler().toDto(departmentService.registerDepartmentPosition(payload, uname));
			
			return new ResponseEntity<>(dto, HttpStatus.OK);		
	}
	
	
	
	
	

}
