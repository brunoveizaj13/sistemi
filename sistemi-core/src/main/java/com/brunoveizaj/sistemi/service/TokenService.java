package com.brunoveizaj.sistemi.service;

import org.springframework.stereotype.Service;

import com.brunoveizaj.sistemi.entities.User;
import com.brunoveizaj.sistemi.security.TokenUtil;



@Service
public class TokenService {

	
	public String generateToken(User u)
	{
		return TokenUtil.generateToken(u);
	}
	
	public String getUsername(String token)
	{
		return TokenUtil.getUsername(token);
	}
	
	
	
	
}
