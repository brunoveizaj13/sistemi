package com.brunoveizaj.sistemi.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brunoveizaj.sistemi.constants.IPatronageType;
import com.brunoveizaj.sistemi.constants.IStatus;
import com.brunoveizaj.sistemi.dao.CrudDAO;
import com.brunoveizaj.sistemi.dao.PatronageDAO;
import com.brunoveizaj.sistemi.dao.UserDAO;
import com.brunoveizaj.sistemi.entities.Building;
import com.brunoveizaj.sistemi.entities.Institution;
import com.brunoveizaj.sistemi.entities.Patronage;
import com.brunoveizaj.sistemi.entities.PatronagePerson;
import com.brunoveizaj.sistemi.entities.PatronageType;
import com.brunoveizaj.sistemi.entities.Person;
import com.brunoveizaj.sistemi.entities.PersonDetails;
import com.brunoveizaj.sistemi.entities.User;
import com.brunoveizaj.sistemi.exceptions.EntityExistsException;
import com.brunoveizaj.sistemi.exceptions.ValidationException;
import com.brunoveizaj.sistemi.forms.PatronageForm;
import com.brunoveizaj.sistemi.forms.PatronagePersonForm;
import com.brunoveizaj.sistemi.forms.PatronagePersonModelForm;


@Service
public class PatronageService {

	
	@Autowired CrudDAO crudDAO;
	@Autowired PatronageDAO patronageDAO;
	@Autowired UserDAO userDAO;
	
	@Transactional
	public Patronage registerPatronage(PatronageForm form, String uname)
	{
		
		if(form.getPerson() == null)
		{
			throw new ValidationException("Zgjidhni personin");
		}

		Person person = crudDAO.findById(Person.class, form.getPerson().getNid());
		PersonDetails details = person.getDetails();
		String phone = (details.getPhone() == null?"":details.getPhone());
		String email = (details.getEmail()== null?"":details.getEmail());
		phone += (form.getPhone() == null?"":form.getPhone());
		email += (form.getEmail() == null?"":form.getEmail());
		details.setPhone(phone);
		details.setEmail(email);
		details.setPatronageStatus(IStatus.ACTIVE);				
		
		details = crudDAO.update(details);
		//person = crudDAO.update(person);
		
		if(form.getBuildingId() != null)
		{
			Building b = crudDAO.findById(Building.class, form.getBuildingId());
			b.setHasData(IStatus.ACTIVE);
			crudDAO.update(b);
		}
		
		Patronage existing = patronageDAO.findByNid(form.getPerson().getNid(), IPatronageType.PERSON);
		if(existing != null)
		{
			throw new EntityExistsException("Personi eshte regjistruar me perpara si Patronazhist");
		}

		Patronage p = new Patronage();
		p.setCreateTime(Calendar.getInstance().getTime());
		p.setCreateUser(uname);
		p.setPatronageType(crudDAO.findById(PatronageType.class, IPatronageType.PERSON));
		p.setPerson(person);
		p.setStatus(IStatus.ACTIVE);
		
		return crudDAO.create(p);
		
				
	}
	
	@Transactional
	public int registerPatronagePerson(PatronagePersonForm form, String uname)
	{
		if(form.getPatronage() == null)
		{
			throw new ValidationException("Zgjidhni patronazhistin");
		}
		
		if(form.getPersonForms() == null || form.getPersonForms().isEmpty())
		{
			throw new ValidationException("Nuk keni zgjedhur asnje person per te patronazhuar");
		}
		
		Patronage patronage = patronageDAO.findByNid(form.getPatronage().getNid(), IPatronageType.PERSON);
		if(patronage == null)
		{
			throw new ValidationException("Patronazhisti i papercaktuar");
		}	
		
		List<PatronagePerson> inPatronage = patronageDAO.getPatronagePersons(patronage.getId(), IPatronageType.PERSON);
		boolean hasData = (inPatronage != null && !inPatronage.isEmpty());
		
		int count = 0;
		for(PatronagePersonModelForm m : form.getPersonForms()) // per cdo person te perzgjedhur
		{
			boolean register = true;
			if(hasData) // bej check me listen ekzistuese nese e ka nen patronazh
			{
				for(PatronagePerson pp : inPatronage)
				{
					if(pp.getPerson().getNid().equals(m.getPerson().getNid())) //nqs eshte regjistruar me perpara nen patronazhin e ktij
					{
						register = false;
						break;
					}
				}
			}
			
			if(register) // duhet regjistruar
			{
				Person person = crudDAO.findById(Person.class, m.getPerson().getNid());
				PersonDetails details = person.getDetails();
				String phone = (details.getPhone() == null?"":details.getPhone());
				String email = (details.getEmail()== null?"":details.getEmail());
				phone += (m.getPhone() == null?"":m.getPhone());
				email += (m.getEmail() == null?"":m.getEmail());
				details.setPhone(phone);
				details.setEmail(email);
				details = crudDAO.update(details);
				//person.setInPatronageStatus(IStatus.ACTIVE);
				//person = crudDAO.update(person);
				
				PatronagePerson pp = new PatronagePerson();
				pp.setCreateTime(Calendar.getInstance().getTime());
				pp.setCreateUser(uname);
				pp.setPatronage(patronage);
				pp.setStatus(IStatus.ACTIVE);
				pp.setPerson(person);
				crudDAO.create(pp);
				
				count++;
			}
			
		}
		
		
		return count;
	}
	
