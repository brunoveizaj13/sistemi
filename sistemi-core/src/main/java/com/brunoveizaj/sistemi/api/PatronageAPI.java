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
import com.brunoveizaj.sistemi.dto.PatronageDTO;
import com.brunoveizaj.sistemi.dto.PatronagePersonDTO;
import com.brunoveizaj.sistemi.forms.PatronageForm;
import com.brunoveizaj.sistemi.forms.PatronagePersonForm;
import com.brunoveizaj.sistemi.service.PatronageService;
import com.brunoveizaj.sistemi.service.TokenService;



@RestController
@RequestMapping("/api/patronage")
public class PatronageAPI {
	
	
	
	@Autowired TokenService  tokenService;
	@Autowired PatronageService patronageService;
	
	
	
	@RequestMapping(value="/registerPatronage", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerPatronage(@RequestHeader(value="Authorization") String token, @RequestBody PatronageForm payload)
	{
			String uname = tokenService.getUsername(token);
			
			PatronageDTO dto = new Assembler().toDto(patronageService.registerPatronage(payload, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	@RequestMapping(value="/registerPatronageInstitution", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerPatronageInstitution(@RequestHeader(value="Authorization") String token, @RequestBody PatronageForm payload)
	{
			String uname = tokenService.getUsername(token);
			
			PatronageDTO dto = new Assembler().toDto(patronageService.registerPatronageInstitution(payload, uname));
			
			return new ResponseEntity<>(dto,HttpStatus.OK);		
	}
	
	@RequestMapping(value="/registerPatronagePerson", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerPatronagePerson(@RequestHeader(value="Authorization") String token, @RequestBody PatronagePersonForm payload)
	{
			String uname = tokenService.getUsername(token);
			
			int cnt = patronageService.registerPatronagePerson(payload, uname);
			
			return new ResponseEntity<>(cnt, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/registerPatronageInstitutionPerson", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> registerPatronageInstitutionPerson(@RequestHeader(value="Authorization") String token, @RequestBody PatronagePersonForm payload)
	{
			String uname = tokenService.getUsername(token);
			
			int cnt = patronageService.registerPatronageInstitutionPerson(payload, uname);
			
			return new ResponseEntity<>(cnt, HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/getPatronagePersons/{patronageId}/{patronageType}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getPatronagePersons(@RequestHeader(value="Authorization") String token, @PathVariable Integer patronageId, @PathVariable Integer patronageType)
	{
		String uname = tokenService.getUsername(token);
		
		List<PatronagePersonDTO> list = new Assembler().patronagePersonListToDto(patronageService.getPatronagePersons(patronageId, patronageType, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/findPatronageByNid/{nid}/{patronageType}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> findPatronageByNid(@RequestHeader(value="Authorization") String token, @PathVariable String nid, @PathVariable Integer patronageType)
	{
		String uname = tokenService.getUsername(token);
		
		PatronageDTO p = new Assembler().toDto(patronageService.findPatronageByNid(nid, patronageType, uname));
		return new ResponseEntity<>(p,HttpStatus.OK);
	}
	

}
