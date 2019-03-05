package com.brunoveizaj.sistemi.ui.beans.application;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.brunoveizaj.sistemi.ui.models.MunicipalityDTO;
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
	
	
	@PostConstruct
	public void load()
	{
		this.regions = new HelperService().listRegion();
		this.municipalities = new HelperService().listMunicipality(null, null);
		this.units = new HelperService().listUnit(null, null, null);
		
	}
	
	
	
	
	

}	
