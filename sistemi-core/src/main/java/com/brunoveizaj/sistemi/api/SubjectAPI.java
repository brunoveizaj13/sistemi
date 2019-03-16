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
import com.brunoveizaj.sistemi.dto.SubjectDTO;
import com.brunoveizaj.sistemi.forms.SubjectSx;
import com.brunoveizaj.sistemi.service.SubjectService;
import com.brunoveizaj.sistemi.service.TokenService;

@RestController
@RequestMapping("/api/subject")
public class SubjectAPI {

	
	@Autowired SubjectService subjectService;
	@Autowired TokenService tokenService;
	
	
	@RequestMapping(value="/getSubjectByNipt/{nipt}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getSubjectByNipt(@RequestHeader(value="Authorization") String token, @PathVariable String nipt)
	{
		String uname = tokenService.getUsername(token);
		
		SubjectDTO p = new Assembler().toDto(subjectService.getSubjectByNipt(nipt, uname));
		return new ResponseEntity<>(p,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/searchSubjects", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?> searchSubjects(@RequestHeader(value="Authorization") String token, @RequestBody SubjectSx req)
	{
		String uname = tokenService.getUsername(token);
				
		List<SubjectDTO> list = new Assembler().subjectListToDto(subjectService.searchSubjects(req, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	
}
