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
import com.brunoveizaj.sistemi.dto.UserDTO;
import com.brunoveizaj.sistemi.forms.PasswordForm;
import com.brunoveizaj.sistemi.models.Principal;
import com.brunoveizaj.sistemi.models.UserToken;
import com.brunoveizaj.sistemi.service.TokenService;
import com.brunoveizaj.sistemi.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserAPI {

	
	@Autowired
	UserService userService;
	@Autowired 
	TokenService tokenService;
	
	
	@RequestMapping(value="/searchUser", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> searchUser(@RequestHeader(value="Authorization") String token)
	{
		tokenService.getUsername(token);
				
		List<UserDTO> list = new Assembler().userListToDto(userService.listUsers());
		
		if(list == null || list.isEmpty())
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/uname/{username}", method=RequestMethod.GET, produces={"application/json"})
	public ResponseEntity<?> getUserByUsername(@RequestHeader(value="Authorization") String token, @PathVariable String username)
	{
        tokenService.getUsername(token);
		
		UserDTO user = new Assembler().toDto(userService.findUserByUsername(username));
		
		if(user == null)
		{
			return new ResponseEntity<>("Nuk ka te dhena",HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
		
	
	@RequestMapping(value="/register", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?>registerUser(@RequestHeader(value="Authorization") String token, @RequestBody UserDTO payload)
	{
			
			String uname = tokenService.getUsername(token);
			
			UserDTO u = new Assembler().toDto(userService.createUser(payload, uname));
			
			return new ResponseEntity<>(u,HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST, produces={"application/json"})
	public ResponseEntity<?>modifyUser(@RequestHeader(value="Authorization") String token, @RequestBody UserDTO payload)
	{
				
		String uname = tokenService.getUsername(token);
		
		UserDTO u = new Assembler().toDto(userService.modifyUser(payload, uname));
		
		return new ResponseEntity<>(u,HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> login(@RequestBody Principal principal)
	{
			UserToken ut = userService.login(principal);
			return new ResponseEntity<>(ut, HttpStatus.OK);		
		
	}
	
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> changePassword(@RequestHeader(value="Authorization") String token, @RequestBody PasswordForm form)
	{
			
			String uname = tokenService.getUsername(token);
			
			userService.changePassword(form, uname);
			
			return new ResponseEntity<>(HttpStatus.OK);	
			
		
	}
	
	
	
	
	
	
	
	
	
	
}
