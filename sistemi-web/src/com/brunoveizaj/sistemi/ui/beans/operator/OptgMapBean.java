package com.brunoveizaj.sistemi.ui.beans.operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.constants.IMap;
import com.brunoveizaj.sistemi.ui.constants.IPatronageType;
import com.brunoveizaj.sistemi.ui.constants.IStatus;
import com.brunoveizaj.sistemi.ui.forms.PatronageForm;
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
import com.brunoveizaj.sistemi.ui.utils.MapUtil;
import com.brunoveizaj.sistemi.ui.utils.Messages;
import com.brunoveizaj.sistemi.ui.utils.StringUtil;

import lombok.Getter;
import lombok.Setter;


@ManagedBean
@ViewScoped
@Getter @Setter
public class OptgMapBean implements Serializable {
	
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
	
	MapModel mapModel;
	String centerMap;
	String zoomMap;
	
	
	Set<PersonPoint> selectedEntityPoints;
	QvMap qvMap;
	Set<BuildingMap> buildings;
	
	String entityCode;
	MapEntity selectedEntity;
	List<MapEntity> entities;	
	
	PatronageDTO selectedPatronage;
	PatronageForm patronageForm;
	Set<AddressDTO> buildingAddresses;
	List<PatronagePersonModelForm> personForms;
	
	boolean renderBack;
		
	
	
	
	@PostConstruct
	public void load()
	{
		this.centerMap = IMap.DEFAULT_CENTER;
		this.zoomMap = "15";
	}
	
	
	public void init()
	{
		String nid = nav.getParam("nid_ptg");
		if(StringUtil.isValid(nid))
		{
			renderBack = true;
		    PatronageDTO p = new PatronageService().findPatronageByNid(nid, IPatronageType.PERSON);
		
			this.patronageForm = new PatronageForm();
			patronageForm.setPatronageTypeId(IPatronageType.PERSON);

			
				if(p != null)
				{
					this.selectedPatronage = p;
					this.patronageForm.setPerson(selectedPatronage.getPerson());
					this.patronageForm.setPhone(selectedPatronage.getPerson().getPhone());
				}
				else
				{
					PersonDTO per = new PersonService().findPersonByNid(nid);
					this.selectedPatronage = new PatronageDTO();
					this.selectedPatronage.setPerson(per);
					this.patronageForm.setPerson(per);;
				}
				
				QvDTO qv = this.selectedPatronage.getPerson().getQv();
				
				qvMap = new MapService().getQvById(qv.getId());
				
				// Shtojme ndertesat e qv-se ne harte
				this.buildings = new HashSet<>();
				List<BuildingMap> buildingsMap = new MapService().getBuildingsByArea(qv.getId(), null);
				if(buildingsMap != null && !buildingsMap.isEmpty())
				{
					this.buildings.addAll(buildingsMap);
				}
				
				
				// Shtojme pikat ne harte
				this.selectedEntityPoints = new HashSet<>();
				List<PersonPoint> points = new MapService().getPersonPointByNid(nid);
				if(points != null && !points.isEmpty())
				{
					this.selectedEntityPoints.addAll(points);
				}
				
				// Shenojme qendren e hartes
				if(qvMap != null)
				{
					this.centerMap = new MapUtil().toMapCoord(qvMap.getCenter());
				}
				else if(selectedEntityPoints != null && !selectedEntityPoints.isEmpty())
				{
					for(PersonPoint pp : selectedEntityPoints) {
					 this.centerMap = new MapUtil().toMapCoord(pp.getPoint());
					 break;
					}
				}
				
		}	
		
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
		
		
		
		
		if(m.getType() == 1) // person
		{
			this.patronageForm = new PatronageForm();
			patronageForm.setPatronageTypeId(IPatronageType.PERSON);
			
			PatronageDTO p = new PatronageService().findPatronageByNid(m.getId(), IPatronageType.PERSON);
			if(p != null)
			{
				this.selectedPatronage = p;
				this.patronageForm.setPerson(selectedPatronage.getPerson());
				this.patronageForm.setPhone(selectedPatronage.getPerson().getPhone());
			}
			else
			{
				PersonDTO per = new PersonService().findPersonByNid(m.getId());
				this.selectedPatronage = new PatronageDTO();
				this.selectedPatronage.setPerson(per);
				this.patronageForm.setPerson(per);;
			}
			
			QvDTO qv = this.selectedPatronage.getPerson().getQv();
			
			qvMap = new MapService().getQvById(qv.getId());			
			
			this.buildings = new HashSet<>();
			List<BuildingMap> buildingsMap = new MapService().getBuildingsByArea(qv.getId(), null);
			if(buildingsMap != null && !buildingsMap.isEmpty())
			{
				this.buildings.addAll(buildingsMap);
			}
			
			this.selectedEntityPoints = new HashSet<>();
			List<PersonPoint> points = new MapService().getPersonPointByNid(m.getId());
			if(points != null && !points.isEmpty())
			{
				this.selectedEntityPoints.addAll(points);
			}
			
			if(this.qvMap != null)
			{
				this.centerMap = new MapUtil().toMapCoord(qvMap.getCenter());
			}
			
			else if(selectedEntityPoints != null && !selectedEntityPoints.isEmpty())
			{
				for(PersonPoint pp : selectedEntityPoints) {
				 this.centerMap = new MapUtil().toMapCoord(pp.getPoint());
				 break;
				}
			}
			
		}
		
		if(m.getType() == 2) // qv
		{

	
			this.selectedEntityPoints = null;
			
			qvMap = new MapService().getQvById(Integer.valueOf(m.getId()));

			this.buildings = new HashSet<>();
			List<BuildingMap> maps = new MapService().getBuildingsByArea(Integer.valueOf(m.getId()), null);						
			if(maps != null && !maps.isEmpty())
			{
			   this.buildings.addAll(maps);
			}
			
			this.centerMap = new MapUtil().toMapCoord(qvMap.getCenter());
			if(!StringUtil.isValid(centerMap))
			{
				this.centerMap = IMap.DEFAULT_CENTER;
			}
			
		}
		
		
		loadMap();
		
	}
	
