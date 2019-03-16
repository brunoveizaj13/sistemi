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

import com.brunoveizaj.sistemi.ui.constants.IApiClient;
import com.brunoveizaj.sistemi.ui.forms.PersonSx;
import com.brunoveizaj.sistemi.ui.models.PersonDTO;
import com.brunoveizaj.sistemi.ui.utils.Util;

public class PersonClient {
	
	
	
	
	public List<PersonDTO> searchPerson(PersonSx req)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/person/searchPerson";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(req, headers);
		
		ParameterizedTypeReference<List<PersonDTO>> typeRef = new ParameterizedTypeReference<List<PersonDTO>>() {};
		
		ResponseEntity<List<PersonDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public String getPhotoByNid(String nid)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/person/photo/"+nid;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public PersonDTO findPersonByNid(String nid)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/person/findPersonByNid/"+nid;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<PersonDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, PersonDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<PersonDTO> getFamilyByFamilyId(long familyId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/person/getFamilyByFamilyId/"+familyId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PersonDTO>> typeRef = new ParameterizedTypeReference<List<PersonDTO>>() {};
		
		ResponseEntity<List<PersonDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<PersonDTO> getFamilyByNid(String nid)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/person/getFamilyByNid/"+nid;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PersonDTO>> typeRef = new ParameterizedTypeReference<List<PersonDTO>>() {};
		
		ResponseEntity<List<PersonDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	

}
