package com.brunoveizaj.sistemi.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.brunoveizaj.sistemi.constants.IPatronageType;
import com.brunoveizaj.sistemi.constants.IStatus;
import com.brunoveizaj.sistemi.entities.MunicipalityStatistic;
import com.brunoveizaj.sistemi.entities.Person;
import com.brunoveizaj.sistemi.entities.QvStatistic;
import com.brunoveizaj.sistemi.entities.RegionStatistic;
import com.brunoveizaj.sistemi.entities.UnitStatistic;
import com.brunoveizaj.sistemi.models.KeyValue;
import com.brunoveizaj.sistemi.utils.DateUtil;

@Repository
@SuppressWarnings("unchecked")
public class QvStatsDAO {

	@PersistenceContext
	EntityManager em;
		
	
	public List<Person> persons(Integer qvId)
	{
		return em.createQuery("From Person p join fetch p.details d where p.qv.id=:qvid ORDER BY p.fraction,p.votingNo")
				.setParameter("qvid", qvId)
				.getResultList();
	}
	
	public List<Person> personsNotInPatronage(Integer qvId)
	{
		return em.createQuery("From Person p join fetch p.details d where p.qv.id=:qvid AND p NOT IN (SELECT pp.person FROM PatronagePerson pp WHERE pp.status=:ppst AND pp.patronage.patronageType.id=:pid AND pp.person.qv.id=:pqvid) ORDER BY p.fraction,p.votingNo")
				.setParameter("qvid", qvId)
				.setParameter("pid", IPatronageType.PERSON)
				.setParameter("ppst", IStatus.ACTIVE)
				.setParameter("pqvid", qvId)
				.getResultList();
	}
	
