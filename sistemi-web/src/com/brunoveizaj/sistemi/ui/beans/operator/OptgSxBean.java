package com.brunoveizaj.sistemi.ui.beans.operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.forms.PersonSx;
import com.brunoveizaj.sistemi.ui.models.MunicipalityDTO;
import com.brunoveizaj.sistemi.ui.models.Param;
import com.brunoveizaj.sistemi.ui.models.PersonDTO;
import com.brunoveizaj.sistemi.ui.models.QvDTO;
import com.brunoveizaj.sistemi.ui.models.RegionDTO;
import com.brunoveizaj.sistemi.ui.models.UnitDTO;
import com.brunoveizaj.sistemi.ui.services.HelperService;
import com.brunoveizaj.sistemi.ui.services.PersonService;
import com.brunoveizaj.sistemi.ui.utils.Messages;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OptgSxBean implements Serializable {
	
	
	
	@ManagedProperty(value="#{navBean}")
	NavBean nav;
	
	boolean renderFilter;
	
	PersonSx req;
	
	List<PersonDTO> persons;
	PersonDTO selectedPerson;
	
	List<QvDTO> qvs;
	List<RegionDTO> regions;
	List<MunicipalityDTO> municipalities;
	List<UnitDTO> units;
	
	@PostConstruct
	public void load()
	{
		renderFilter();
		this.regions = new HelperService().listRegion();
		this.municipalities = new HelperService().listMunicipality(null, null);
		this.units = new HelperService().listUnit(null, null, null);
		init();
	}
	
	public void init()
	{
		this.req = new PersonSx();
		this.selectedPerson = null;
		this.persons = null;
		
		this.qvs = null;
		
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
		
		System.out.println(regionId+" "+municipalityId+" "+unitId+" "+query);
		
		this.qvs = new HelperService().listQv(regionId, municipalityId, unitId, query+"%");
		return qvs;
	}
	
	
	public void search()
	{
		try {
			
			this.persons = new PersonService().searchPerson(req);
			this.selectedPerson = null;
			
			renderList();
			
		}catch(Exception e)
		{
			Messages.throwFacesMessage(e);
		}
	}
	
	public void clear()
	{
		init();
		renderFilter();
	}

	public void onPersonSelect()
	{
		List<Param> params = new ArrayList<>();
		params.add(new Param("nid", selectedPerson.getNid()));
		nav.navigate("ptg_view_form",params);
	}
	
	public void renderFilter()
	{
		this.renderFilter = true;
		this.selectedPerson = null;
		this.persons = null;
	}
	
	public void renderList()
	{
		this.renderFilter = false;
	}
	
	
}
