package com.brunoveizaj.sistemi.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.brunoveizaj.sistemi.constants.IStatus;
import com.brunoveizaj.sistemi.entities.Patronage;
import com.brunoveizaj.sistemi.entities.PatronagePerson;

@Repository
@SuppressWarnings("unchecked")
public class PatronageDAO {

	
	@PersistenceContext
	EntityManager em;
	
	
	public Patronage findByNid(String nid, int type) {

		
		List<Patronage> list = em.createQuery("FROM Patronage p JOIN FETCH p.person per WHERE p.status=:st AND per.nid=:nid and p.patronageType.id=:pid")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", nid)
				.setParameter("pid", type)
				.getResultList();
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
		
	}

	public List<PatronagePerson> getPatronagePersons(int id, int type) {

		return em.createQuery("FROM PatronagePerson pp WHERE pp.status=:st AND pp.patronage.id=:id AND pp.patronage.patronageType.id=:pid ORDER BY pp.id DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("id", id)
				.setParameter("pid", type)
				.getResultList();
		
	}

	public List<PatronagePerson> getPatronagesOfPerson(String personNid, int patronageTypeId) {		
		
		return em.createQuery("FROM PatronagePerson pp WHERE pp.status=:st "
							+ "AND pp.patronage.patronageType.id=:pid AND pp.person.nid=:nid ORDER BY pp.id DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("nid", personNid)
				.setParameter("pid", patronageTypeId)
				.getResultList();
	}

	
	public List<Patronage> getPatronagesByArea(Integer unitId, Integer qvId, Integer type)
	{
		Set<Patronage> set = new HashSet<>();
				
		if(unitId != null)
		{
			List<Patronage> list = em.createQuery("FROM Patronage p JOIN FETCH p.person per WHERE p.status=:st AND p.patronageType.id=:pid "
					+ "AND per.qv.unit.id=:uid ORDER BY per.name,per.surname")
					.setParameter("st", IStatus.ACTIVE)
					.setParameter("uid", unitId)
					.setParameter("pid", type)
					.getResultList();
			
			if(list!=null) set.addAll(list);
		}
		
		if(qvId != null)
		{
			List<Patronage> list = em.createQuery("FROM Patronage p JOIN FETCH p.person per WHERE p.status=:st AND p.patronageType.id=:pid "
					+ "AND per.qv.id=:qid ORDER BY per.name,per.surname")
					.setParameter("st", IStatus.ACTIVE)
					.setParameter("qid", qvId)
					.setParameter("pid", type)
					.getResultList();
			
			if(list!=null) set.addAll(list);
		}
		
		return new ArrayList<Patronage>(set);
	}
	
	
	
}
