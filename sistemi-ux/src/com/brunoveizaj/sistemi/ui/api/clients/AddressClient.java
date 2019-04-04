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
import com.brunoveizaj.sistemi.ui.models.AddressDTO;
import com.brunoveizaj.sistemi.ui.utils.Util;

public class AddressClient {
	
	
	
	public List<AddressDTO> getPersonAddresses(String nid)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/address/getPersonAddresses/"+nid;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<AddressDTO>> typeRef = new ParameterizedTypeReference<List<AddressDTO>>() {};
		
		ResponseEntity<List<AddressDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<AddressDTO> getBuildingAddresses(Integer buildingId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/address/getBuildingAddresses/"+buildingId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<AddressDTO>> typeRef = new ParameterizedTypeReference<List<AddressDTO>>() {};
		
		ResponseEntity<List<AddressDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	

}
