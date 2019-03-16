package com.brunoveizaj.sistemi.ui.beans.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.constants.IMap;
import com.brunoveizaj.sistemi.ui.constants.IPatronageType;
import com.brunoveizaj.sistemi.ui.constants.IStatus;
import com.brunoveizaj.sistemi.ui.forms.PersonSx;
import com.brunoveizaj.sistemi.ui.models.AddressDTO;
import com.brunoveizaj.sistemi.ui.models.MapEntity;
import com.brunoveizaj.sistemi.ui.models.PersonDTO;
import com.brunoveizaj.sistemi.ui.models.QvDTO;
import com.brunoveizaj.sistemi.ui.models.map.BuildingMap;
import com.brunoveizaj.sistemi.ui.models.map.PatronagePoint;
import com.brunoveizaj.sistemi.ui.models.map.PersonPoint;
import com.brunoveizaj.sistemi.ui.models.map.PoiPoint;
import com.brunoveizaj.sistemi.ui.models.map.QvMap;
import com.brunoveizaj.sistemi.ui.models.map.UnitMap;
import com.brunoveizaj.sistemi.ui.services.AddressService;
import com.brunoveizaj.sistemi.ui.services.HelperService;
import com.brunoveizaj.sistemi.ui.services.MapService;
import com.brunoveizaj.sistemi.ui.services.PersonService;
import com.brunoveizaj.sistemi.ui.utils.MapUtil;
import com.brunoveizaj.sistemi.ui.utils.Messages;
import com.brunoveizaj.sistemi.ui.utils.StringUtil;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class ViewMapBean implements Serializable {

	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	boolean renderBack = false;
	
	
	MapModel mapModel;
	String centerMap;
	String zoomMap;
	
	String entityCode;
	MapEntity selectedEntity;
	List<MapEntity> entities;
	
	
	Set<PersonPoint> personPoints;
	Set<PersonPoint> memberPoints;
	Set<PersonPoint> firstTimePoints;
	Set<PatronagePoint> patronagePoints;
	Set<PoiPoint> poiPoints;
	Set<QvMap> qvMapList;
	UnitMap unitMap;
	Set<BuildingMap> buildings;
	
	//boolean loadUnit;
	/*boolean loadUnitQvs;
	boolean loadPatronages;
	boolean loadPois;
	boolean loadMembers;
	boolean loadFirstTime;
	*/
	QvDTO selectedQv;
	List<AddressDTO> buildingAddresses;
	
	
	@PostConstruct
	public void load()
	{
		this.centerMap = IMap.DEFAULT_CENTER;
		this.zoomMap = "16";
	}
	
	
	public void init()
	{
		String nid = nav.getParam("nid");
		if(StringUtil.isValid(nid))
		{
			renderBack = true;
			loadPerson(nid);
			loadMap();
			return;
		}
		String qv_id = nav.getParam("qv_id");
		if(StringUtil.isValid(qv_id))
		{
			renderBack = true;
			this.selectedQv = new HelperService().findQv(Integer.valueOf(qv_id));
			loadQv(this.selectedQv.getId(), null);
			loadMap();
		}
		
		
	}
	
	
	public void loadQv(Integer qvId, Integer unitId)
	{
		this.qvMapList = new HashSet<>();
		
		if(qvId != null)
		{
			QvMap qm = new MapService().getQvById(qvId);
			if(qm != null && StringUtil.isValid(qm.getCenter()))
			{
				this.centerMap = new MapUtil().toMapCoord(qm.getCenter());
				this.qvMapList.add(qm);
			}
		}
		else if(unitId != null)
		{
			List<QvMap> list = new MapService().getQvsByUnitId(unitId);
			if(list != null && !list.isEmpty())
			{
				this.qvMapList.addAll(list);
			}
			
		}
		this.buildings = new HashSet<>();
		List<BuildingMap> buildingsMap = new MapService().getBuildingsByArea(qvId, unitId);
		if(buildingsMap != null && !buildingsMap.isEmpty())
		{
			this.buildings.addAll(buildingsMap);
		}		
		
		this.patronagePoints = new HashSet<>();
		List<PatronagePoint> patronages = new MapService().getPatronagesPointByArea(qvId, unitId, IPatronageType.PERSON);
		if(patronages != null && !patronages.isEmpty())
		{
			this.patronagePoints.addAll(patronages);
		}
		
		this.poiPoints = new HashSet<>();
		List<PoiPoint> pois = new MapService().getPoisPointByArea(qvId, unitId);
		if(pois != null && !pois.isEmpty())
		{
			this.poiPoints.addAll(pois);
		}
		
		this.memberPoints = new HashSet<>();
		List<PersonPoint> members = new MapService().getMemberPointsByArea(qvId, unitId);
		if(members != null && !members.isEmpty())
		{
			this.memberPoints.addAll(members);
		}
		
		this.firstTimePoints = new HashSet<>();
		List<PersonPoint> first = new MapService().getFirstTimeVoterPointsByArea(qvId, unitId);
		if(first != null && !first.isEmpty())
		{
			this.firstTimePoints.addAll(first);
		}
		
	}
	
	public void loadPerson(String nid)
	{
		this.personPoints = new HashSet<>();
		List<PersonPoint> points = new MapService().getPersonPointByNid(nid);
		if(points != null && !points.isEmpty())
		{
			this.personPoints.addAll(points);
		}
		
		PersonDTO p = new PersonService().findPersonByNid(nid);
		if(p != null)
		{
			this.selectedQv = p.getQv();
			
			loadQv(selectedQv.getId(), null);
		}
		
	}
	
	public void loadUnit()
	{
		loadQv(null, this.selectedQv.getUnit().getId());
		
		loadMap();
	}
		
	public void loadMap()
	{
		
		this.mapModel = new DefaultMapModel();
        Polygon polygon = new Polygon();
        mapModel.addOverlay(polygon);
		
		if(personPoints != null && !personPoints.isEmpty())
		{
			for(PersonPoint pm : personPoints)
			{
				if(StringUtil.isValid(pm.getPoint()) && pm.getPoint().length()>5) 
				{
					Marker m = new Marker(new MapUtil().toLatLng(pm.getPoint()), pm.getFullName()+" Qv: "+pm.getQvCode()+"/"+pm.getFraction());
					m.setData(new MapEntity(pm.getNid(),1));
					m.setIcon("http://maps.google.com/mapfiles/ms/micons/green-dot.png");
					mapModel.addOverlay(m);
				}
			}
		}
			

		if(qvMapList != null && !qvMapList.isEmpty())
		{
			for(QvMap qvMap : qvMapList) 
			{
		        polygon = new MapUtil().toPolygon(qvMap.getShape());
				if(polygon != null)
				{
					
					polygon.setId(String.valueOf(qvMap.getQvId()));
					polygon.setData(new MapEntity(String.valueOf(qvMap.getQvId()),2));
					polygon.setFillOpacity(IMap.QV_FILL_OPACITY);
					polygon.setStrokeColor(IMap.QV_STROKE_COLOR);
					polygon.setStrokeOpacity(IMap.QV_STROKE_OPACITY);
					polygon.setStrokeWeight(IMap.QV_STROKE_WHEIGHT);
								          
			        mapModel.addOverlay(polygon);
				}
			}
		}	
		
		
		
		if(this.buildings != null && !buildings.isEmpty())
		{
			for(BuildingMap bm : buildings)
			{
				polygon = new MapUtil().toPolygon(bm.getShape());
				
				if(polygon != null)
				{
					polygon.setId(String.valueOf(bm.getBuildingId()));
					polygon.setData(new MapEntity(String.valueOf(bm.getBuildingId()),4));
					if(bm.getHasData() == null || (bm.getHasData() != IStatus.ACTIVE))
					{
						polygon.setFillColor(IMap.BUILDING_FILL_COLOR);
						polygon.setStrokeColor(IMap.BUILDING_STROKE_COLOR);
					}
					else
					{
						polygon.setFillColor(IMap.BUILDING_HAS_DATA_FILL_COLOR);
						polygon.setStrokeColor(IMap.BUILDING_HAS_DATA_STROKE_COLOR);
					}
					polygon.setFillOpacity(IMap.BUILDING_FILL_OPACITY);
					
					polygon.setStrokeOpacity(IMap.BUILDING_STROKE_OPACITY);
					polygon.setStrokeWeight(IMap.BUILDING_STROKE_WHEIGHT);
					
					mapModel.addOverlay(polygon);
					
					//add building_no as marker by building center
				}
			}
		}
		
		
		if(patronagePoints != null && !patronagePoints.isEmpty())
		{
			for(PatronagePoint pm : patronagePoints)
			{
				if(StringUtil.isValid(pm.getPoint()) && pm.getPoint().length()>5) 
				{
					Marker m = new Marker(new MapUtil().toLatLng(pm.getPoint()),"PATRONAZHIST: "+ pm.getFullName());
					m.setData(new MapEntity(pm.getNid(),1));
					m.setIcon("http://maps.google.com/mapfiles/ms/micons/yellow-dot.png");
					mapModel.addOverlay(m);
				}
			}
		}
		
		if(poiPoints != null && !poiPoints.isEmpty())
		{
			for(PoiPoint pm : poiPoints)
			{
				if(StringUtil.isValid(pm.getPoint()) && pm.getPoint().length()>5) 
				{
					Marker m = new Marker(new MapUtil().toLatLng(pm.getPoint()),"Person me Influence: " + pm.getFullName());
					m.setData(new MapEntity(pm.getNid(),1));
					m.setIcon("http://maps.google.com/mapfiles/ms/micons/red-dot.png");
					mapModel.addOverlay(m);
				}
			}
		}
		
		
		
		if(memberPoints != null && !memberPoints.isEmpty())
		{
			for(PersonPoint pm : memberPoints)
			{
				if(StringUtil.isValid(pm.getPoint()) && pm.getPoint().length()>5) 
				{
					Marker m = new Marker(new MapUtil().toLatLng(pm.getPoint()),"ANETAR: " + pm.getFullName()+" Qv: "+pm.getQvCode()+"/"+pm.getFraction());
					m.setData(new MapEntity(pm.getNid(),1));
					m.setIcon("http://maps.google.com/mapfiles/ms/micons/purple-dot.png");
					mapModel.addOverlay(m);
				}
			}
		}
		
		if(firstTimePoints != null && !firstTimePoints.isEmpty())
		{
			for(PersonPoint pm : firstTimePoints)
			{
				if(StringUtil.isValid(pm.getPoint()) && pm.getPoint().length()>5) 
				{
					Marker m = new Marker(new MapUtil().toLatLng(pm.getPoint()),"VOTUES H.P: " + pm.getFullName()+" Qv: "+pm.getQvCode()+"/"+pm.getFraction());
					m.setData(new MapEntity(pm.getNid(),1));
					m.setIcon("http://maps.google.com/mapfiles/ms/micons/orange-dot.png");
					mapModel.addOverlay(m);
				}
			}
		}
		
		
	}
	
	public List<MapEntity> searchEntity(String query)
	{
		List<MapEntity> list = new ArrayList<>();
		if(query != null && query.length() > 0)
		{
			// Kerkim per qv
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
		    // Kerkim per person
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
		    			list.add(new MapEntity(p));
		    		}
		    	}
		    	
		    }
		    // Kerkim me NID
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
		if(m.getType() == 1) // person
		{
				PersonDTO per = new PersonService().findPersonByNid(m.getId());
				loadPerson(per.getNid());
				if(personPoints != null && !personPoints.isEmpty())
				{
					for(PersonPoint pp : personPoints) {		
						 this.centerMap = new MapUtil().toMapCoord(pp.getPoint());
						 break;
					}
				}
		}	
			
								
		if(m.getType() == 2) // qv
		{
			loadQv(Integer.valueOf(m.getId()), null);
			
			QvMap qvMap = new MapService().getQvById(Integer.valueOf(m.getId()));
			
			this.centerMap = new MapUtil().toMapCoord(qvMap.getCenter());
		}
		
		if(centerMap == null || centerMap.length() < 5)
		{
			this.centerMap = IMap.DEFAULT_CENTER;
			Messages.throwFacesMessage("Eniteti nuk ka te dhena ne harte", 2);
		}
		
		loadMap();
		
	}

	