	public List<Person> personsInPatronage(Integer qvId, Integer type)
	{
		return em.createQuery("SELECT DISTINCT(pp.person) From PatronagePerson pp where pp.status=:st AND pp.patronage.patronageType.id=:pid AND pp.person.qv.id=:qvid ORDER BY pp.person.fraction,pp.person.votingNo")
				.setParameter("qvid", qvId)
				.setParameter("pid", type)
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<Person> patronages(Integer qvId, Integer type)
	{
		return em.createQuery("SELECT DISTINCT(pp.patronage.person) From PatronagePerson pp where pp.status=:st AND pp.patronage.patronageType.id=:pid AND pp.patronage.person.qv.id=:qvid ORDER BY pp.patronage.person.fraction,pp.patronage.person.votingNo")
				.setParameter("qvid", qvId)
				.setParameter("pid", type)
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<Person> firstTimeVoters(Integer qvId)
	{
		return em.createQuery("From Person p join fetch p.details d where p.qv.id=:qvid AND d.firstTimeVoterStatus=:st ORDER BY p.fraction,p.votingNo")
				.setParameter("qvid", qvId)
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<Person> notVotingLastElections(Integer qvId)
	{
		return em.createQuery("From Person p join fetch p.details d where p.qv.id=:qvid AND d.voting2017Status=:st ORDER BY p.fraction,p.votingNo")
				.setParameter("qvid", qvId)
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}
	
	
	public List<QvStatistic> getQvStatisticsByUnit(Integer unitId)
	{
		return em.createQuery("FROM QvStatistic s join fetch s.unit u WHERE u.id=:uid ORDER BY u.name,s.code")
				.setParameter("uid", unitId)
				.getResultList();
	}
	
	public List<UnitStatistic> getUnitStatisticsByMunicipality(Integer regionId, Integer municipalityId)
	{
		
		if(municipalityId != null)
		{
		  return em.createQuery("FROM UnitStatistic s join fetch s.municipality m WHERE m.id=:mid ORDER BY m.name,s.unit")
				.setParameter("mid", municipalityId)
				.getResultList();
		}
		else if(regionId != null)
		{
			return em.createQuery("FROM UnitStatistic s join fetch s.municipality m WHERE m.region.id=:rid ORDER BY m.region.name,m.name,s.unit")
					.setParameter("rid", regionId)
					.getResultList();
		}
		else
		{
			return em.createQuery("FROM UnitStatistic s join fetch s.municipality m ORDER BY m.region.name,m.name,s.unit")
					.getResultList();
		}
	}
	
	public List<MunicipalityStatistic> getMunicipalityStatisticsByRegion(Integer regionId)
	{
		return em.createQuery("FROM MunicipalityStatistic s join fetch s.region r WHERE r.id=:rid ORDER BY r.name,s.municipality")
				.setParameter("rid", regionId)
				.getResultList();
	}
	
	public List<RegionStatistic> getRegionStatistics()
	{
		return em.createQuery("FROM RegionStatistic s ORDER BY s.region")
				.getResultList();
	}
	
	
	public List<KeyValue> getInstitutionPatronages(Integer regionId, Integer municipalityId, Integer unitId, Integer qvId)
	{
		if(municipalityId != null)
		{
			return em.createQuery("SELECT new "+KeyValue.class.getName()+" (p.institution.name, count(p) as cnt) "
					+ "FROM Patronage p "
					+ "WHERE p.institution.status=:st AND p.status=:pst AND p.patronageType.id=:pid AND p.person.qv.unit.municipality.id=:mid "
					+ "GROUP BY p.institution.name ORDER BY cnt DESC")
					.setParameter("st", IStatus.ACTIVE)
					.setParameter("pst", IStatus.ACTIVE)
					.setParameter("pid", IPatronageType.INSTITUTION)
					.setParameter("mid", municipalityId)
					.getResultList();
		}
		else if(regionId != null)
		{
			return em.createQuery("SELECT new "+KeyValue.class.getName()+" (p.institution.name, count(p) as cnt) "
					+ "FROM Patronage p "
					+ "WHERE p.institution.status=:st AND p.status=:pst AND p.patronageType.id=:pid AND p.person.qv.unit.municipality.region.id=:rid "
					+ "GROUP BY p.institution.name ORDER BY cnt DESC")
					.setParameter("st", IStatus.ACTIVE)
					.setParameter("pst", IStatus.ACTIVE)
					.setParameter("pid", IPatronageType.INSTITUTION)
					.setParameter("rid", regionId)
					.getResultList();
		}
		else
		{
			return em.createQuery("SELECT new "+KeyValue.class.getName()+" (p.institution.name, count(p) as cnt) "
					+ "FROM Patronage p "
					+ "WHERE p.institution.status=:st AND p.status=:pst AND p.patronageType.id=:pid "
					+ "GROUP BY p.institution.name ORDER BY cnt DESC")
					.setParameter("st", IStatus.ACTIVE)
					.setParameter("pst", IStatus.ACTIVE)
					.setParameter("pid", IPatronageType.INSTITUTION)
					.getResultList();
		}
		
		
		
	}
	
	
	public List<KeyValue> getInstitutionInPatronages(Integer regionId, Integer municipalityId, Integer unitId, Integer qvId)
	{
		if(municipalityId != null)
		{
			return em.createQuery("SELECT new "+KeyValue.class.getName()+" (pp.patronage.institution.name, count(pp.person) as cnt) "
					+ "FROM PatronagePerson pp "
					+ "WHERE pp.patronage.institution.status=:st AND pp.status=:pst AND pp.patronage.patronageType.id=:pid AND pp.person.qv.unit.municipality.id=:mid "
					+ "GROUP BY pp.patronage.institution.name ORDER BY cnt DESC")
					.setParameter("st", IStatus.ACTIVE)
					.setParameter("pst", IStatus.ACTIVE)
					.setParameter("pid", IPatronageType.INSTITUTION)
					.setParameter("mid", municipalityId)
					.getResultList();
		}
		else if(regionId != null)
		{
			return em.createQuery("SELECT new "+KeyValue.class.getName()+" (pp.patronage.institution.name, count(pp.person) as cnt) "
					+ "FROM PatronagePerson pp "
					+ "WHERE pp.patronage.institution.status=:st AND pp.status=:pst AND pp.patronage.patronageType.id=:pid AND pp.person.qv.unit.municipality.region.id=:rid "
					+ "GROUP BY pp.patronage.institution.name ORDER BY cnt DESC")
					.setParameter("st", IStatus.ACTIVE)
					.setParameter("pst", IStatus.ACTIVE)
					.setParameter("pid", IPatronageType.INSTITUTION)
					.setParameter("rid", regionId)
					.getResultList();
		}
		else
		{
			return em.createQuery("SELECT new "+KeyValue.class.getName()+" (pp.patronage.institution.name, count(pp.person) as cnt) "
					+ "FROM PatronagePerson pp "
					+ "WHERE pp.patronage.institution.status=:st AND pp.status=:pst AND pp.patronage.patronageType.id=:pid "
					+ "GROUP BY pp.patronage.institution.name ORDER BY cnt DESC")
					.setParameter("st", IStatus.ACTIVE)
					.setParameter("pst", IStatus.ACTIVE)
					.setParameter("pid", IPatronageType.INSTITUTION)
					.getResultList();
		}
		
		
		
	}
	
	
	public List<KeyValue> getPatronagesByDate(Date from, Date to, Integer typeId, Integer regionId, Integer municipId, Integer unitId, Integer qvId)
	{
		String sql = "SELECT new "+KeyValue.class.getName()+" (trunc(p.createTime), count(p) as cnt) "
				+ "FROM Patronage p WHERE p.status=:pst AND p.patronageType.id=:pid AND p.createTime>=:fdt AND p.createTime<:tdt ";
		if(regionId != null)
		{
			sql += "AND p.person.qv.unit.municipality.region.id=:rid ";
		}
		if(municipId != null)
		{
			sql += "AND p.person.qv.unit.municipality.id=:mid ";
		}
		if(unitId != null)
		{
			sql += "AND p.person.qv.unit.id=:uid ";
		}
		if(qvId != null)
		{
			sql += "AND p.person.qv.id=:qid ";
		}
				
		sql += " GROUP BY trunc(p.createTime) ORDER BY trunc(p.createTime)";
		
		Query q = em.createQuery(sql).setParameter("pst", IStatus.ACTIVE).setParameter("pid", typeId).setParameter("fdt", from)
				.setParameter("tdt", DateUtil.addDaysToDate(to, 1));
		
		if(regionId != null)
		{
			q.setParameter("rid", regionId);
		}
		if(municipId != null)
		{
			q.setParameter("mid", municipId);
		}
		if(unitId != null)
		{
			q.setParameter("uid", unitId);
		}
		if(qvId != null)
		{
			q.setParameter("qid", qvId);
		}
		
		
		return q.getResultList();
		
	}
	
	
	
	public List<KeyValue> getInPatronagesByDate(Date from, Date to, Integer typeId, Integer regionId, Integer municipId, Integer unitId, Integer qvId)
	{
		String sql = "SELECT new "+KeyValue.class.getName()+" (trunc(pp.createTime), count(pp.person) as cnt) "
				+ "FROM PatronagePerson pp WHERE pp.status=:pst AND pp.patronage.patronageType.id=:pid AND pp.createTime>=:fdt AND pp.createTime<:tdt ";
		if(regionId != null)
		{
			sql += "AND pp.person.qv.unit.municipality.region.id=:rid ";
		}
		if(municipId != null)
		{
			sql += "AND pp.person.qv.unit.municipality.id=:mid ";
		}
		if(unitId != null)
		{
			sql += "AND pp.person.qv.unit.id=:uid ";
		}
		if(qvId != null)
		{
			sql += "AND pp.person.qv.id=:qid ";
		}
				
		sql += " GROUP BY trunc(pp.createTime) ORDER BY trunc(pp.createTime)";
		
		Query q = em.createQuery(sql).setParameter("pst", IStatus.ACTIVE).setParameter("pid", typeId).setParameter("fdt", from)
				.setParameter("tdt", DateUtil.addDaysToDate(to, 1));
		
		if(regionId != null)
		{
			q.setParameter("rid", regionId);
		}
		if(municipId != null)
		{
			q.setParameter("mid", municipId);
		}
		if(unitId != null)
		{
			q.setParameter("uid", unitId);
		}
		if(qvId != null)
		{
			q.setParameter("qid", qvId);
		}
		
		
		return q.getResultList();
		
	}
	
	
	
	
	
}
