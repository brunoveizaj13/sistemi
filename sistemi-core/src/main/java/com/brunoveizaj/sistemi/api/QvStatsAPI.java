package com.brunoveizaj.sistemi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brunoveizaj.sistemi.assemblers.Assembler;
import com.brunoveizaj.sistemi.dto.PersonDTO;
import com.brunoveizaj.sistemi.service.QvStatsService;
import com.brunoveizaj.sistemi.service.TokenService;

@RestController
@RequestMapping("/api/statistic/qv")
public class QvStatsAPI {

	
	@Autowired TokenService tokenService;
	@Autowired QvStatsService qvStatsService;
	
	@RequestMapping(value="/persons/{qvId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> persons(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonDTO> list = new Assembler().personListToDto(qvStatsService.persons(qvId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/personsNotInPatronage/{qvId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> personsNotInPatronage(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonDTO> list = new Assembler().personListToDto(qvStatsService.personsNotInPatronage(qvId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value="/personsInPatronage/{qvId}/{patronageTypeId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> personsInPatronage(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId, @PathVariable Integer patronageTypeId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonDTO> list = new Assembler().personListToDto(qvStatsService.personsInPatronage(qvId, patronageTypeId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/patronages/{qvId}/{patronageTypeId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> patronages(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId, @PathVariable Integer patronageTypeId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonDTO> list = new Assembler().personListToDto(qvStatsService.patronages(qvId, patronageTypeId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/firstTimeVoters/{qvId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> firstTimeVoters(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonDTO> list = new Assembler().personListToDto(qvStatsService.firstTimeVoters(qvId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/notVotingLastElections/{qvId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> notVotingLastElections(@RequestHeader(value="Authorization") String token, @PathVariable Integer qvId)
	{
		String uname = tokenService.getUsername(token);
		
		List<PersonDTO> list = new Assembler().personListToDto(qvStatsService.notVotingLastElections(qvId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	
	
	
	
	
}
