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
import com.brunoveizaj.sistemi.ui.models.InstitutionDTO;
import com.brunoveizaj.sistemi.ui.models.MonthYear;
import com.brunoveizaj.sistemi.ui.models.MunicipalityDTO;
import com.brunoveizaj.sistemi.ui.models.PartyDTO;
import com.brunoveizaj.sistemi.ui.models.PatronageTypeDTO;
import com.brunoveizaj.sistemi.ui.models.PoiTypeDTO;
import com.brunoveizaj.sistemi.ui.models.QvDTO;
import com.brunoveizaj.sistemi.ui.models.RegionDTO;
import com.brunoveizaj.sistemi.ui.models.RoleDTO;
import com.brunoveizaj.sistemi.ui.models.UnitDTO;
import com.brunoveizaj.sistemi.ui.utils.StringUtil;



public class HelperClient {
	
	
	public List<PartyDTO> listParty()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/party";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PartyDTO>> typeRef = new ParameterizedTypeReference<List<PartyDTO>>() {};
		
		ResponseEntity<List<PartyDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public PartyDTO findParty(String code)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/find/party/"+code;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<PartyDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, PartyDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<PoiTypeDTO> listPoiType()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/poiType";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PoiTypeDTO>> typeRef = new ParameterizedTypeReference<List<PoiTypeDTO>>() {};
		
		ResponseEntity<List<PoiTypeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public PoiTypeDTO findPoiType(Integer id)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/find/poiType/"+id;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<PoiTypeDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, PoiTypeDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<PatronageTypeDTO> listPatronageType()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/patronageType";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<PatronageTypeDTO>> typeRef = new ParameterizedTypeReference<List<PatronageTypeDTO>>() {};
		
		ResponseEntity<List<PatronageTypeDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public PatronageTypeDTO findPatronageType(Integer id)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/find/patronageType/"+id;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<PatronageTypeDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, PatronageTypeDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<RoleDTO> listRole()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/role";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<RoleDTO>> typeRef = new ParameterizedTypeReference<List<RoleDTO>>() {};
		
		ResponseEntity<List<RoleDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public RoleDTO findRole(String code)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/find/role/"+code;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<RoleDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, RoleDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<InstitutionDTO> listInstitution()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/institution";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<InstitutionDTO>> typeRef = new ParameterizedTypeReference<List<InstitutionDTO>>() {};
		
		ResponseEntity<List<InstitutionDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public InstitutionDTO findInstitution(Integer id)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/find/institution/"+id;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<InstitutionDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, InstitutionDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<RegionDTO> listRegion()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/region";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<RegionDTO>> typeRef = new ParameterizedTypeReference<List<RegionDTO>>() {};
		
		ResponseEntity<List<RegionDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public RegionDTO findRegion(Integer id)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/find/region/"+id;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<RegionDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, RegionDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public List<MunicipalityDTO> listMunicipality(Integer regionId, String name)
	{
		String BASE_URL = IApiClient.SERVER+"/api/helper/list/municipality";
		
		boolean isFirst = true;
		
		if(regionId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("regionId=" + regionId));
			isFirst = false;
		}
		
		if(StringUtil.isValid(name))
		{
			BASE_URL += ((isFirst?"?":"&") + ("name=" + name));
			isFirst = false;
		}
		
		
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<MunicipalityDTO>> typeRef = new ParameterizedTypeReference<List<MunicipalityDTO>>() {};
		
		ResponseEntity<List<MunicipalityDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public MunicipalityDTO findMunicipality(Integer id)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/find/municipality/"+id;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<MunicipalityDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, MunicipalityDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
		
		return null;
				
	}
	
	public List<UnitDTO> listUnit(Integer regionId, Integer municipalityId, String name)
	{
		String BASE_URL = IApiClient.SERVER+"/api/helper/list/unit";
		
		boolean isFirst = true;
		
		if(regionId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("regionId=" + regionId));
			isFirst = false;
		}
		if(municipalityId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("municipalityId=" + municipalityId));
			isFirst = false;
		}
		
		if(StringUtil.isValid(name))
		{
			BASE_URL += ((isFirst?"?":"&") + ("name=" + name));
			isFirst = false;
		}
		
		
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<UnitDTO>> typeRef = new ParameterizedTypeReference<List<UnitDTO>>() {};
		
		ResponseEntity<List<UnitDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public UnitDTO findUnit(Integer id)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/find/unit/"+id;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<UnitDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, UnitDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
		
		return null;
	}
	
	public List<QvDTO> listQv(Integer regionId, Integer municipalityId,  Integer unitId, String code)
	{
		String BASE_URL = IApiClient.SERVER+"/api/helper/list/qv";
		
		boolean isFirst = true;
		
		if(regionId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("regionId=" + regionId));
			isFirst = false;
		}
		if(municipalityId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("municipalityId=" + municipalityId));
			isFirst = false;
		}
		if(unitId != null)
		{
			BASE_URL += ((isFirst?"?":"&") + ("unit=" + unitId));
			isFirst = false;
		}
		if(StringUtil.isValid(code))
		{
			BASE_URL += ((isFirst?"?":"&") + ("code=" + code));
			isFirst = false;
		}
		
		
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<QvDTO>> typeRef = new ParameterizedTypeReference<List<QvDTO>>() {};
		
		ResponseEntity<List<QvDTO>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	public QvDTO findQv(Integer id)
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/find/qv/"+id;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
				
		ResponseEntity<QvDTO> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, QvDTO.class);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
		
		return null;
	}
	
	public List<MonthYear> listpayrollMonthYears()
	{
		final String BASE_URL = IApiClient.SERVER+"/api/helper/list/payrollMonthYears";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL);		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);	
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ParameterizedTypeReference<List<MonthYear>> typeRef = new ParameterizedTypeReference<List<MonthYear>>() {};
		
		ResponseEntity<List<MonthYear>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, typeRef);
		
		if(response.getStatusCode() == HttpStatus.OK)
		{
			return response.getBody();
		}
				
		return null;
	}
	
	
	
	

}
