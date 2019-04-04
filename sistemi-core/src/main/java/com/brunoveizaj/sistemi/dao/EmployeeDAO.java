package com.brunoveizaj.sistemi.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.brunoveizaj.sistemi.entities.Employee;
import com.brunoveizaj.sistemi.entities.EmploymentPeriod;
import com.brunoveizaj.sistemi.forms.EmployeeSx;
import com.brunoveizaj.sistemi.utils.StringUtil;

@SuppressWarnings("unchecked")
@Repository
public class EmployeeDAO {

	
	@PersistenceContext
	EntityManager em;
	
	
	public List<EmploymentPeriod> getEmploymentPeriods(String nid)
	{
		return em.createQuery("FROM EmploymentPeriod e WHERE e.nid=:nid ORDER BY e.fromDate DESC,e.toDate DESC")
				.setParameter("nid", nid.trim().toUpperCase())
				.getResultList();
	}
	
	
	@SuppressWarnings("rawtypes")
	public List<Employee> searchEmployees(EmployeeSx criterias)
	{
		
		HashMap<String,Object> params = new HashMap<>();
		String sql = "FROM Employee t WHERE 1=1 ";
		
		
		
		String order;

		if(StringUtil.isValid(criterias.getNid()))
		{
			sql += "AND t.internationalNid LIKE :nid ";
			params.put("nid", criterias.getNid());
		}
		if(StringUtil.isValid(criterias.getNipt()))
		{
			sql += "AND t.nipt LIKE :nipt ";
			params.put("nipt", criterias.getNipt());
		}
		if(StringUtil.isValid(criterias.getSubject()))
		{
			sql += "AND t.subject LIKE :subj ";
			params.put("subj", criterias.getSubject());
		}
		if(StringUtil.isValid(criterias.getName()))
		{
			sql += "AND t.name LIKE :name ";
			params.put("name", criterias.getName());
		}
			
	    if(StringUtil.isValid(criterias.getSurname()))
		{
	    	sql += "AND t.surname LIKE :surname ";
			params.put("surname", criterias.getSurname());
		}
	    if(criterias.getYear() != null)
		{
			sql += "AND t.year = :year ";
			params.put("year", criterias.getYear());
		}
		
		if(criterias.getMonth() != null)
		{
			sql += "AND t.month = :month ";
			params.put("month", criterias.getMonth());
		}
		
		if(StringUtil.isValid(criterias.getNid()))
		{
			order = "ORDER BY t.year desc,t.month desc";
		}
		else if(StringUtil.isValid(criterias.getNipt()))
		{
			order = "ORDER BY t.salary desc";
		}
		else if(StringUtil.isValid(criterias.getSubject()))
		{
			order = "ORDER BY t.subject";
		}
		else {
			order = "ORDER BY t.name,t.surname";
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