	public void onOverlaySelect()
	{
		
	}
	
	public void loadMap()
	{
				
		if(this.selectedPatronage == null || this.selectedPatronage.getId() <= 0)
		{
			Messages.throwFacesMessage("Patronazhisti i papercaktuar",2);
		}
		
		this.mapModel = new DefaultMapModel();
        Polygon polygon = new Polygon();
        mapModel.addOverlay(polygon);
		
		if(selectedEntityPoints != null && !selectedEntityPoints.isEmpty())
		{
			for(PersonPoint pm : selectedEntityPoints)
			{
				Marker m = new Marker(new MapUtil().toLatLng(pm.getPoint()), pm.getFullName()+" Qv: "+pm.getQvCode()+"/"+pm.getFraction(), pm.getNid());
				mapModel.addOverlay(m);
			}
		}
			

		if(this.qvMap != null)
		{
		        polygon = new MapUtil().toPolygon(qvMap.getShape());
				if(polygon != null)
				{
					
					polygon.setId(String.valueOf(qvMap.getQvId()));
				//	polygon.setData(qvMap.getQvId());
					polygon.setFillColor(IMap.QV_FILL_COLOR);
					polygon.setFillOpacity(IMap.QV_FILL_OPACITY);
					polygon.setStrokeColor(IMap.QV_STROKE_COLOR);
					polygon.setStrokeOpacity(IMap.QV_STROKE_OPACITY);
					polygon.setStrokeWeight(IMap.QV_STROKE_WHEIGHT);
								          
			        mapModel.addOverlay(polygon);
				}
				
		}	
		
		
		
		if(this.buildings != null && !buildings.isEmpty())
		{
			for(BuildingMap bm : buildings)
			{
				polygon = new MapUtil().toPolygon(bm.getShape());
				
				if(polygon != null)
				{
					polygon.setData(bm.getBuildingId());
					if(bm.getHasData() == null || bm.getHasData() != IStatus.ACTIVE)
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
				
	}
	
	
	public void registerPatronage()
	{
		try {
			
			this.selectedPatronage = new PatronageService().registerPatronage(patronageForm);
			Messages.throwFacesMessage("Patronazhisti u regjistrua me sukses!", 1);
			
		}catch(Exception e)
		{
			Messages.throwFacesMessage(e);
		}
	}
	
	
	public String generateMessage()
	{
		if(selectedPatronage == null)
		{
			return "<span>Ju duhet te zgjidhni me pare patronazhistin duke e kerkuar me emer mbiemer ose</span> <br/>" + 
					"<span>te kerkoni qv-ne, te klikoni mbi ndertesen dhe me pas mbi emrin e patronazhistit </span>";
		}
		
		else if(this.selectedPatronage.getId() == 0)
		{
			return "<span>Ju duhet te regjistroni patronazhistin e zgjedhur,</span> <br/>" + 
					"<span>ose te kerkoni per patronazhist tjeter</span>";
		}
		
		else if(this.selectedPatronage.getId() > 0)
		{
			return "<span>Kerkoni per banore me emer mbiemer ose</span> <br/>" + 
					"<span>kerkoni qv-ne, te klikoni mbi ndertesen dhe me pas mbi emrin e banorit qe doni ta shtoni nen patronazh </span>";
		}
		
		return null;
		
	}
	
	

}
