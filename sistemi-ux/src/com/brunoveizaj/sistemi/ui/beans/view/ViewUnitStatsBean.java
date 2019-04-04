package com.brunoveizaj.sistemi.ui.beans.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.models.QvStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.UnitStatisticDTO;
import com.brunoveizaj.sistemi.ui.services.QvStatsService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class ViewUnitStatsBean implements Serializable {
	
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	

	Integer regionId;
	Integer municipId;
	UnitStatisticDTO selectedUnit;
	
	int qvs;
	int persons;
	int voters;
	int firstTime;
	int noVoting;
	int members;
	int patronages;
	int inPatronages;
	int noPatronages;
	int pois;
	int instPatronages;
	int inInstPatronages;
	
	

	List<UnitStatisticDTO> unitStats;
	List<QvStatisticDTO> qvStats;

	
	
	
	@PostConstruct
	public void load()
	{
		onRegionSelect();
	}
	
	public void onRegionSelect()
	{
		this.municipId = null;
		this.selectedUnit = null;
		this.qvStats = null;
		loadRegionsStats();
		grandTotal();
	}
	
	
	public void onMunicipalitySelect()
	{
		this.selectedUnit = null;
		this.qvStats = null;
		loadMunicipStats();
		grandTotal();
	}
	
	public void onUnitSelect()
	{
		loadUnitQvs();
	}
	
	public void loadRegionsStats()
	{
		this.unitStats = new QvStatsService().getUnitStatisticsByMunicipality(regionId, null);
	}
	
	public void loadMunicipStats()
	{
		this.unitStats = new QvStatsService().getUnitStatisticsByMunicipality(null, municipId);
	}
	
	
	public void grandTotal()
	{
		this.qvs = 0;
		this.persons = 0;
		this.voters = 0;
		this.firstTime = 0;
		this.noVoting = 0;
		this.pois = 0;
		this.patronages = 0;
		this.inPatronages = 0;
		
		this.instPatronages = 0;
		this.inInstPatronages = 0;
		
		
		if(this.unitStats != null && !this.unitStats.isEmpty())
		{
			for(UnitStatisticDTO s : unitStats)
			{
				qvs += s.getQvs();
				persons += s.getPersons();
				voters += s.getVoters();
				firstTime += s.getFirstTime();
				noVoting += s.getNoVoting();
				pois += s.getPois();
				patronages += s.getPatronages();
				inPatronages += s.getInPatronages();
				instPatronages += s.getInstPatronages();
				inInstPatronages += s.getInInstPatronages();
			}
		}
		
		
		this.noPatronages = (voters - inPatronages);
		
	}
	
	public void loadUnitQvs()
	{
		this.qvStats = new QvStatsService().getQvStatisticsByUnit(selectedUnit.getId());
	}
	
	
	
	
	


}