public void onOverlaySelect(OverlaySelectEvent event) {
		
		try {
           Marker m = (Marker) event.getOverlay();
           onMarkerSelect(m);
           return;
		}catch(Exception e){}
		
		try {
	        Polygon p = (Polygon) event.getOverlay();
	        onPolygonSelect(p);
	        return;
		}catch(Exception e){}
		
				
	}
	
	
	
	public void onMarkerSelect(Marker m)
	{
		if(m == null) Messages.throwFacesMessage("Pika e zgjedhur nuk ka te dhena", 3);
		return;
		
		
		
	}
	
	
	
	public void onPolygonSelect(Polygon p)
	{
		if(p == null) Messages.throwFacesMessage("Zona e zgjedhur nuk ka te dhena", 3);
		
		MapEntity e = (MapEntity) p.getData();
		
		if(e.getType() == 4) // building select
		{
			String buildingId = e.getId();
			
			
			this.buildingAddresses = new AddressService().getBuildingAddresses(Integer.valueOf(buildingId));
			
			if(buildingAddresses != null && !buildingAddresses.isEmpty())
			{
				PrimeFaces current = PrimeFaces.current();		
				current.executeScript("PF('addWid').show();");
				current.ajax().update("add_dlg");
			}
			else
			{
				Messages.throwFacesMessage("Nuk ka banore te regjistruar ne kete ndertese",2);
			}
			
		}
		
	}
	
	
	
	

}
