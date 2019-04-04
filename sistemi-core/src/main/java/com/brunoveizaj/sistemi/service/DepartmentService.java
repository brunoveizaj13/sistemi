package com.brunoveizaj.sistemi.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brunoveizaj.sistemi.constants.IStatus;
import com.brunoveizaj.sistemi.dao.CrudDAO;
import com.brunoveizaj.sistemi.dao.DepartmentDAO;
import com.brunoveizaj.sistemi.entities.Department;
import com.brunoveizaj.sistemi.entities.DepartmentFunction;
import com.brunoveizaj.sistemi.entities.DepartmentPosition;
import com.brunoveizaj.sistemi.entities.Person;
import com.brunoveizaj.sistemi.exceptions.ValidationException;
import com.brunoveizaj.sistemi.forms.DepartmentForm;
import com.brunoveizaj.sistemi.forms.DepartmentPositionForm;
import com.brunoveizaj.sistemi.utils.StringUtil;

@Service
public class DepartmentService {

	
	@Autowired CrudDAO crudDAO;
	@Autowired DepartmentDAO departmentDAO;
	
	
	
	public Department getRootDepartment()
	{
		return departmentDAO.getRootDepartment();
	}
	
	public Department getMunicipalityRootDepartment(Integer munId)
	{
		return departmentDAO.getMunicipalityRootDepartment(munId);
	}
	
	public List<Department> getChildDepartments(Integer deptId)
	{
		return departmentDAO.getChildDepartments(deptId);
	}
	
	public List<DepartmentPosition> getDepartmentPositions(Integer deptId)
	{
		return departmentDAO.getDepartmentPositions(deptId);
	}
	
	public List<DepartmentFunction> listDepartmentFunctions()
	{
		return departmentDAO.listDepartmentFunctions();
	}
	
	@Transactional
	public Department registerDepartment(DepartmentForm form, String uname)
	{
		if(!StringUtil.isValid(form.getName()))
		{
			throw new ValidationException("Ploteso emrin e departamentit");
		}
		if(form.getArea() == null)
		{
			throw new ValidationException("Plotesoni zonen");
		}
		if(form.getParentId() == null)
		{
			throw new ValidationException("Stuktura prind e papercaktuar");
		}
		if(form.getArea() == 2 && form.getRegionId() == null)
		{
			throw new ValidationException("Zgjidhni qarkun");
		}
		if(form.getArea() == 3 && form.getMunicipalityId() == null)
		{
			throw new ValidationException("Zgjidhni bashkine");
		}
		if(form.getArea() == 4 && form.getUnitId() == null)
		{
			throw new ValidationException("Zgjidhni njesine");
		}
		if(form.getArea() == 5 && form.getQvId() == null)
		{
			throw new ValidationException("Zgjidhni qv-ne");
		}
		
		
		Department d = new Department();
		d.setArea(form.getArea());
		d.setName(form.getName());
		d.setExpanded(form.isExpanded()?IStatus.ACTIVE:IStatus.NOT_ACTIVE);
		d.setColor((form.getColor()==null)?null:("#"+form.getColor()));
		d.setSinglePosition(form.isSinglePosition()?IStatus.ACTIVE:IStatus.NOT_ACTIVE);
		if(form.getArea() == 2) {d.setRegionId(form.getRegionId());}
		if(form.getArea() == 3) {d.setMunicipalityId(form.getMunicipalityId());}
		if(form.getArea() == 4) {d.setUnitId(form.getUnitId());}
		if(form.getArea() == 5) {d.setQvId(form.getQvId());d.setFraction(form.getFraction());}
		d.setStatus(IStatus.ACTIVE);
		Department parent = crudDAO.findById(Department.class, form.getParentId());
		d.setParent(parent);
		d.setStructure(parent.getStructure());
		
		return crudDAO.create(d);
		
	}
	
	@Transactional
	public DepartmentPosition registerDepartmentPosition(DepartmentPositionForm form, String uname)
	{
		
		if(!StringUtil.isValid(form.getPersonNid()))
		{
			throw new ValidationException("Zgjidhni personin");
		}
		if(form.getDepartmentId() == null)
		{
			throw new ValidationException("Zgjidhni departamentin");
		}
		if(form.getFunctionId() == null)
		{
			throw new ValidationException("Zgjidhni funsionin");
		}
		if(form.getStartDate() == null)
		{
			throw new ValidationException("Zgjidhni daten e fillimit");
		}
		
		Department d = crudDAO.findById(Department.class, form.getDepartmentId());
		if(d.getSinglePosition() == IStatus.ACTIVE)
		{
		   List<DepartmentPosition> list = departmentDAO.getDepartmentPositions(form.getDepartmentId());
		   if(list != null && !list.isEmpty())
		   {
			   DepartmentPosition dp = list.get(0);
			   dp.setEndDate(Calendar.getInstance().getTime());
			   dp.setStatus(IStatus.NOT_ACTIVE);
			   crudDAO.update(dp);
		   }
		}
		
		DepartmentPosition dp = new DepartmentPosition();
		dp.setDepartment(d);
		dp.setFunction(crudDAO.findById(DepartmentFunction.class, form.getFunctionId()));
		dp.setPerson(crudDAO.findById(Person.class, form.getPersonNid()));
		dp.setStartDate(form.getStartDate());
		dp.setStatus(IStatus.ACTIVE);
		
		return crudDAO.create(dp);
		
	}

	public List<DepartmentPosition> getDepartmentPositionsHistory(Integer deptId) {
		return departmentDAO.getDepartmentPositionsHistory(deptId);
	}

	public List<DepartmentPosition> getPersonParyHistory(String nid) {
		return departmentDAO.getPersonParyHistory(nid);
	}
	
	
	
	
	
	
	
}
