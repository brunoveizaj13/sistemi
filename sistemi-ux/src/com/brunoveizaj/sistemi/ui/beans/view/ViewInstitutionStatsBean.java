package com.brunoveizaj.sistemi.ui.beans.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.models.KeyValue;
import com.brunoveizaj.sistemi.ui.services.QvStatsService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class ViewInstitutionStatsBean implements Serializable {
	
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	

	Integer regionId;
	Integer municipId;
	
	int totalPtg;
	int totalInPtg;
	
	List<KeyValue> patronages;
	List<KeyValue> inPatronages;
	
	
	@PostConstruct
	public void load()
	{
		onRegionSelect();
	}
	
	public void onRegionSelect()
	{
		this.municipId = null;
		loadRegionStats();
		grandTotal();
	}
	
	
	public void onMunicipalitySelect()
	{
		
		loadMunicipStats();
		grandTotal();
	}
	
	public void loadRegionStats()
	{
		this.patronages = new QvStatsService().getInstitutionPatronages(regionId, null, null, null);
		this.inPatronages = new QvStatsService().getInstitutionInPatronages(regionId, null, null, null);
	}
	
	public void loadMunicipStats()
	{
		this.patronages = new QvStatsService().getInstitutionPatronages(null, municipId, null, null);
		this.inPatronages = new QvStatsService().getInstitutionInPatronages(null, municipId, null, null);
	}
	
	public void grandTotal()
	{
		this.totalInPtg = 0;
		this.totalPtg = 0;
		if(patronages != null && !patronages.isEmpty())
		{
			for(KeyValue k : patronages)
			{
				totalPtg += (Integer)k.getValue();
			}
		}
		if(inPatronages != null && !inPatronages.isEmpty())
		{
			for(KeyValue k : inPatronages)
			{
				totalInPtg += (Integer)k.getValue();
			}
		}
	}
	

}
