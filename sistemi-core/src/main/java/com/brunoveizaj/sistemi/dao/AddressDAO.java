package com.brunoveizaj.sistemi.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.brunoveizaj.sistemi.entities.Address;

@Repository
@SuppressWarnings("unchecked")
public class AddressDAO {
	
	@PersistenceContext
	EntityManager em;
	
	
	
	
	public List<Address> getAddressByNid(String nid)
	{
		return em.createQuery("FROM Address a WHERE a.nid=:nid")
				.setParameter("nid", nid.trim().toUpperCase())
				.getResultList();
	}
	
	public List<Address> getAddressByBuildingId(Integer buildingId)
	{
		return em.createQuery("FROM Address a WHERE a.buildingId=:bid")
				.setParameter("bid", BigInteger.valueOf(buildingId))
				.getResultList();
	}
	
	

}
