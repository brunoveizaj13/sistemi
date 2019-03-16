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
import com.brunoveizaj.sistemi.ui.models.map.BuildingMap;
import com.brunoveizaj.sistemi.ui.models.map.PatronagePoint;
import com.brunoveizaj.sistemi.ui.models.map.PersonPoint;
import com.brunoveizaj.sistemi.ui.models.map.PoiPoint;
import com.brunoveizaj.sistemi.ui.models.map.QvMap;
import com.brunoveizaj.sistemi.ui.models.map.UnitMap;
import com.brunoveizaj.sistemi.ui.utils.Util;

public class MapClient {

	
	public List<PersonPoint> getPersonPointByNid(String nid)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/map/getPersonPointByNid/"+nid;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		System.out.println(BASE_URL);
		
		ParameterizedTypeReference<List<PersonPoint>> typeRef = new ParameterizedTypeReference<List<PersonPoint>>() {};
		ResponseEntity<List<PersonPoint>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
		
		return null;
	}
	
	public List<PatronagePoint> getPatronagesPointByArea(Integer qvId, Integer unitId, Integer patronageTypeId)
	{
		String BASE_URL = IApiClient.SERVER+"/api/map/getPatronagesPointByArea";
		
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
		
		System.out.println(BASE_URL);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PatronagePoint>> typeRef = new ParameterizedTypeReference<List<PatronagePoint>>() {};
		
		ResponseEntity<List<PatronagePoint>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<PersonPoint> getInPatronagePointsByPatronageNid(Integer patronageNid, Integer patronageTypeId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/map/getInPatronagePointsByPatronageNid/"+patronageNid+"/"+patronageTypeId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);	
		
		System.out.println(BASE_URL);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ParameterizedTypeReference<List<PersonPoint>> typeRef = new ParameterizedTypeReference<List<PersonPoint>>() {};
		ResponseEntity<List<PersonPoint>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
		
		return null;
	}
	
	public List<PersonPoint> getInPatronagePointsByArea(Integer qvId, Integer unitId, Integer patronageTypeId)
	{			
		String BASE_URL = IApiClient.SERVER+"/api/map/getInPatronagePointsByArea";
		
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
		
		System.out.println(BASE_URL);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PersonPoint>> typeRef = new ParameterizedTypeReference<List<PersonPoint>>() {};
		
		ResponseEntity<List<PersonPoint>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<PoiPoint> getPoisPointByArea(Integer qvId, Integer unitId)
	{
		String BASE_URL = IApiClient.SERVER+"/api/map/getPoisPointByArea";
		
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
		
		System.out.println(BASE_URL);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PoiPoint>> typeRef = new ParameterizedTypeReference<List<PoiPoint>>() {};
		
		ResponseEntity<List<PoiPoint>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<BuildingMap> getBuildingsByArea(Integer qvId, Integer unitId)
	{
		String BASE_URL = IApiClient.SERVER+"/api/map/getBuildingsByArea";
		
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
		
		System.out.println(BASE_URL);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<BuildingMap>> typeRef = new ParameterizedTypeReference<List<BuildingMap>>() {};
		
		ResponseEntity<List<BuildingMap>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public QvMap getQvById(Integer qvId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/map/getQvById/"+qvId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		
		System.out.println(BASE_URL);
		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<QvMap> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, QvMap.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
		
		return null;
	}
	
	public List<QvMap> getQvsByUnitId(Integer unitId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/map/getQvsByUnitId/"+unitId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		
		System.out.println(BASE_URL);
		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ParameterizedTypeReference<List<QvMap>> typeRef = new ParameterizedTypeReference<List<QvMap>>() {};
		
		ResponseEntity<List<QvMap>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
		
		return null;
	}
	
	public UnitMap getUnitById(Integer unitId)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/map/getUnitById/"+unitId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		System.out.println(BASE_URL);
		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<UnitMap> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, UnitMap.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
		
		return null;
	}
	
	public List<PersonPoint> getFirstTimeVoterPointsByArea(Integer qvId, Integer unitId)
	{
		String BASE_URL = IApiClient.SERVER+"/api/map/getFirstTimeVoterPointsByArea";
		
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
		
		System.out.println(BASE_URL);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PersonPoint>> typeRef = new ParameterizedTypeReference<List<PersonPoint>>() {};
		
		ResponseEntity<List<PersonPoint>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<PersonPoint> getMemberPointsByArea(Integer qvId, Integer unitId)
	{
		String BASE_URL = IApiClient.SERVER+"/api/map/getMemberPointsByArea";
		
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
		
		System.out.println(BASE_URL);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PersonPoint>> typeRef = new ParameterizedTypeReference<List<PersonPoint>>() {};
		
		ResponseEntity<List<PersonPoint>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
}
