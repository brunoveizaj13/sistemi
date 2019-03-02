package com.brunoveizaj.sistemi.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.brunoveizaj.sistemi.entities.Subject;
import com.brunoveizaj.sistemi.forms.SubjectSx;
import com.brunoveizaj.sistemi.utils.StringUtil;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class SubjectDAO {

	
	
	@PersistenceContext
	EntityManager em;
	
	
	public List<Subject> searchSubject(SubjectSx criterias)
	{
		HashMap<String,Object> params = new HashMap<>();
		String sql = "FROM Subject s WHERE 1=1 ";
		String order = "ORDER BY s.name";

		if(StringUtil.isValid(criterias.getNipt()))
		{
			sql += "AND s.nipt LIKE :nipt ";
			params.put("nipt", criterias.getNipt().trim().toUpperCase());
		}
		
		if(StringUtil.isValid(criterias.getName()))
		{
			sql += "AND s.name LIKE :name ";
			params.put("name", criterias.getName().trim().toUpperCase());
		}
		
		if(StringUtil.isValid(criterias.getNotName()))
		{
			sql += "AND s.name NOT LIKE :nname ";
			params.put("nname", criterias.getNotName().trim().toUpperCase());
		}
		
		if(StringUtil.isValid(criterias.getLegalForm()))
		{
			sql += "AND s.legalForm LIKE :lf ";
			params.put("name", criterias.getName().trim().toUpperCase());
		}
		
		if(StringUtil.isValid(criterias.getAdministrator()))
		{
			sql += "AND s.administrator LIKE :adm ";
			params.put("adm", "%"+criterias.getAdministrator().trim().toUpperCase()+"%");
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