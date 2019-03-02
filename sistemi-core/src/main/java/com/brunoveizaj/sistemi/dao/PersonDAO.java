package com.brunoveizaj.sistemi.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.brunoveizaj.sistemi.constants.IElections;
import com.brunoveizaj.sistemi.entities.Person;
import com.brunoveizaj.sistemi.forms.PersonSx;
import com.brunoveizaj.sistemi.utils.StringUtil;



@Repository
@SuppressWarnings("unchecked")
public class PersonDAO {
	
	
	@PersistenceContext
	EntityManager em;
	
	
	public List<Person> getFamily(Long familyId)
	{
		return em.createQuery("FROM Person p WHERE p.familyId=:fid ORDER BY p.familyRowId")
				.setParameter("fid", familyId).getResultList();
	}
	
	public List<Person> getFamily(String nid)
	{
		return em.createQuery("FROM Person p WHERE p.familyId IN (SELECT s.familyId FROM Person s WHERE s.nid=:nid) ORDER BY p.familyRowId")
				.setParameter("nid", nid).getResultList();
	}
	
	@SuppressWarnings("rawtypes")
	public List<Person> searchPerson(PersonSx criterias)
	{
		
		HashMap<String,Object> params = new HashMap<>();
		String sql = "FROM Person p WHERE 1=1 ";
		String order = "ORDER BY p.nid";
		
		if(StringUtil.isValid(criterias.getFraction()))
		{
			sql += "AND p.fraction=:frac ";
			params.put("frac", criterias.getFraction());
		}
		if(StringUtil.isValid(criterias.getVotingListNo()))
		{
			sql += "AND p.votingNo=:vno ";
			params.put("vno", criterias.getVotingListNo());
		}
		if(StringUtil.isValid(criterias.getDob()))
		{
			sql += "AND p.dob=:dob ";
			params.put("dob", criterias.getDob());
		}
		
		if(StringUtil.isValid(criterias.getFatherName()))
		{
			sql += "AND p.fatherNameSx LIKE :fn ";
			params.put("fn", criterias.getFatherName().trim().toUpperCase().replace("Ë", "E").replace("Ç", "C"));
		}				
		
		if(StringUtil.isValid(criterias.getGender()))
		{
			sql += "AND p.gender=:gender ";
			params.put("gender", criterias.getGender().trim().toUpperCase());
		}
		
		if(StringUtil.isValid(criterias.getMaidenName()))
		{
			sql += "AND p.maidenNameSx LIKE :maiden ";
			params.put("maiden", criterias.getMaidenName().trim().toUpperCase().replace("Ë", "E").replace("Ç", "C"));
		}
		
		if(StringUtil.isValid(criterias.getMotherName()))
		{
			sql += "AND p.motherNameSx LIKE :mn ";
			params.put("mn", criterias.getMotherName().trim().toUpperCase().replace("Ë", "E").replace("Ç", "C"));
		}			
		
		if(StringUtil.isValid(criterias.getName()))
		{
			sql += "AND p.nameSx LIKE :name ";
			params.put("name", criterias.getName().trim().toUpperCase().replace("Ë", "E").replace("Ç", "C"));
		}

		if(StringUtil.isValid(criterias.getNid()))
		{
			sql += "AND p.nid LIKE :nid ";
			params.put("nid", criterias.getNid().trim().toUpperCase());
		}
		
		if(StringUtil.isValid(criterias.getPob()))
		{
			sql += "AND p.pob LIKE :pob ";
			params.put("pob", criterias.getPob().trim().toUpperCase().replace("Ë", "E").replace("Ç", "C"));
		}
		
		if(StringUtil.isValid(criterias.getSurname()))
		{
			sql += "AND p.surnameSx LIKE :surname ";
			params.put("surname", criterias.getSurname().trim().toUpperCase().replace("Ë", "E").replace("Ç", "C"));
		}
		
		if(criterias.getFamilyId() != null)
		{
			sql += "AND p.familyId=:fid ";
			params.put("fid", criterias.getFamilyId());
		}
		
		if(criterias.getQvId() != null)
		{
			sql += "AND p.qv.id=:qid ";
			params.put("qid", criterias.getQvId());
		}
		
		if(criterias.getUnitId() != null)
		{
			sql += "AND p.qv.unit.id=:unid ";
			params.put("unid", criterias.getUnitId());
		}
		
		if(criterias.getMunicipalityId() != null)
		{
			sql += "AND p.qv.unit.municipality.id=:munid ";
			params.put("munid", criterias.getMunicipalityId());
		}
		
		if(criterias.getRegionId() != null)
		{
			sql += "AND p.qv.unit.municipality.region.id=:regid ";
			params.put("regid", criterias.getRegionId());
		}
		
		if(criterias.getFromAge() != null)
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(IElections.ELECTIONS_DATE);
			cal.add(Calendar.YEAR, -1*criterias.getFromAge());
			
			String db = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
			
			sql += "AND p.dob<=:fag ";
			params.put("fag", db);
		}
		
		if(criterias.getToAge() != null)
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(IElections.ELECTIONS_DATE);
			cal.add(Calendar.YEAR, -1*criterias.getFromAge());
			
			String db = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
			
			sql += "AND p.dob>=:tag ";
			params.put("tag", db);
		}
		
		
		sql += order;
				
		Query q = em.createQuery(sql);
		Iterator it = params.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry) it.next();
			q.setParameter((String) pair.getKey(), pair.getValue());
			it.remove();
		}
		
		
		if(criterias.getFirstResult() != null)
		{
			q.setFirstResult(criterias.getFirstResult());
		}
		
		if(criterias.getMaxResult() != null)
		{
			q.setMaxResults(criterias.getMaxResult());
		}
		
		
		
		return q.getResultList();
		
	}
	
	
	
	
	
	

}
