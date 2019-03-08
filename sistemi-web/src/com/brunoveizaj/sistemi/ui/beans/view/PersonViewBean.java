package com.brunoveizaj.sistemi.ui.beans.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.models.EmployeeDTO;
import com.brunoveizaj.sistemi.ui.models.PatronageDTO;
import com.brunoveizaj.sistemi.ui.models.PatronagePersonDTO;
import com.brunoveizaj.sistemi.ui.models.PersonDTO;
import com.brunoveizaj.sistemi.ui.services.EmployeeService;
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
	List<EmployeeDTO> tatime;
	PersonDTO familyMember;

	
	
	List<PatronageDTO> unitPatronages;
	List<PatronagePersonDTO> personsInPatronage;
	List<PatronagePersonDTO> patronagedBy;
	
	
	
	
	public void init() {
		String nid = nav.getParam("nid");
		loadPersonRaport(nid);
	}

	public void loadPersonRaport(String nid) {
		try {

			this.person = new PersonService().findPersonByNid(nid);
			if (person != null) {
				this.family = new PersonService().getFamilyByNid(nid);
				this.familyMember = person;
				this.tatime = new EmployeeService().getEmployment(nid);
			}

		} catch (Exception e) {
			Messages.throwFacesMessage(e);
		}
	}

	public void onFamilySelect() {
		loadPersonRaport(this.familyMember.getNid());
	}

}
