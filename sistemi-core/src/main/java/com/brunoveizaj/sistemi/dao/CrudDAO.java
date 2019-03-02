package com.brunoveizaj.sistemi.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.brunoveizaj.sistemi.constants.IStatus;
import com.brunoveizaj.sistemi.entities.Institution;
import com.brunoveizaj.sistemi.entities.Municipality;
import com.brunoveizaj.sistemi.entities.Party;
import com.brunoveizaj.sistemi.entities.PatronageType;
import com.brunoveizaj.sistemi.entities.PoiType;
import com.brunoveizaj.sistemi.entities.Qv;
import com.brunoveizaj.sistemi.entities.Region;
import com.brunoveizaj.sistemi.entities.Role;
import com.brunoveizaj.sistemi.entities.Unit;
import com.brunoveizaj.sistemi.models.MonthYear;
import com.brunoveizaj.sistemi.utils.StringUtil;


@Repository
@SuppressWarnings("unchecked")
public class CrudDAO {

	@PersistenceContext
	EntityManager em;

	public <T> T create(T entity) {
		em.persist(entity);
		em.flush();
		return entity;
	}

	public <T> T update(T entity) {
		return em.merge(entity);
	}

	public <T> T findById(Class<T> claxx, Object id) {
		return em.find(claxx, id);
	}

	
	//******** LIST *********
	
	
	
	public List<Institution> listInstitution()
	{
		return em.createQuery("FROM Institution i WHERE i.status=:st ORDER BY i.rank")
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<Region> listRegion()
	{
		return em.createQuery("FROM Region r ORDER BY r.name")
				.getResultList();
	}
	
	public List<Municipality> listMunicipality(Integer regionId, String name)
	{
		String sql = "FROM Municipality m WHERE 1=1 ";
		
		if(regionId != null)
		{
			sql += "AND m.region.id=:rid ";
		}
		
		if(StringUtil.isValid(name))
		{
			sql += "AND UPPER(m.name) like :name ";
		}
		
		sql += "ORDER BY m.name";
		
		Query q = em.createQuery(sql);
		
		if(regionId != null)
		{
			q.setParameter("rid", regionId);
		}
		
		if(StringUtil.isValid(name))
		{
			q.setParameter("name", "%"+name.trim().toUpperCase()+"%");
		}
		
		return q.getResultList();
	}
	
	public List<Unit> listUnit(Integer regionId, Integer municipId, String name)
	{
		String sql = "FROM Unit u WHERE 1=1 ";
		
		if(regionId != null)
		{
			sql += "AND u.municipality.region.id=:rid ";
		}
		
		if(municipId != null)
		{
			sql += "AND u.municipality.id=:mid ";
		}
		
		if(StringUtil.isValid(name))
		{
			sql += "AND UPPER(u.name) like :name ";
		}
		
		sql += "ORDER BY u.name";
		
		Query q = em.createQuery(sql);
		
		if(regionId != null)
		{
			q.setParameter("rid", regionId);
		}
		
		if(municipId != null)
		{
			q.setParameter("mid", municipId);
		}
		
		if(StringUtil.isValid(name))
		{
			q.setParameter("name", "%"+name.trim().toUpperCase()+"%");
		}
		
		return q.getResultList();
	}
	
	public List<Qv> listQv(Integer regionId, Integer municipId, Integer unitId, String code)
	{
		String sql = "FROM Qv q WHERE 1=1 ";
		
		if(regionId != null)
		{
			sql += "AND q.unit.municipality.region.id=:rid ";
		}
		
		if(municipId != null)
		{
			sql += "AND q.unit.municipality.id=:mid ";
		}
		
		if(unitId != null)
		{
			sql += "AND q.unit.id=:uid ";
		}
		
		if(StringUtil.isValid(code))
		{
			sql += "AND q.code like :code ";
		}
		
		sql += "ORDER BY q.code";
		
		Query q = em.createQuery(sql);
		
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
		
		if(StringUtil.isValid(code))
		{
			q.setParameter("code", code.trim()+"%");
		}
		
		return q.getResultList();
	}
	
	public List<Party> listParty()
	{
		return em.createQuery("FROM Party p ORDER BY p.rank").getResultList();
	}
	
	public List<PatronageType> listPatronageType()
	{
		return em.createQuery("FROM PatronageType p ").getResultList();
	}
	
	public List<PoiType> listPoiType()
	{
		return em.createQuery("FROM PoiType p WHERE p.status=:st ORDER BY p.rank")
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<Role> listRole()
	{
		return em.createQuery("FROM Role r ").getResultList();
	}
	
	public List<MonthYear> getPayrollMonthYears()
	{
		return em.createQuery("SELECT DISTINCT new "+MonthYear.class.getName()+"(t.month,t.year) FROM Employee t ORDER BY t.year desc,t.month desc")
				.getResultList();
	}
	
	
}
