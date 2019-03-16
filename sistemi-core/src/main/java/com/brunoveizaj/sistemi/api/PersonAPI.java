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
import com.brunoveizaj.sistemi.dto.PersonDTO;
import com.brunoveizaj.sistemi.forms.PersonSx;
import com.brunoveizaj.sistemi.service.PersonService;
import com.brunoveizaj.sistemi.service.TokenService;

@RestController
@RequestMapping("/api/person")
public class PersonAPI {

	
	
	@Autowired PersonService personService;
	@Autowired TokenService tokenService;
	
	
	
	@RequestMapping(value="/findPersonByNid/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> findPersonByNid(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		String uname = tokenService.getUsername(token);
		
		PersonDTO p = new Assembler().toDto(personService.findPersonByNid(nid, uname));
		return new ResponseEntity<>(p,HttpStatus.OK);
	}
	
	@RequestMapping(value="/photo/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getPhotoByNid(@PathVariable String nid)
	{		
		String base64Photo = personService.getPhotoByNid(nid);
		return new ResponseEntity<>(base64Photo,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getFamilyByFamilyId/{familyId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getFamilyByFamilyId(@RequestHeader(value="Authorization") String token, @PathVariable long familyId)
	{
		String uname = tokenService.getUsername(token);
				
		List<PersonDTO> list = new Assembler().personListToDto(personService.getFamilyByFamilyId(familyId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getFamilyByNid/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getFamilyByNid(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		String uname = tokenService.getUsername(token);
				
		List<PersonDTO> list = new Assembler().personListToDto(personService.getFamilyByNid(nid, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/searchPerson", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> searchPerson(@RequestHeader(value="Authorization") String token,@RequestBody PersonSx req)
	{
		String uname = tokenService.getUsername(token);
				
		List<PersonDTO> list = new Assembler().personListToDto(personService.searchPerson(req, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
	
	
	
	
}
