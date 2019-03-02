package com.brunoveizaj.sistemi.ui.services;

import java.util.List;

import com.brunoveizaj.sistemi.ui.api.clients.MapClient;
import com.brunoveizaj.sistemi.ui.models.map.BuildingMap;
import com.brunoveizaj.sistemi.ui.models.map.PatronagePoint;
import com.brunoveizaj.sistemi.ui.models.map.PersonPoint;
import com.brunoveizaj.sistemi.ui.models.map.PoiPoint;
import com.brunoveizaj.sistemi.ui.models.map.QvMap;
import com.brunoveizaj.sistemi.ui.models.map.UnitMap;



public class MapService {

	
	public List<PersonPoint> getPersonPointByNid(String nid)
	{
		return new MapClient().getPersonPointByNid(nid);
	}
	
	public List<PatronagePoint> getPatronagesPointByArea(Integer qvId, Integer unitId, Integer patronageTypeId)
	{
		return new MapClient().getPatronagesPointByArea(qvId, unitId, patronageTypeId);
	}
	
	public List<PersonPoint> getInPatronagePointsByPatronageNid(Integer patronageNid, Integer patronageTypeId)
	{
		return new MapClient().getInPatronagePointsByPatronageNid(patronageNid, patronageTypeId);
	}
	
	public List<PersonPoint> getInPatronagePointsByArea(Integer qvId, Integer unitId, Integer patronageTypeId)
	{
		return new MapClient().getInPatronagePointsByArea(qvId, unitId, patronageTypeId);
	}
	
	public List<PoiPoint> getPoisPointByArea(Integer qvId, Integer unitId)
	{
		return new MapClient().getPoisPointByArea(qvId, unitId);
	}
	
	public List<BuildingMap> getBuildingsByArea(Integer qvId, Integer unitId)
	{
		return new MapClient().getBuildingsByArea(qvId, unitId);
	}
	
	public QvMap getQvById(Integer qvId)
	{
		return new MapClient().getQvById(qvId);
	}
	
	public List<QvMap> getQvsByUnitId(Integer unitId)
	{
		return new MapClient().getQvsByUnitId(unitId);
	}
	
	public UnitMap getUnitById(Integer unitId)
	{
		return new MapClient().getUnitById(unitId);
	}
	
	public List<PersonPoint> getFirstTimeVoterPointsByArea(Integer qvId, Integer unitId)
	{
		return new MapClient().getFirstTimeVoterPointsByArea(qvId, unitId);
	}
	
	public List<PersonPoint> getMemberPointsByArea(Integer qvId, Integer unitId)
	{
		return new MapClient().getMemberPointsByArea(qvId, unitId);
	}
	
	
	
	
	
}
