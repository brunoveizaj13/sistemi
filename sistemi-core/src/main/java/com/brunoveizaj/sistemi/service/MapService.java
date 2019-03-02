package com.brunoveizaj.sistemi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunoveizaj.sistemi.dao.MapDAO;
import com.brunoveizaj.sistemi.models.map.BuildingMap;
import com.brunoveizaj.sistemi.models.map.PatronagePoint;
import com.brunoveizaj.sistemi.models.map.PersonPoint;
import com.brunoveizaj.sistemi.models.map.PoiPoint;
import com.brunoveizaj.sistemi.models.map.QvMap;
import com.brunoveizaj.sistemi.models.map.UnitMap;

@Service
public class MapService {

	
	@Autowired MapDAO mapDAO;
	
	
	public List<PersonPoint> getPersonPointByNid(String nid, String uname)
	{
		return mapDAO.getPersonPointByNid(nid);
	}
	
	public List<PatronagePoint> getPatronagesPointByArea(Integer qvId, Integer unitId, Integer patronageTypeId, String uname)
	{
		return mapDAO.getPatronagesPointByArea(qvId, unitId, patronageTypeId);
	}
	
	public List<PersonPoint> getInPatronagePointsByPatronageNid(Integer patronageNid, Integer patronageTypeId, String uname)
	{
		return mapDAO.getInPatronagePointsByPatronageNid(patronageNid, patronageTypeId);
	}
	
	public List<PersonPoint> getInPatronagePointsByArea(Integer qvId, Integer unitId, Integer patronageTypeId, String uname)
	{
		return mapDAO.getInPatronagePointsByArea(qvId, unitId, patronageTypeId);
	}
	
	public List<PoiPoint> getPoisPointByArea(Integer qvId, Integer unitId, String uname)
	{
		return mapDAO.getPoisPointByArea(qvId, unitId);
	}
	
	public List<BuildingMap> getBuildingsByArea(Integer qvId, Integer unitId, String uname)
	{
		return mapDAO.getBuildingsByArea(qvId, unitId);
	}
	
	public QvMap getQvById(Integer qvId, String uname)
	{
		return mapDAO.getQvById(qvId);
	}
	
	public List<QvMap> getQvsByUnitId(Integer unitId, String uname)
	{
		return mapDAO.getQvsByUnitId(unitId);
	}
	
	public UnitMap getUnitById(Integer unitId, String uname)
	{
		return mapDAO.getUnitById(unitId);
	}
	
	public List<PersonPoint> getFirstTimeVoterPointsByArea(Integer qvId, Integer unitId, String uname)
	{
		return mapDAO.getFirstTimeVoterPointsByArea(qvId, unitId);
	}
	
	public List<PersonPoint> getMemberPointsByArea(Integer qvId, Integer unitId, String uname)
	{
		return mapDAO.getMemberPointsByArea(qvId, unitId);
	}
	
	
	
	
	
	
}
