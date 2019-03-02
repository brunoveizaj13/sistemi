package com.brunoveizaj.sistemi.ui.services;

import java.util.List;

import com.brunoveizaj.sistemi.ui.api.clients.HelperClient;
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

public class HelperService {

	
	

	
		
		public List<PartyDTO> listParty()
		{
			return new HelperClient().listParty();
		}
		
		public PartyDTO findParty(String code)
		{
			return new HelperClient().findParty(code);
		}
		
		public List<PoiTypeDTO> listPoiType()
		{
			return new HelperClient().listPoiType();
		}
		
		public PoiTypeDTO findPoiType(Integer id)
		{
			return new HelperClient().findPoiType(id);
		}
		
		public List<PatronageTypeDTO> listPatronageType()
		{
			return new HelperClient().listPatronageType();
		}
		
		public PatronageTypeDTO findPatronageType(Integer id)
		{
			return new HelperClient().findPatronageType(id);
		}
		
		public List<RoleDTO> listRole()
		{
			return new HelperClient().listRole();
		}
		
		public RoleDTO findRole(String code)
		{
			return new HelperClient().findRole(code);
		}
		
		public List<InstitutionDTO> listInstitution()
		{
			return new HelperClient().listInstitution();
		}
		
		public InstitutionDTO findInstitution(Integer id)
		{
			return new HelperClient().findInstitution(id);
		}
		
		public List<RegionDTO> listRegion()
		{
			return new HelperClient().listRegion();
		}
		
		public RegionDTO findRegion(Integer id)
		{
			return new HelperClient().findRegion(id);
		}
		
		public List<MunicipalityDTO> listMunicipality(Integer regionId, String name)
		{
			return new HelperClient().listMunicipality(regionId, name);
		}
		
		public MunicipalityDTO findMunicipality(Integer id)
		{
			return new HelperClient().findMunicipality(id);
		}
		
		public List<UnitDTO> listUnit(Integer regionId, Integer municipalityId, String name)
		{
			return new HelperClient().listUnit(regionId, municipalityId, name);
		}
		
		public UnitDTO findUnit(Integer id)
		{
			return new HelperClient().findUnit(id);
		}
		
		public List<QvDTO> listQv(Integer regionId, Integer municipalityId,  Integer unitId, String code)
		{
			return new HelperClient().listQv(regionId, municipalityId, unitId, code);
		}
		
		public QvDTO findQv(Integer id)
		{
			return new HelperClient().findQv(id);
		}
		
		public List<MonthYear> listpayrollMonthYears()
		{
			return new HelperClient().listpayrollMonthYears();
		}
		
		
		
		

	}

	
	
	

