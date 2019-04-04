package com.brunoveizaj.sistemi.ui.beans.view;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.models.MunicipalityDTO;
import com.brunoveizaj.sistemi.ui.models.MunicipalityStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.Param;
import com.brunoveizaj.sistemi.ui.services.QvStatsService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class ViewMunicipalitiesBean implements Serializable {

	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	String view;
	
	Integer regionId;
	
	MunicipalityDTO municip;
	MunicipalityStatisticDTO stats;
	
	@PostConstruct
	public void load()
	{
		this.regionId = null;
		goToList();
	}
	
	
	public void goToList()
	{
		this.view = "municipalities_list.xhtml";
	}
	
	public void goToPartyStructure()
	{
		List<Param> params = new ArrayList<>();
		params.add(new Param("municip_id", String.valueOf(municip.getId())));
		nav.navigate("municipality_party_structure",params);
	}
	
	public void onMunicipalitySelect(MunicipalityDTO m)
	{
		this.municip = m;
		this.view = "municipality_view.xhtml";
		this.stats = new QvStatsService().getMunicipalityStatistic(m.getId());
	}
	
	
	
	public void goToMunicipalitySubjects()
	{
		List<Param> params = new ArrayList<>();
		params.add(new Param("municip_id", String.valueOf(municip.getId())));		
		params.add(new Param("administrate", String.valueOf(false)));
		nav.navigate("subject_sx",params);
	}
	
	public void goToMunicipalityAdministrate()
	{
		List<Param> params = new ArrayList<>();
		params.add(new Param("municip_id", String.valueOf(municip.getId())));		
		params.add(new Param("administrate", String.valueOf(true)));
		nav.navigate("subject_sx",params);
	}
	
	public void goToQvStats()
	{
		List<Param> params = new ArrayList<>();
		params.add(new Param("municip_id", String.valueOf(municip.getId())));
		nav.navigate("qv_stats",params);
	}
	
	public void goToDashboard()
	{
		List<Param> params = new ArrayList<>();
		params.add(new Param("municip_id", String.valueOf(municip.getId())));
		nav.navigate("dashboard",params);
	}
	

}
