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
import com.brunoveizaj.sistemi.ui.forms.EmployeeSx;
import com.brunoveizaj.sistemi.ui.models.EmployeeDTO;
import com.brunoveizaj.sistemi.ui.models.EmployeePeriodDTO;
import com.brunoveizaj.sistemi.ui.utils.Util;

public class EmployeeClient {

	
	
	
	public List<EmployeePeriodDTO> getEmploymentPeriods(String nid) {


		final String BASE_URL = IApiClient.SERVER+"/api/employee/getEmploymentPeriods/"+nid.trim();
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<EmployeePeriodDTO>> typeRef = new ParameterizedTypeReference<List<EmployeePeriodDTO>>() {};
		
		ResponseEntity<List<EmployeePeriodDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
		
		
	}
	
	
	
	public List<EmployeeDTO> getEmployment(String nid) {


		final String BASE_URL = IApiClient.SERVER+"/api/employee/getEmployment/"+nid.trim();
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<EmployeeDTO>> typeRef = new ParameterizedTypeReference<List<EmployeeDTO>>() {};
		
		ResponseEntity<List<EmployeeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
		
		
	}

	public List<EmployeeDTO> getEmployees(String nipt, Integer year, Integer month) {
		
		final String BASE_URL = IApiClient.SERVER+"/api/employee/getEmployees/"+nipt.trim()+"/"+year+"/"+month;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<EmployeeDTO>> typeRef = new ParameterizedTypeReference<List<EmployeeDTO>>() {};
		
		ResponseEntity<List<EmployeeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

	public List<EmployeeDTO> getEmployment(EmployeeSx req) {
		
		final String BASE_URL = IApiClient.SERVER+"/api/employee/getEmployment";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(req, headers);
		
		ParameterizedTypeReference<List<EmployeeDTO>> typeRef = new ParameterizedTypeReference<List<EmployeeDTO>>() {};
		
		ResponseEntity<List<EmployeeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

}
