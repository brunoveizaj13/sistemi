package com.brunoveizaj.sistemi.ui.services;

import java.util.Date;
import java.util.List;

import com.brunoveizaj.sistemi.ui.api.clients.QvStatsClient;
import com.brunoveizaj.sistemi.ui.models.AlbaniaStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.KeyValue;
import com.brunoveizaj.sistemi.ui.models.MunicipalityStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.PersonDTO;
import com.brunoveizaj.sistemi.ui.models.QvStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.RegionStatisticDTO;
import com.brunoveizaj.sistemi.ui.models.UnitStatisticDTO;

public class QvStatsService {
	
	public List<PersonDTO> persons(Integer qvId) {
		return new QvStatsClient().persons(qvId);
	}
	
	public List<PersonDTO> personsNotInPatronage(Integer qvId) {
		return new QvStatsClient().personsNotInPatronage(qvId);
	}
	
	public List<PersonDTO> personsInPatronage(Integer qvId, Integer type) {
		return new QvStatsClient().personsInPatronage(qvId, type);
	}
	
	public List<PersonDTO> patronages(Integer qvId, Integer type) {
		return new QvStatsClient().patronages(qvId, type);
	}
	
	public List<PersonDTO> firstTimeVoters(Integer qvId) {
		return new QvStatsClient().firstTimeVoters(qvId);
	}
	
	public List<PersonDTO> notVotingLastElections(Integer qvId) {
		return new QvStatsClient().notVotingLastElections(qvId);
	}
	
	
	public List<QvStatisticDTO> getQvStatisticsByUnit(Integer unitId) {
		return new QvStatsClient().getQvStatisticsByUnit(unitId);
	}
	
	public List<UnitStatisticDTO> getUnitStatisticsByMunicipality(Integer regionId, Integer munId) {
		return new QvStatsClient().getUnitStatisticsByMunicipality(regionId, munId);
	}
	
	public List<MunicipalityStatisticDTO> getMunicipalityStatisticsByRegion(Integer regionId) {
		return new QvStatsClient().getMunicipalityStatisticsByRegion(regionId);
	}
	
	public List<RegionStatisticDTO> getRegionStatistics() {
		return new QvStatsClient().getRegionStatistics();
	}
	
	public QvStatisticDTO getQvStatistic(Integer id) {
		return new QvStatsClient().getQvStatistic(id);
	}
	
	public UnitStatisticDTO getUnitStatistic(Integer id) {
		return new QvStatsClient().getUnitStatistic(id);
	}
	
	public MunicipalityStatisticDTO getMunicipalityStatistic(Integer id) {
		return new QvStatsClient().getMunicipalityStatistic(id);
	}
	
	public RegionStatisticDTO getRegionStatistic(Integer id) {
		return new QvStatsClient().getRegionStatistic(id);
	}
	
	public AlbaniaStatisticDTO getAlbaniaStatistic() {
		return new QvStatsClient().getAlbaniaStatistic();
	}
	
	//************************
	public List<KeyValue> getInstitutionPatronages(Integer regionId, Integer munId, Integer unitId, Integer qvId) {
		return new QvStatsClient().getInstitutionPatronages(regionId, munId, unitId, qvId);
	}
	
	public List<KeyValue> getInstitutionInPatronages(Integer regionId, Integer munId, Integer unitId, Integer qvId) {
		return new QvStatsClient().getInstitutionInPatronages(regionId, munId, unitId, qvId);
	}
	
	
//***************************
	public List<KeyValue> getPatronagesByDate(Date from, Date to, Integer type, Integer regionId, Integer municipId,
			Integer unitId, Integer qvId) {
		return new QvStatsClient().getPatronagesByDate(from, to, type, regionId, municipId,
				unitId, qvId);
	}

	public List<KeyValue> getInPatronagesByDate(Date from, Date to, Integer type, Integer regionId, Integer municipId,
			Integer unitId, Integer qvId) {
		return new QvStatsClient().getInPatronagesByDate(from, to, type, regionId, municipId,
				unitId, qvId);
	}


	public List<KeyValue> getPoisByDate(Date from, Date to, Integer regionId, Integer municipId, Integer unitId,
			Integer qvId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
