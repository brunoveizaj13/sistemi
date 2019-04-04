package com.brunoveizaj.sistemi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunoveizaj.sistemi.dao.CrudDAO;
import com.brunoveizaj.sistemi.dao.QvStatsDAO;
import com.brunoveizaj.sistemi.entities.AlbaniaStatistic;
import com.brunoveizaj.sistemi.entities.MunicipalityStatistic;
import com.brunoveizaj.sistemi.entities.Person;
import com.brunoveizaj.sistemi.entities.QvStatistic;
import com.brunoveizaj.sistemi.entities.RegionStatistic;
import com.brunoveizaj.sistemi.entities.UnitStatistic;
import com.brunoveizaj.sistemi.models.KeyValue;

@Service
public class QvStatsService {
	
	
	@Autowired CrudDAO crudDAO;
	@Autowired QvStatsDAO qvStatsDAO;
	
	public List<Person> persons(Integer qvId, String uname)
	{
		return qvStatsDAO.persons(qvId);
	}
	
	public List<Person> personsNotInPatronage(Integer qvId, String uname)
	{
		return qvStatsDAO.personsNotInPatronage(qvId);
	}
	
	public List<Person> personsInPatronage(Integer qvId, Integer type, String uname)
	{
		return qvStatsDAO.personsInPatronage(qvId, type);
	}
	
	public List<Person> patronages(Integer qvId, Integer type, String uname)
	{
		return qvStatsDAO.patronages(qvId, type);
	}
	
	public List<Person> firstTimeVoters(Integer qvId, String uname)
	{
		return qvStatsDAO.firstTimeVoters(qvId);
	}
	
	public List<Person> notVotingLastElections(Integer qvId, String uname)
	{
		return qvStatsDAO.notVotingLastElections(qvId);
	}
	
	public QvStatistic getQvStatistic(Integer id)
	{
		return crudDAO.findById(QvStatistic.class, id);
	}
	
	public List<QvStatistic> getQvStatisticsByUnit(Integer unitId)
	{
		return qvStatsDAO.getQvStatisticsByUnit(unitId);
	}
	
	public UnitStatistic getUnitStatistic(Integer id)
	{
		return crudDAO.findById(UnitStatistic.class, id);
	}
	
	public List<UnitStatistic> getUnitStatisticsByMunicipality(Integer regionId, Integer municipalityId)
	{
		return qvStatsDAO.getUnitStatisticsByMunicipality(regionId, municipalityId);
	}
	
	public MunicipalityStatistic getMunicipalityStatistic(Integer id)
	{
		return crudDAO.findById(MunicipalityStatistic.class, id);
	}
	
	public List<MunicipalityStatistic> getMunicipalityStatisticsByRegion(Integer regionId)
	{
		return qvStatsDAO.getMunicipalityStatisticsByRegion(regionId);
	}
	
	public RegionStatistic getRegionStatistic(Integer id)
	{
		return crudDAO.findById(RegionStatistic.class, id);
	}
	
	public List<RegionStatistic> getRegionStatistics()
	{
		return qvStatsDAO.getRegionStatistics();
	}
	
	public AlbaniaStatistic getAlbaniaStatistic()
	{
		return crudDAO.findById(AlbaniaStatistic.class, 1);
	}
	
	public List<KeyValue> getInstitutionPatronages(Integer regionId, Integer municipalityId, Integer unitId, Integer qvId)
	{
		return qvStatsDAO.getInstitutionPatronages(regionId, municipalityId, unitId, qvId);
	}
	
	public List<KeyValue> getInstitutionInPatronages(Integer regionId, Integer municipalityId, Integer unitId, Integer qvId)
	{
		return qvStatsDAO.getInstitutionInPatronages(regionId, municipalityId, unitId, qvId);
	}
	
	////******************
	public List<KeyValue> getPatronagesByDate(Date from, Date to, Integer typeId, Integer regionId, Integer municipId, Integer unitId, Integer qvId)
	{
		return qvStatsDAO.getPatronagesByDate(from, to, typeId, regionId, municipId, unitId, qvId);
	}
	
	public List<KeyValue> getInPatronagesByDate(Date from, Date to, Integer typeId, Integer regionId, Integer municipId, Integer unitId, Integer qvId)
	{
		return qvStatsDAO.getInPatronagesByDate(from, to, typeId, regionId, municipId, unitId, qvId);
	}
	
	
	
	
	
	
	
	
	

}