	@Transactional
	public Patronage registerPatronageInstitution(PatronageForm form, String uname)
	{
		
		User u = userDAO.findByUsername(uname);
		
		form.setInstitutionId(u.getInstitution().getId());
		
		if(form.getPerson() == null)
		{
			throw new ValidationException("Zgjidhni personin");
		}

		if(form.getInstitutionId() == null)
		{
			throw new ValidationException("Perzgjidhni institucionin");
		}
		
		Institution i = crudDAO.findById(Institution.class, form.getInstitutionId());
		if(i == null || (i.getStatus() != IStatus.ACTIVE))
		{
			throw new ValidationException("Perzgjidhni institucionin");
		}
	
		
		Person person = crudDAO.findById(Person.class, form.getPerson().getNid());
		PersonDetails details = person.getDetails();
		String phone = (details.getPhone() == null?"":details.getPhone());
		String email = (details.getEmail()== null?"":details.getEmail());
		phone += (form.getPhone() == null?"":form.getPhone());
		email += (form.getEmail() == null?"":form.getEmail());
		details.setPhone(phone);
		details.setEmail(email);
		details.setPatronageInstitutionStatus(IStatus.ACTIVE);				
		details = crudDAO.update(details);
		//person = crudDAO.update(person);
		
		
		Patronage existing = patronageDAO.findByNid(form.getPerson().getNid(), IPatronageType.INSTITUTION);
		if(existing != null && (existing.getInstitution().getId() == i.getId())) // kontrrollo a eshte regjistruar me perpara ne kete institucion si patronazhist
		{
			throw new EntityExistsException("Personi eshte regjistruar me perpara si Patronazhist ne kete Institucion");
		}
		
		

		Patronage p = new Patronage();
		p.setCreateTime(Calendar.getInstance().getTime());
		p.setCreateUser(uname);
		p.setPatronageType(crudDAO.findById(PatronageType.class, IPatronageType.INSTITUTION));
		p.setPerson(person);
		p.setStatus(IStatus.ACTIVE);
		p.setInstitution(i);
		
		return crudDAO.create(p);
		
				
	}
	
	@Transactional
	public int registerPatronageInstitutionPerson(PatronagePersonForm form, String uname)
	{
		if(form.getPatronage() == null)
		{
			throw new ValidationException("Zgjidhni patronazhistin");
		}
		
		if(form.getPersonForms() == null || form.getPersonForms().isEmpty())
		{
			throw new ValidationException("Nuk keni zgjedhur asnje person per te patronazhuar");
		}
		
		Patronage patronage = patronageDAO.findByNid(form.getPatronage().getNid(), IPatronageType.INSTITUTION);
		if(patronage == null)
		{
			throw new ValidationException("Patronazhisti i papercaktuar");
		}	
		
		List<PatronagePerson> inPatronage = patronageDAO.getPatronagePersons(patronage.getId(), IPatronageType.INSTITUTION);
		boolean hasData = (inPatronage != null && !inPatronage.isEmpty());
		
		int count = 0;
		for(PatronagePersonModelForm m : form.getPersonForms()) // per cdo person te perzgjedhur
		{
			boolean register = true;
			if(hasData) // bej check me listen ekzistuese nese e ka nen patronazh
			{
				for(PatronagePerson pp : inPatronage)
				{
					if(pp.getPerson().getNid().equals(m.getPerson().getNid())) //nqs eshte regjistruar me perpara nen patronazhin e ktij
					{
						register = false;
						break;
					}
				}
			}
			
			if(register) // duhet regjistruar
			{
				Person person = crudDAO.findById(Person.class, m.getPerson().getNid());
				PersonDetails details = person.getDetails();
				String phone = (details.getPhone() == null?"":details.getPhone());
				String email = (details.getEmail()== null?"":details.getEmail());
				phone += (m.getPhone() == null?"":m.getPhone());
				email += (m.getEmail() == null?"":m.getEmail());
				details.setPhone(phone);
				details.setEmail(email);
				//person.setInPatronageStatus(IStatus.ACTIVE);
				
				details = crudDAO.update(details);
				//person = crudDAO.update(person);
				
				PatronagePerson pp = new PatronagePerson();
				pp.setCreateTime(Calendar.getInstance().getTime());
				pp.setCreateUser(uname);
				pp.setPatronage(patronage);
				pp.setStatus(IStatus.ACTIVE);
				pp.setPerson(person);
				crudDAO.create(pp);
				
				count++;
			}
			
		}
		
		
		return count;
	}
		
	public List<PatronagePerson> getPatronagePersons(Integer patronageId, Integer patronageType, String uname)
	{
		return patronageDAO.getPatronagePersons(patronageId, patronageType);
	}
	
	public Patronage findPatronageByNid(String patronageNid, Integer patronageType, String uname)
	{
		return patronageDAO.findByNid(patronageNid, patronageType);
	}
	
	
	
	public List<PatronagePerson> getPatronagesOfPerson(String personNid, Integer patronageTypeId, String uname)
	{
		return patronageDAO.getPatronagesOfPerson(personNid, patronageTypeId);
	}
	
	
	public List<Patronage> getPatronagesByArea(Integer unitId, Integer qvId, Integer patronageTypeId, String uname)
	{
		return patronageDAO.getPatronagesByArea(unitId, qvId, patronageTypeId);
	}
	
	
	
}
