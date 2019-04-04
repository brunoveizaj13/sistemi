package com.brunoveizaj.sistemi.ui.services;

import java.util.List;

import com.brunoveizaj.sistemi.ui.api.clients.PersonClient;
import com.brunoveizaj.sistemi.ui.forms.PersonSx;
import com.brunoveizaj.sistemi.ui.models.PersonDTO;

public class PersonService {
	
	
	public List<PersonDTO> searchPerson(PersonSx req)
	{
		return new PersonClient().searchPerson(req);
	}
	
	public PersonDTO findPersonByNid(String nid)
	{
		return new PersonClient().findPersonByNid(nid);
	}
	
	public List<PersonDTO> getFamilyByFamilyId(long familyId)
	{
		return new PersonClient().getFamilyByFamilyId(familyId);
	}
	
	public List<PersonDTO> getFamilyByNid(String nid)
	{
		return new PersonClient().getFamilyByNid(nid);
	}

	public String getPhotoByNid(String nid) 
	{
		return new PersonClient().getPhotoByNid(nid);
	}

}
