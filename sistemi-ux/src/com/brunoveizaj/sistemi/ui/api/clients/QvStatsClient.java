package com.brunoveizaj.sistemi.ui.api.clients;

import java.util.Date;
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
import com.brunoveizaj.sistemi.ui.models.AlbaniaStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.KeyValue;
import com.brunoveizaj.sistemi.ui.models.MunicipalityStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.PersonDTO;
import com.brunoveizaj.sistemi.ui.models.QvStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.RegionStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.UnitStatisticDTO;
import com.brunoveizaj.sistemi.ui.utils.DateUtil;
import com.brunoveizaj.sistemi.ui.utils.Util;

public class QvStatsClient {

	
	
	public List<PersonDTO> persons(Integer qvId) {
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/persons/"+qvId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
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

	public List<PersonDTO> personsNotInPatronage(Integer qvId) {
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/personsNotInPatronage/"+qvId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
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

	public List<PersonDTO> personsInPatronage(Integer qvId, Integer type) {
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/personsInPatronage/"+qvId+"/"+type;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
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
	
	public List<PersonDTO> patronages(Integer qvId, Integer type) {
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/patronages/"+qvId+"/"+type;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
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
	
	
	public List<PersonDTO> firstTimeVoters(Integer qvId) {
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/firstTimeVoters/"+qvId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
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
	
	
	public List<PersonDTO> notVotingLastElections(Integer qvId) {
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/notVotingLastElections/"+qvId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
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
	
	
	
	public List<QvStatisticDTO> getQvStatisticsByUnit(Integer unitId) {
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/getQvStatisticsByUnit/"+unitId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<QvStatisticDTO>> typeRef = new ParameterizedTypeReference<List<QvStatisticDTO>>() {};
		
		ResponseEntity<List<QvStatisticDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	public List<UnitStatisticDTO> getUnitStatisticsByMunicipality(Integer regionId, Integer munId) {
		
		if(regionId == null) regionId = 0;
		if(munId == null) munId = 0;
		
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/getUnitStatisticsByMunicipality/"+regionId+"/"+munId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<UnitStatisticDTO>> typeRef = new ParameterizedTypeReference<List<UnitStatisticDTO>>() {};
		
		ResponseEntity<List<UnitStatisticDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

	public List<MunicipalityStatisticDTO> getMunicipalityStatisticsByRegion(Integer regionId) {
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/getMunicipalityStatisticsByRegion/"+regionId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<MunicipalityStatisticDTO>> typeRef = new ParameterizedTypeReference<List<MunicipalityStatisticDTO>>() {};
		
		ResponseEntity<List<MunicipalityStatisticDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<RegionStatisticDTO> getRegionStatistics() {
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/getRegionStatistics";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<RegionStatisticDTO>> typeRef = new ParameterizedTypeReference<List<RegionStatisticDTO>>() {};
		
		ResponseEntity<List<RegionStatisticDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	public QvStatisticDTO getQvStatistic(Integer id)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/getQvStatistic/"+id;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<QvStatisticDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, QvStatisticDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public UnitStatisticDTO getUnitStatistic(Integer id)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/getUnitStatistic/"+id;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<UnitStatisticDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, UnitStatisticDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public MunicipalityStatisticDTO getMunicipalityStatistic(Integer id)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/getMunicipalityStatistic/"+id;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<MunicipalityStatisticDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, MunicipalityStatisticDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public RegionStatisticDTO getRegionStatistic(Integer id)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/getRegionStatistic/"+id;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<RegionStatisticDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, RegionStatisticDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	public AlbaniaStatisticDTO getAlbaniaStatistic()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/getAlbaniaStatistic/";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<AlbaniaStatisticDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, AlbaniaStatisticDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	public List<KeyValue> getInstitutionPatronages(Integer regionId, Integer munId, Integer unitId, Integer qvId) {
		
		if(regionId == null) regionId = 0;
		if(munId == null) munId = 0;
		if(unitId == null) unitId = 0;
		if(qvId == null) qvId = 0;
		
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/getInstitutionPatronages/"+regionId+"/"+munId+"/"+unitId+"/"+qvId;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
    public List<KeyValue> getInstitutionInPatronages(Integer regionId, Integer munId, Integer unitId, Integer qvId) {
		
		if(regionId == null) regionId = 0;
		if(munId == null) munId = 0;
		if(unitId == null) unitId = 0;
		if(qvId == null) qvId = 0;
		
		
		final String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/getInstitutionInPatronages/"+regionId+"/"+munId+"/"+unitId+"/"+qvId;;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

    //***************************
	public List<KeyValue> getPatronagesByDate(Date from, Date to, Integer type, Integer regionId, Integer munId,
			Integer unitId, Integer qvId) {
				
		String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/getPatronagesByDate/"+type;
		
		
		boolean isFirst = true;
		
		if(regionId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("regionId=" + regionId));
			isFirst = false;
		}
		if(munId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("municipalityId=" + munId));
			isFirst = false;
		}
		if(unitId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("unitId=" + unitId));
			isFirst = false;
		}
		if(qvId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("qvId=" + qvId));
			isFirst = false;
		}
		if(from != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("from=" + DateUtil.formatDate(from)));
			isFirst = false;
		}
		if(to != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("to=" + DateUtil.formatDate(to)));
			isFirst = false;
		}
				
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}

	public List<KeyValue> getInPatronagesByDate(Date from, Date to, Integer type, Integer regionId, Integer munId,
			Integer unitId, Integer qvId) {
		
        String BASE_URL = IApiClient.SERVER+"/api/statistic/qv/getInPatronagesByDate/"+type;
		
		
		boolean isFirst = true;
		
		if(regionId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("regionId=" + regionId));
			isFirst = false;
		}
		if(munId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("municipalityId=" + munId));
			isFirst = false;
		}
		if(unitId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("unitId=" + unitId));
			isFirst = false;
		}
		if(qvId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("qvId=" + qvId));
			isFirst = false;
		}
		if(from != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("from=" + DateUtil.formatDate(from)));
			isFirst = false;
		}
		if(to != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("to=" + DateUtil.formatDate(to)));
			isFirst = false;
		}
				
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new ApiErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+Util.getToken());
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<KeyValue>> typeRef = new ParameterizedTypeReference<List<KeyValue>>() {};
		
		ResponseEntity<List<KeyValue>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	
	
}
