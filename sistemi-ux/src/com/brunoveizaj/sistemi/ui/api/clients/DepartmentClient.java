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
import com.brunoveizaj.sistemi.ui.forms.DepartmentForm;
import com.brunoveizaj.sistemi.ui.forms.DepartmentPositionForm;
import com.brunoveizaj.sistemi.ui.models.DepartmentDTO;
import com.brunoveizaj.sistemi.ui.models.DepartmentFunctionDTO;
import com.brunoveizaj.sistemi.ui.models.DepartmentPositionDTO;
import com.brunoveizaj.sistemi.ui.utils.Util;

public class DepartmentClient {

	
	
	
	public DepartmentDTO registerDepartment(DepartmentForm form)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/department/registerDepartment";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(form, headers);
		
		
		ResponseEntity<DepartmentDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, DepartmentDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<DepartmentFunctionDTO> listDepartmentFunctions()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/department/listDepartmentFunctions";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<DepartmentFunctionDTO>> typeRef = new ParameterizedTypeReference<List<DepartmentFunctionDTO>>() {};
		
		ResponseEntity<List<DepartmentFunctionDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<DepartmentPositionDTO> getDepartmentPositions(Integer deptId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/department/getDepartmentPositions/"+deptId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<DepartmentPositionDTO>> typeRef = new ParameterizedTypeReference<List<DepartmentPositionDTO>>() {};
		
		ResponseEntity<List<DepartmentPositionDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<DepartmentDTO> getChildDepartments(Integer deptId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/department/getChildDepartments/"+deptId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<DepartmentDTO>> typeRef = new ParameterizedTypeReference<List<DepartmentDTO>>() {};
		
		ResponseEntity<List<DepartmentDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public DepartmentDTO getRootDepartment()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/department/getRootDepartment";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		
		ResponseEntity<DepartmentDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, DepartmentDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

	public DepartmentDTO getMunicipalityRootDepartment(Integer munId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/department/getMunicipalityRootDepartment/"+munId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		
		ResponseEntity<DepartmentDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, DepartmentDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	public DepartmentPositionDTO registerDepartmentPosition(DepartmentPositionForm form) {
		final String BASE_URL = IApiClient.SERVER+"/api/department/registerDepartmentPosition";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(form, headers);
		
		
		ResponseEntity<DepartmentPositionDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, DepartmentPositionDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

	public List<DepartmentPositionDTO> getDepartmentPositionsHistory(Integer deptId) {
		final String BASE_URL = IApiClient.SERVER+"/api/department/getDepartmentPositionsHistory/"+deptId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<DepartmentPositionDTO>> typeRef = new ParameterizedTypeReference<List<DepartmentPositionDTO>>() {};
		
		ResponseEntity<List<DepartmentPositionDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

	public List<DepartmentPositionDTO> getPersonParyHistory(String nid) {
		final String BASE_URL = IApiClient.SERVER+"/api/department/getPersonParyHistory/"+nid;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<DepartmentPositionDTO>> typeRef = new ParameterizedTypeReference<List<DepartmentPositionDTO>>() {};
		
		ResponseEntity<List<DepartmentPositionDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
}
