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
import com.brunoveizaj.sistemi.ui.forms.PatronageForm;
import com.brunoveizaj.sistemi.ui.forms.PatronagePersonForm;
import com.brunoveizaj.sistemi.ui.models.PatronageDTO;
import com.brunoveizaj.sistemi.ui.models.PatronagePersonDTO;
import com.brunoveizaj.sistemi.ui.utils.Util;

public class PatronageClient {
	
	
	
	public PatronageDTO registerPatronage(PatronageForm form)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/patronage/registerPatronage";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(form, headers);
		
		
		ResponseEntity<PatronageDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, PatronageDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public PatronageDTO registerPatronageInstitution(PatronageForm form)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/patronage/registerPatronageInstitution";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(form, headers);
		
		
		ResponseEntity<PatronageDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, PatronageDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public int registerPatronagePerson(PatronagePersonForm form)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/patronage/registerPatronagePerson";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(form, headers);
		
		
		ResponseEntity<Integer> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, Integer.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return 0;
	}
	
	public int registerPatronageInstitutionPerson(PatronagePersonForm form)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/patronage/registerPatronageInstitutionPerson";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(form, headers);
		
		
		ResponseEntity<Integer> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, Integer.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return 0;
	}
	
	public List<PatronagePersonDTO> getPatronagePersons(Integer patronageId, Integer patronageType)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/patronage/getPatronagePersons/"+patronageId+"/"+patronageType;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PatronagePersonDTO>> typeRef = new ParameterizedTypeReference<List<PatronagePersonDTO>>() {};
		
		ResponseEntity<List<PatronagePersonDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public PatronageDTO findPatronageByNid(String nid, Integer patronageType)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/patronage/findPatronageByNid/"+nid+"/"+patronageType;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		
		ResponseEntity<PatronageDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, PatronageDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

	public List<PatronagePersonDTO> getPatronagesOfPerson(String nid, Integer patronageTypeId) {
		final String BASE_URL = IApiClient.SERVER+"/api/patronage/getPatronagesOfPerson/"+nid+"/"+patronageTypeId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PatronagePersonDTO>> typeRef = new ParameterizedTypeReference<List<PatronagePersonDTO>>() {};
		
		ResponseEntity<List<PatronagePersonDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

	public List<PatronageDTO> getPatronagesByArea(Integer unitId, Integer qvId, Integer patronageTypeId) {
		String BASE_URL = IApiClient.SERVER+"/api/patronage/getPatronagesByArea";
		
		boolean isFirst = true;
		
		if(qvId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("qvId=" + qvId));
			isFirst = false;
		}
		
		if(unitId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("unitId=" + unitId));
			isFirst = false;
		}
		
		if(patronageTypeId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("patronageTypeId=" + patronageTypeId));
			isFirst = false;
		}
						
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PatronageDTO>> typeRef = new ParameterizedTypeReference<List<PatronageDTO>>() {};
		
		ResponseEntity<List<PatronageDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	
	
	

}
