package com.brunoveizaj.sistemi.ui.beans.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.constants.IPatronageType;
import com.brunoveizaj.sistemi.ui.models.DepartmentPositionDTO;
import com.brunoveizaj.sistemi.ui.models.EmployeeDTO;
import com.brunoveizaj.sistemi.ui.models.EmployeePeriodDTO;
import com.brunoveizaj.sistemi.ui.models.PatronageDTO;
import com.brunoveizaj.sistemi.ui.models.PatronagePersonDTO;
import com.brunoveizaj.sistemi.ui.models.PersonDTO;
import com.brunoveizaj.sistemi.ui.models.PoiDTO;
import com.brunoveizaj.sistemi.ui.services.DepartmentService;
import com.brunoveizaj.sistemi.ui.services.EmployeeService;
import com.brunoveizaj.sistemi.ui.services.PatronageService;
import com.brunoveizaj.sistemi.ui.services.PersonService;
import com.brunoveizaj.sistemi.ui.utils.Messages;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class PersonViewBean implements Serializable {

	@ManagedProperty(value = "#{navBean}")
	NavBean nav;

	PersonDTO person;
	List<PersonDTO> family;
	List<EmployeeDTO> employment;
	List<EmployeePeriodDTO> employmentPeriod;
	PersonDTO familyMember;

	
	
	List<PatronageDTO> unitPatronages;
	List<PatronagePersonDTO> personsInPatronage;
	List<PatronagePersonDTO> personsInstInPatronage;
	List<PatronagePersonDTO> patronagedBy;
	List<PatronagePersonDTO> patronagedByInst;
	
	List<PoiDTO> unitPois;
	
	List<DepartmentPositionDTO> departmentPositions;
	
	
	public void init() {
		
		this.person = null;
		this.family = null;
		this.employment = null;
		this.employmentPeriod = null;
		this.familyMember = null;		
		this.unitPatronages = null;
		this.personsInPatronage = null;
		this.personsInstInPatronage = null;
		this.patronagedBy = null;
		this.patronagedByInst = null;
		this.unitPois = null;
		this.departmentPositions = null;
		
		String nid = nav.getParam("nid");
		loadPersonRaport(nid);
		if(this.person != null)
		{
	    	loadFamilyRaport();
		}
	}

	public void loadPersonRaport(String nid) {
		try {

			this.person = new PersonService().findPersonByNid(nid);
			if (person != null) {
				this.familyMember = person;
				this.departmentPositions = new DepartmentService().getPersonParyHistory(nid);
			//	this.employment = new EmployeeService().getEmployment(nid);
				this.employmentPeriod = new EmployeeService().getEmploymentPeriods(nid);
				if(person.isPatronageStatus())
				{
				    PatronageDTO p = new PatronageService().findPatronageByNid(nid, IPatronageType.PERSON);
				    this.personsInPatronage = new PatronageService().getPatronagePersons(p.getId(), IPatronageType.PERSON);
				}
				if(person.isPatronageInstitutionStatus())
				{
				    PatronageDTO p = new PatronageService().findPatronageByNid(nid, IPatronageType.INSTITUTION);
				    this.personsInstInPatronage = new PatronageService().getPatronagePersons(p.getId(), IPatronageType.INSTITUTION);
				}
				
				this.patronagedBy = new PatronageService().getPatronagesOfPerson(nid, IPatronageType.PERSON);
				
				this.patronagedByInst = new PatronageService().getPatronagesOfPerson(nid, IPatronageType.INSTITUTION);
				
			}

		} catch (Exception e) {
			Messages.throwFacesMessage(e);
		}
	}

	public void onFamilySelect() {
		loadPersonRaport(this.familyMember.getNid());
	}
	
	
	public void loadFamilyRaport()
	{
		this.unitPatronages = new PatronageService().getPatronagesByArea(person.getQv().getUnit().getId(), null, IPatronageType.PERSON);
		this.unitPois = null;
		this.family = new PersonService().getFamilyByNid(this.person.getNid());
	}
	
	

}
