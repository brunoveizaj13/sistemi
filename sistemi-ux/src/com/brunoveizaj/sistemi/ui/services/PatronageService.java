package com.brunoveizaj.sistemi.ui.services;

import java.util.List;

import com.brunoveizaj.sistemi.ui.api.clients.PatronageClient;
import com.brunoveizaj.sistemi.ui.forms.PatronageForm;
import com.brunoveizaj.sistemi.ui.forms.PatronagePersonForm;
import com.brunoveizaj.sistemi.ui.models.PatronageDTO;
import com.brunoveizaj.sistemi.ui.models.PatronagePersonDTO;

public class PatronageService {

	
	
	public PatronageDTO registerPatronage(PatronageForm form)
	{
		return new PatronageClient().registerPatronage(form);
	}
	
	public PatronageDTO modifyPatronage(PatronageForm form)
	{
		return null;
	}
	
	public PatronageDTO registerPatronageInstitution(PatronageForm form) 
	{
		return new PatronageClient().registerPatronageInstitution(form);
	}
	
	public int registerPatronagePerson(PatronagePersonForm form)
	{
		return new PatronageClient().registerPatronagePerson(form);
	}
	
	public int registerPatronageInstitutionPerson(PatronagePersonForm form)
	{
		return new PatronageClient().registerPatronageInstitutionPerson(form);
	}
	
	public List<PatronagePersonDTO> getPatronagePersons(Integer patronageId, Integer patronageType)
	{
		return new PatronageClient().getPatronagePersons(patronageId, patronageType);
	}
	
	public PatronageDTO findPatronageByNid(String nid, Integer patronageType)
	{
		return new PatronageClient().findPatronageByNid(nid, patronageType);
	}

	public List<PatronagePersonDTO> getPatronagesOfPerson(String nid, Integer patronageTypeId) {
		
		return new PatronageClient().getPatronagesOfPerson(nid, patronageTypeId);
	}
	
	public List<PatronageDTO> getPatronagesByArea(Integer unitId, Integer qvId, Integer patronageTypeId)
	{
		return new PatronageClient().getPatronagesByArea(unitId, qvId, patronageTypeId);
	}
	

	
	
	
	
	
}
