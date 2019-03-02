package com.brunoveizaj.sistemi.dao;

import java.util.List;

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

		
		List<Patronage> list = em.createQuery("FROM Patronage p WHERE p.status=:st AND p.person.nid=:nid and p.patronageType.id=:pid")
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

		return em.createQuery("FROM PatronagePerson pp WHERE p.status=:st AND pp.patronage.id=:id AND pp.patronage.patronageType.id=:pid ORDER BY pp.id DESC")
				.setParameter("st", IStatus.ACTIVE)
				.setParameter("id", id)
				.setParameter("pid", type)
				.getResultList();
		
	}

}
