package com.brunoveizaj.sistemi.ui.services;

import java.util.List;

import com.brunoveizaj.sistemi.ui.api.clients.DepartmentClient;
import com.brunoveizaj.sistemi.ui.forms.DepartmentForm;
import com.brunoveizaj.sistemi.ui.forms.DepartmentPositionForm;
import com.brunoveizaj.sistemi.ui.models.DepartmentDTO;
import com.brunoveizaj.sistemi.ui.models.DepartmentFunctionDTO;
import com.brunoveizaj.sistemi.ui.models.DepartmentPositionDTO;

public class DepartmentService {
	
	
	public DepartmentDTO registerDepartment(DepartmentForm form)
	{
		return new DepartmentClient().registerDepartment(form);
	}
	
	public List<DepartmentFunctionDTO> listDepartmentFunctions()
	{
		return new DepartmentClient().listDepartmentFunctions();
	}
	
	public List<DepartmentPositionDTO> getDepartmentPositions(Integer deptId)
	{
		return new DepartmentClient().getDepartmentPositions(deptId);
	}
	
	public List<DepartmentDTO> getChildDepartments(Integer deptId)
	{
		return new DepartmentClient().getChildDepartments(deptId);
	}
	
	public DepartmentDTO getRootDepartment()
	{
		return new DepartmentClient().getRootDepartment();
	}
	
	public DepartmentDTO getMunicipalityRootDepartment(Integer munId)
	{
		return new DepartmentClient().getMunicipalityRootDepartment(munId);
	}

	public DepartmentPositionDTO getDepartmentSinglePosition(Integer deptId) {
		List<DepartmentPositionDTO> list = getDepartmentPositions(deptId);
		
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}

	public DepartmentPositionDTO registerDepartmentPosition(DepartmentPositionForm form) {
		return new DepartmentClient().registerDepartmentPosition(form);
	}

	public List<DepartmentPositionDTO> getDepartmentPositionsHistory(Integer deptId) {
		return new DepartmentClient().getDepartmentPositionsHistory(deptId);
	}

	public List<DepartmentPositionDTO> getPersonParyHistory(String nid) {
		return new DepartmentClient().getPersonParyHistory(nid);
	}
	
	
	

}
