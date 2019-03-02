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
import com.brunoveizaj.sistemi.dto.AddressDTO;
import com.brunoveizaj.sistemi.service.AddressService;
import com.brunoveizaj.sistemi.service.TokenService;



@RestController
@RequestMapping("/api/address")
public class AddressAPI {
	
	@Autowired TokenService  tokenService;
	@Autowired AddressService addressService;
	
	
	@RequestMapping(value="/getPersonAddresses/{nid}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getPersonAddresses(@RequestHeader(value="Authorization") String token, @PathVariable String nid)
	{
		String uname = tokenService.getUsername(token);
		
		List<AddressDTO> list = new Assembler().addressListToDto(addressService.getPersonAddresses(nid, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getBuildingAddresses/{buildingId}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getBuildingAddresses(@RequestHeader(value="Authorization") String token, @PathVariable Integer buildingId)
	{
		String uname = tokenService.getUsername(token);
		
		List<AddressDTO> list = new Assembler().addressListToDto(addressService.getBuildingAddresses(buildingId, uname));
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	
	

}
