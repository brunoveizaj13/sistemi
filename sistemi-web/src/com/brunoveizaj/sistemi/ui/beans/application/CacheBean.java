package com.brunoveizaj.sistemi.ui.beans.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.brunoveizaj.sistemi.ui.models.MonthYear;
import com.brunoveizaj.sistemi.ui.models.MunicipalityDTO;
import com.brunoveizaj.sistemi.ui.models.PartyDTO;
import com.brunoveizaj.sistemi.ui.models.RegionDTO;
import com.brunoveizaj.sistemi.ui.models.UnitDTO;
import com.brunoveizaj.sistemi.ui.services.HelperService;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;


@ManagedBean
@ApplicationScoped
@Getter @Setter
public class CacheBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	List<RegionDTO> regions;
	List<MunicipalityDTO> municipalities;
	List<UnitDTO> units;
	

	List<MonthYear> monthYears;
	List<PartyDTO> parties;
	
	@PostConstruct
	public void load()
	{
		this.regions = new HelperService().listRegion();
		this.municipalities = new HelperService().listMunicipality(null, null);
		this.units = new HelperService().listUnit(null, null, null);
		this.parties = new HelperService().listParty();
		this.monthYears = new HelperService().listpayrollMonthYears();
		
	}
	
	
	
	public List<MunicipalityDTO> loadMunicipalities(Integer regionId, boolean fill)
	{
		if(regionId == null )
		{
			return fill?this.municipalities:null;
		}
		
		List<MunicipalityDTO> list = new ArrayList<>();
		for(MunicipalityDTO m : municipalities)
		{
			if(m.getRegion().getId() == regionId)
			{
				list.add(m);
			}
		}
		
		return list;
		
	}
		
	public List<UnitDTO> loadUnits(Integer municipId, boolean fill)
	{
		if(municipId == null )
		{
			return fill?this.units:null;
		}
		
		List<UnitDTO> list = new ArrayList<>();
		for(UnitDTO m : units)
		{
			if(m.getMunicipality().getId() == municipId)
			{
				list.add(m);
			}
		}
		
		return list;
		
	}
	

}	
