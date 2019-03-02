package com.brunoveizaj.sistemi.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunoveizaj.sistemi.dao.CrudDAO;
import com.brunoveizaj.sistemi.entities.Institution;
import com.brunoveizaj.sistemi.entities.Municipality;
import com.brunoveizaj.sistemi.entities.Party;
import com.brunoveizaj.sistemi.entities.PatronageType;
import com.brunoveizaj.sistemi.entities.PoiType;
import com.brunoveizaj.sistemi.entities.Qv;
import com.brunoveizaj.sistemi.entities.Region;
import com.brunoveizaj.sistemi.entities.Role;
import com.brunoveizaj.sistemi.entities.Unit;
import com.brunoveizaj.sistemi.models.MonthYear;


@Service
public class HelperService {
	
	
	@Autowired CrudDAO crudDAO;
	
	
	   //*********** FIND BY ID INTEGER******************
		public <T> T findById(Class<T> claxx, Integer id) {
			return crudDAO.findById(claxx, id);
		}
		
		//*********** FIND BY ID STRING******************
		public <T> T findById(Class<T> claxx, String id) {
			return crudDAO.findById(claxx, id);
		}
	
	
		
		public List<Institution> listInstitution()
		{
			return crudDAO.listInstitution();
		}
		
		public List<Region> listRegion()
		{
			return crudDAO.listRegion();
		}
		
		public List<Municipality> listMunicipality(Integer regionId, String name)
		{
			return crudDAO.listMunicipality(regionId, name);
		}
		
		public List<Unit> listUnit(Integer regionId, Integer municipId, String name)
		{
			return crudDAO.listUnit(regionId, municipId, name);
		}
		
		public List<Qv> listQv(Integer regionId, Integer municipId, Integer unitId, String code)
		{
			return crudDAO.listQv(regionId, municipId, unitId, code);
		}
		
		public List<Party> listParty()
		{
			return crudDAO.listParty();
		}
		
		public List<PatronageType> listPatronageType()
		{
			return crudDAO.listPatronageType();
		}
		
		public List<PoiType> listPoiType()
		{
			return crudDAO.listPoiType();
		}
		
		public List<Role> listRole()
		{
			return crudDAO.listRole();
		}
		
		public List<MonthYear> getPayrollMonthYears()
		{
			return crudDAO.getPayrollMonthYears();
		}
	

}
