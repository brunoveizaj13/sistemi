package com.brunoveizaj.sistemi.ui.api.clients;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.brunoveizaj.sistemi.ui.api.security.ApiErrorHandler;
import com.brunoveizaj.sistemi.ui.constants.IApiClient;
import com.brunoveizaj.sistemi.ui.forms.PasswordForm;
import com.brunoveizaj.sistemi.ui.models.Principal;
import com.brunoveizaj.sistemi.ui.models.UserDTO;
import com.brunoveizaj.sistemi.ui.models.UserToken;
import com.brunoveizaj.sistemi.ui.utils.Util;

public class UserClient {

	
	public UserToken login(Principal principal)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/user/login";
	    
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<Principal>(principal,headers);
		
		ResponseEntity<UserToken> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, UserToken.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}				
		
		return null;
	}
	
	public void changePassword(PasswordForm form)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/user/changePassword";
	    
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<PasswordForm>(form,headers);
		
		restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, Void.class);
				
	}
	
	
	
	public UserDTO registerUser(UserDTO dto)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/user/register";
	    
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<UserDTO>(dto,headers);
		
		ResponseEntity<UserDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, UserDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}				
		
		return null;
	}
	
	
	public UserDTO modifyUser(UserDTO dto)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/user/modify";
	    
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<UserDTO>(dto,headers);
		
		ResponseEntity<UserDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, UserDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}				
		
		return null;
	}
	
	public List<UserDTO> loadUsers()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/user/searchUser";		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);
		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", "Bearer "+Util.getToken());
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<UserDTO>> typeRef = new ParameterizedTypeReference<List<UserDTO>>() {};
		
		ResponseEntity<List<UserDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
				
		return null;
		
	}
	
	
	
	
	
	
	
	
	
}
