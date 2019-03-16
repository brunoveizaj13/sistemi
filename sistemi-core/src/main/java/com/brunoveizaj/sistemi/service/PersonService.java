package com.brunoveizaj.sistemi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunoveizaj.sistemi.dao.CrudDAO;
import com.brunoveizaj.sistemi.dao.PersonDAO;
import com.brunoveizaj.sistemi.entities.Person;
import com.brunoveizaj.sistemi.forms.PersonSx;

@Service
public class PersonService {
	
	
	@Autowired CrudDAO crudDAO;
	@Autowired PersonDAO personDAO;
	
	
	
	public Person findPersonByNid(String nid, String uname)
	{
		return crudDAO.findById(Person.class, nid);
	}
	
	public List<Person> getFamilyByFamilyId(Long familyId, String uname)
	{
		return personDAO.getFamily(familyId);
	}
	
	public List<Person> getFamilyByNid(String nid, String uname)
	{
		return personDAO.getFamily(nid);
	}
	
	public List<Person> searchPerson(PersonSx req, String uname)
	{
		return personDAO.searchPerson(req);
	}

	public String getPhotoByNid(String nid) {
		return personDAO.getPhotoByNid(nid);
	}

}
