package com.brunoveizaj.sistemi.ui.beans.operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.constants.IPatronageType;
import com.brunoveizaj.sistemi.ui.forms.PatronageForm;
import com.brunoveizaj.sistemi.ui.forms.PatronagePersonForm;
import com.brunoveizaj.sistemi.ui.forms.PatronagePersonModelForm;
import com.brunoveizaj.sistemi.ui.forms.PersonSx;
import com.brunoveizaj.sistemi.ui.models.Param;
import com.brunoveizaj.sistemi.ui.models.PatronageDTO;
import com.brunoveizaj.sistemi.ui.models.PatronagePersonDTO;
import com.brunoveizaj.sistemi.ui.models.PersonDTO;
import com.brunoveizaj.sistemi.ui.models.QvDTO;
import com.brunoveizaj.sistemi.ui.services.HelperService;
import com.brunoveizaj.sistemi.ui.services.PatronageService;
import com.brunoveizaj.sistemi.ui.services.PersonService;
import com.brunoveizaj.sistemi.ui.utils.Messages;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class OptgViewBean implements Serializable {

	@ManagedProperty(value = "#{navBean}")
	NavBean nav;

	PatronageForm patronageForm;
	PatronageDTO selectedPatronage;
	List<PatronagePersonDTO> personsInPatronage;

	PersonSx req;
	List<QvDTO> qvs;
	QvDTO selectedQv;
	List<PatronagePersonModelForm> personForms;
	List<PatronagePersonModelForm> selectedPersonForms;

	boolean registerPatronage;

	public void init() {
		String nid = nav.getParam("nid");
		loadPatronageRaport(nid);
		prepareDialog();
	}

	public void loadPatronageRaport(String nid) {
		try {

			this.selectedPatronage = new PatronageService().findPatronageByNid(nid, IPatronageType.PERSON);
			if (selectedPatronage == null) {
				this.registerPatronage = true;
				PersonDTO p = new PersonService().findPersonByNid(nid);
				this.selectedPatronage = new PatronageDTO();
				selectedPatronage.setPerson(p);

				this.patronageForm = new PatronageForm();
				patronageForm.setPerson(p);
				patronageForm.setPhone(p.getPhone());
				this.personsInPatronage = null;
				Messages.throwFacesMessage(
						"Regjistroni me pare personin si patronazhist, pastaj vazhdoni me shtimin e banoreve nen patronazh",
						2);
			} else {
				this.registerPatronage = false;
				this.patronageForm = new PatronageForm();
				patronageForm.setPerson(selectedPatronage.getPerson());
				patronageForm.setPhone(selectedPatronage.getPerson().getPhone());

				this.personsInPatronage = new PatronageService().getPatronagePersons(selectedPatronage.getId(),
						IPatronageType.PERSON);
			}

		} catch (Exception e) {
			Messages.throwFacesMessage(e);
		}
	}

	public void registerPatronage() {
		try {

			this.selectedPatronage = new PatronageService().registerPatronage(patronageForm);
			Messages.throwFacesMessage("Patronazhisti u regjistrua me sukses!", 1);
			this.registerPatronage = false;

		} catch (Exception e) {
			Messages.throwFacesMessage(e);
		}
	}

	public void modifyPatronage() {
		try {

			this.selectedPatronage = new PatronageService().modifyPatronage(patronageForm);
			Messages.throwFacesMessage("Patronazhisti u modifikua me sukses!", 1);
			this.registerPatronage = false;

		} catch (Exception e) {
			Messages.throwFacesMessage(e);
		}
	}

	
	
	public void prepareDialog()
	{
		clearSearchPerson();
	}
	
	
	public List<QvDTO> searchQv(String query)
	{
		Integer regionId = null;
		Integer municipalityId = null;
		Integer unitId = null;
		
		if(req.getRegionId() != null)
		{
			regionId = req.getRegionId();
		}
		if(req.getMunicipalityId() != null)
		{
			municipalityId = req.getMunicipalityId();
		}
		if(req.getUnitId() != null)
		{
			unitId = req.getUnitId();
		}
				
		this.qvs = new HelperService().listQv(regionId, municipalityId, unitId, query);
		return qvs;
	}
	
	
	public void searchPerson()
	{
		try {
			if(this.selectedQv != null)
			{req.setQvId(selectedQv.getId());}
			List<PersonDTO> persons = new PersonService().searchPerson(req);

			if(persons != null && !persons.isEmpty())	
			{
				this.personForms = new ArrayList<>();
				for(PersonDTO p : persons)
				{
					PatronagePersonModelForm f = new PatronagePersonModelForm();
					f.setPerson(p);
					personForms.add(f);
				}
			}
			
		}catch(Exception e)
		{
			Messages.throwFacesMessage(e);
		}
	}
	
	public void clearSearchPerson()
	{
		this.req = new PersonSx();
		this.selectedQv = null;
		this.qvs = null;
		this.personForms = null;
		this.selectedPersonForms = new ArrayList<>();
	}

	public void addPersonsInPatronage()
	{
		try {
			PatronagePersonForm form = new PatronagePersonForm();
			form.setPatronage(this.selectedPatronage.getPerson());
			form.setPersonForms(this.selectedPersonForms);
			int nr = new PatronageService().registerPatronagePerson(form);
			Messages.throwFacesMessage(nr + " banore u shtuan nen patronazh",1);
		}catch(Exception e)
		{
			Messages.throwFacesMessage(e);
		}
	}
	
	public void addPersonsInPatronage(PatronagePersonModelForm pp)
	{
		try {
			PatronagePersonForm form = new PatronagePersonForm();
			form.setPatronage(this.selectedPatronage.getPerson());
			
			List<PersonDTO> persons = new ArrayList<>();
			if(pp.isWithFamily())
			{
				persons = new PersonService().getFamilyByNid(pp.getPerson().getNid());
			}
			else
			{
				persons.add(pp.getPerson());
			}
			
			List<PatronagePersonModelForm> list = new ArrayList<>();
			for(PersonDTO p : persons)
			{
				PatronagePersonModelForm f = new PatronagePersonModelForm();
				f.setPerson(p);
				f.setPhone(pp.getPhone());
				list.add(f);
			}
			
			form.setPersonForms(list);
			int nr = new PatronageService().registerPatronagePerson(form);
			Messages.throwFacesMessage(nr + " banore u shtuan nen patronazh",1);
			this.personsInPatronage = new PatronageService().getPatronagePersons(this.selectedPatronage.getId(), IPatronageType.PERSON);
		}catch(Exception e)
		{
			Messages.throwFacesMessage(e);
		}
	}
	
	public void goToMap()
	{
		List<Param> params = new ArrayList<>();
		params.add(new Param("nid_ptg", selectedPatronage.getPerson().getNid()));
		nav.navigate("ptg_map", params);
	}
	
	
	
	
}
