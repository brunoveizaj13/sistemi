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
import com.brunoveizaj.sistemi.ui.forms.SubjectSx;
import com.brunoveizaj.sistemi.ui.models.SubjectDTO;
import com.brunoveizaj.sistemi.ui.utils.Util;

public class SubjectClient {

	public List<SubjectDTO> searchSubjects(SubjectSx req) {
		final String BASE_URL = IApiClient.SERVER+"/api/subject/searchSubjects";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(req, headers);
		
		ParameterizedTypeReference<List<SubjectDTO>> typeRef = new ParameterizedTypeReference<List<SubjectDTO>>() {};
		
		ResponseEntity<List<SubjectDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

	public SubjectDTO getSubjectByNipt(String nipt) {
		final String BASE_URL = IApiClient.SERVER+"/api/subject/getSubjectByNipt/"+nipt;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<SubjectDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, SubjectDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

}
