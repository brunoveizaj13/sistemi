package com.brunoveizaj.sistemi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.brunoveizaj.sistemi.constants.IStatus;
import com.brunoveizaj.sistemi.entities.Department;
import com.brunoveizaj.sistemi.entities.DepartmentFunction;
import com.brunoveizaj.sistemi.entities.DepartmentPosition;
import com.brunoveizaj.sistemi.entities.Municipality;

@Repository
@SuppressWarnings("unchecked")
public class DepartmentDAO {

	
	@PersistenceContext
	EntityManager em;
	
	
	public Department getRootDepartment()
	{
		
		List<Department> list = em.createQuery("FROM Department d WHERE d.status=:st AND d.structure.active=:sa AND d.parent IS NULL")
				.setParameter("st", IStatus.ACTIVE).setParameter("sa", IStatus.ACTIVE)
				.getResultList();
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}
	
	
	public Department getMunicipalityRootDepartment(Integer munId)
	{
		
		List<Department> list = em.createQuery("FROM Department d WHERE d.status=:st AND d.structure.active=:sa AND d.municipalityId=:munId "
				+ "AND d.parent.regionId=:regId")
				.setParameter("st", IStatus.ACTIVE).setParameter("sa", IStatus.ACTIVE)
				.setParameter("munId", munId).setParameter("regId", (em.find(Municipality.class, munId)).getRegion().getId())
				.getResultList();
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}
	
	
	
	public List<Department> getChildDepartments(Integer deptId)
	{
		return em.createQuery("FROM Department d WHERE d.status=:st AND d.parent.id=:did")
				.setParameter("st", IStatus.ACTIVE).setParameter("did", deptId)
				.getResultList();
	}
	
	public List<DepartmentPosition> getDepartmentPositions(Integer deptId)
	{
		return em.createQuery("FROM DepartmentPosition p WHERE p.status=:st AND p.department.id=:did")
				.setParameter("st", IStatus.ACTIVE).setParameter("did", deptId)
				.getResultList();
	}
	
	public List<DepartmentFunction> listDepartmentFunctions()
	{
		return em.createQuery("FROM DepartmentFunction f WHERE f.status=:st")
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}

	public List<DepartmentPosition> getDepartmentPositionsHistory(Integer deptId) {
		return em.createQuery("FROM DepartmentPosition p WHERE p.department.id=:did order by p.startDate desc")
				.setParameter("did", deptId)
				.getResultList();
	}


	public List<DepartmentPosition> getPersonParyHistory(String nid) {
		return em.createQuery("FROM DepartmentPosition p WHERE p.person.nid=:nid order by p.startDate desc")
				.setParameter("nid", nid)
				.getResultList();
	}
	
}
