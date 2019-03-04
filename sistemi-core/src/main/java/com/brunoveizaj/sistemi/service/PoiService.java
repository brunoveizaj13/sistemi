package com.brunoveizaj.sistemi.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brunoveizaj.sistemi.constants.IStatus;
import com.brunoveizaj.sistemi.dao.CrudDAO;
import com.brunoveizaj.sistemi.entities.Building;
import com.brunoveizaj.sistemi.entities.Municipality;
import com.brunoveizaj.sistemi.entities.Person;
import com.brunoveizaj.sistemi.entities.PersonDetails;
import com.brunoveizaj.sistemi.entities.Poi;
import com.brunoveizaj.sistemi.entities.PoiType;
import com.brunoveizaj.sistemi.entities.Qv;
import com.brunoveizaj.sistemi.entities.Region;
import com.brunoveizaj.sistemi.entities.Unit;
import com.brunoveizaj.sistemi.exceptions.ValidationException;
import com.brunoveizaj.sistemi.forms.PoiForm;

@Service
public class PoiService {

	
	@Autowired CrudDAO crudDAO;
	
	@Transactional
	public Poi registerPoi(PoiForm form, String uname)
	{
		if(form.getPerson() == null)
		{
			throw new ValidationException("Zgjidhni personin");
		}
		
		if(form.getPoiTypeId() == null)
		{
			throw new ValidationException("Percaktoni tipin");
		}

		if(form.getQvId() == null && form.getUnitId() == null && form.getMunicipalityId() == null && form.getRegionId() == null)
		{
			throw new ValidationException("Percaktoni zonen qe mbulon");
		}
		
		Person person = crudDAO.findById(Person.class, form.getPerson().getNid());
		PersonDetails details = person.getDetails();
		String phone = (details.getPhone() == null?"":details.getPhone());
		String email = (details.getEmail()== null?"":details.getEmail());
		phone += (form.getPhone() == null?"":form.getPhone());
		email += (form.getEmail() == null?"":form.getEmail());
		details.setPhone(phone);
		details.setEmail(email);
		details.setPoiStatus(IStatus.ACTIVE);				
		
		details = crudDAO.update(details);
		//person = crudDAO.update(person);
		
		if(form.getBuildingId() != null)
		{
			Building b = crudDAO.findById(Building.class, form.getBuildingId());
			b.setHasData(IStatus.ACTIVE);
			crudDAO.update(b);
		}
		
		
		Poi poi = new Poi();
		poi.setCreateTime(Calendar.getInstance().getTime());
		poi.setCreateUser(uname);
		poi.setPerson(person);
		poi.setPoiType(crudDAO.findById(PoiType.class, form.getPoiTypeId()));
		poi.setStatus(IStatus.ACTIVE);
		
		if(form.getQvId() != null)
		{
			poi.setQv(crudDAO.findById(Qv.class, form.getQvId()));
		}		
		if(form.getUnitId() != null)
		{
			poi.setUnit(crudDAO.findById(Unit.class, form.getUnitId()));
		}
		if(form.getMunicipalityId() != null)
		{
			poi.setMunicipality(crudDAO.findById(Municipality.class, form.getMunicipalityId()));
		}
		if(form.getRegionId() != null)
		{
			poi.setRegion(crudDAO.findById(Region.class, form.getRegionId()));
		}
		
		return crudDAO.create(poi);
		
	}
	
	
	
}
