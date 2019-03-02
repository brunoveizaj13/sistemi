package com.brunoveizaj.sistemi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.brunoveizaj.sistemi.constants.IStatus;
import com.brunoveizaj.sistemi.entities.Poi;

@Repository
@SuppressWarnings("unchecked")
public class PoiDAO {
	
	@PersistenceContext
	EntityManager em;
	
	
	
	public Poi findByNid(String nid, int type) {
		
		List<Poi> list = em.createQuery("FROM Poi p WHERE p.status=:st AND p.person.nid=:nid and p.poiType.id=:pid")
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
	

}
