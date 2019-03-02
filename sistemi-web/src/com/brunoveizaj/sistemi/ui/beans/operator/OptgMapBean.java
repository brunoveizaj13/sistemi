package com.brunoveizaj.sistemi.ui.beans.operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;

import com.brunoveizaj.sistemi.ui.constants.IPatronageType;
import com.brunoveizaj.sistemi.ui.forms.PatronagePersonModelForm;
import com.brunoveizaj.sistemi.ui.forms.PersonSx;
import com.brunoveizaj.sistemi.ui.models.AddressDTO;
import com.brunoveizaj.sistemi.ui.models.MapEntity;
import com.brunoveizaj.sistemi.ui.models.PatronageDTO;
import com.brunoveizaj.sistemi.ui.models.PersonDTO;
import com.brunoveizaj.sistemi.ui.models.QvDTO;
import com.brunoveizaj.sistemi.ui.models.map.BuildingMap;
import com.brunoveizaj.sistemi.ui.models.map.PersonPoint;
import com.brunoveizaj.sistemi.ui.models.map.QvMap;
import com.brunoveizaj.sistemi.ui.services.HelperService;
import com.brunoveizaj.sistemi.ui.services.MapService;
import com.brunoveizaj.sistemi.ui.services.PatronageService;
import com.brunoveizaj.sistemi.ui.services.PersonService;

import lombok.Getter;
import lombok.Setter;


@ManagedBean
@ViewScoped
@Getter @Setter
public class OptgMapBean implements Serializable {
	
	
	MapModel mapModel;
	String centerMap;
	String zoomMap;
	
	
	Set<PersonPoint> selectedEntityPoints;
	Set<QvMap> qvs;
	Set<BuildingMap> buildings;
	
	String entityCode;
	MapEntity selectedEntity;
	List<MapEntity> entities;	
	
	PatronageDTO selectedPatronage;
	Set<AddressDTO> buildingAddresses;
	List<PatronagePersonModelForm> personForms;
	
	
	
	
	
	
	
	
	
	@PostConstruct
	public void load()
	{
		this.centerMap = "41.328861, 19.818140";
		this.zoomMap = "17";
		loadMap();
	}
	
	
	public List<MapEntity> searchEntity(String query)
	{
		List<MapEntity> list = new ArrayList<>();
		if(query != null && query.length() > 0)
		{
			
		    if(Character.isDigit(query.charAt(0)))
		    {
		    	List<QvDTO> qvs = new HelperService().listQv(null, null, null, query);
		    	if(qvs != null && !qvs.isEmpty())
		    	{
		    		for(QvDTO q : qvs)
		    		{
		    			list.add(new MapEntity(q));
		    		}
		    	}
		    }
		    
		    else if (query.length() > 3 && query.contains(" ") && (query.length() > (query.indexOf(" ")+1)) && Character.isLetter(query.charAt(query.indexOf(" ")+1)))
		    {
		    	String[] names = query.split(" ",-1);
		    	
		    	String name = null;
		    	String fatherName = null;
		    	String surname = null;
		    	
		    	if(names != null && names.length > 1)
		    	{
		    		name = names[0];
		    		if(names.length == 2)
		    		{
		    			surname = names[1];
		    			
		    		}
		    		else
		    		{
		    			fatherName = names[1];
		    			surname = names[2];
		    		}
		    	}
		    	
		    	PersonSx sx = new PersonSx();
		    	sx.setName(name+"%");
		    	sx.setSurname(surname+"%");
		    	sx.setFatherName(fatherName==null?null:(fatherName+"%"));
		    	List<PersonDTO> pers = new PersonService().searchPerson(sx);		    	
		    	
		    	if(pers != null && !pers.isEmpty())
		    	{
		    		for(PersonDTO p : pers)
		    		{
		    			System.out.println(p.getNid());
		    			list.add(new MapEntity(p));
		    		}
		    	}
		    	
		    }
		    
		    else if(query.length() > 7 && Character.isLetter(query.indexOf(0)) && Character.isDigit(query.indexOf(1)))
		    {
		    	PersonSx sx = new PersonSx();
		    	sx.setNid(query+"%");
		    	List<PersonDTO> pers = new PersonService().searchPerson(sx);
		    	
		    	if(pers != null && !pers.isEmpty())
		    	{
		    		for(PersonDTO p : pers)
		    		{
		    			list.add(new MapEntity(p));
		    		}
		    	}
		    }
		    
		}
		
		this.entities = list;
		return list;
	}
	
	public void onEntitySelect()
	{
		MapEntity m = selectedEntity;
		/*
		MapEntity m = null;
		
		if(entities != null && !entities.isEmpty())
		{
			for(MapEntity me : entities)
			{
				if(me.getId().equals(this.entityCode))
				{
					m = me;
					break;
				}
			}
		}
		*/
		if(m.getType() == 1) // person
		{
			PatronageDTO p = new PatronageService().findPatronageByNid(m.getId(), IPatronageType.PERSON);
			if(p != null)
			{
				this.selectedPatronage = p;
			}
			else
			{
				PersonDTO per = new PersonService().findPersonByNid(m.getId());
				this.selectedPatronage = new PatronageDTO();
				this.selectedPatronage.setPerson(per);
			}
			
			QvDTO qv = this.selectedPatronage.getPerson().getQv();
			
			if(this.qvs == null) this.qvs = new HashSet<>();			
			QvMap qvMap = new MapService().getQvById(qv.getId());
			if(qvMap != null)
			{
				this.qvs.add(qvMap);
			}
			
			if(this.buildings == null) this.buildings = new HashSet<>();
			List<BuildingMap> buildingsMap = new MapService().getBuildingsByArea(qv.getId(), null);
			if(buildingsMap != null && !buildingsMap.isEmpty())
			{
				this.buildings.addAll(buildingsMap);
			}
			
			if(this.selectedEntityPoints == null) this.selectedEntityPoints = new HashSet<>();
			List<PersonPoint> points = new MapService().getPersonPointByNid(m.getId());
			if(points != null && !points.isEmpty())
			{
				this.selectedEntityPoints.addAll(points);
			}
			if(selectedEntityPoints != null && !selectedEntityPoints.isEmpty())
			{
				for(PersonPoint pp : selectedEntityPoints) {
				 this.centerMap = pp.getPoint();
				 break;
				}
			}
			
		}
		
		if(m.getType() == 2) // qv
		{
			QvMap qv = new MapService().getQvById(Integer.valueOf(m.getId()));
			if(this.qvs == null) this.qvs = new HashSet<>();			
			this.qvs.add(qv);
			if(this.buildings == null) this.buildings = new HashSet<>();
			List<BuildingMap> maps = new MapService().getBuildingsByArea(Integer.valueOf(m.getId()), null);
			if(maps != null && !maps.isEmpty())
			{
			   this.buildings.addAll(maps);
			}
			this.centerMap = qv.getCenter();
			
		}
		
		
		loadMap();
		
	}
	
	public void onOverlaySelect()
	{
		
	}
	
	private void loadMap()
	{
		this.mapModel = new DefaultMapModel();
		
	}
	
	
	
	
	

}
