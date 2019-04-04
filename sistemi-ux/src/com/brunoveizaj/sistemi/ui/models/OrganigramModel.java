package com.brunoveizaj.sistemi.ui.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrganigramModel implements Serializable {
	
	
	public static final int DEPARTMENT = 1;
	public static final int POSITION = 2;
	

	Integer departmentId;
	Integer positionId;
	String nid;
	String name;
	String function;
	String departmentName;
	boolean singlePosition;
	boolean expanded;
	String color;
	int type; //1 dept, 2 position
	
	public OrganigramModel(DepartmentDTO d)
	{
		this.departmentId = d.getId();
		this.departmentName = d.getName();
		this.singlePosition = d.isSinglePosition();
		this.expanded = d.isExpanded();
		this.color = d.getColor();
		this.type = DEPARTMENT;
	}
	
	public OrganigramModel(DepartmentPositionDTO d)
	{
		this.positionId = d.getId();
		this.departmentId = d.getDepartment().getId();
		this.singlePosition = d.getDepartment().isSinglePosition();
		this.nid = d.getPerson().getNid();
		this.name = d.getPerson().getName()+" "+d.getPerson().getSurname();
		this.departmentName = d.getDepartment().getName();
		this.function = d.getFunction().getName();
		this.expanded = d.getDepartment().isExpanded();
		this.color = d.getDepartment().getColor();
		this.type = POSITION;
	}
	
	
	public OrganigramModel() {
		// TODO Auto-generated constructor stub
	}

	public String display()
	{
		if(this.type == DEPARTMENT)
		{
			return (this.singlePosition?"<span style='color:#ff1300'>Pa përcaktuar</span><br/>":"")+this.departmentName;
		}
		else
		{
			return "<strong>"+name+"</strong><br/>"+departmentName;
		}
	}
	
	
}
