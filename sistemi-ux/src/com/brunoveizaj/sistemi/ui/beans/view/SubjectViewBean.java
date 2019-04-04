package com.brunoveizaj.sistemi.ui.beans.view;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.brunoveizaj.sistemi.ui.beans.application.CacheBean;
import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.models.EmployeeDTO;
import com.brunoveizaj.sistemi.ui.models.SubjectDTO;
import com.brunoveizaj.sistemi.ui.services.EmployeeService;
import com.brunoveizaj.sistemi.ui.services.SubjectService;
import com.brunoveizaj.sistemi.ui.utils.Messages;
import com.brunoveizaj.sistemi.ui.utils.StringUtil;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class SubjectViewBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty("#{navBean}")
	NavBean nav;
	
	@ManagedProperty("#{cacheBean}")
	CacheBean cache;
	
	SubjectDTO subject;
	List<EmployeeDTO> employees;
	
	Integer year;
	Integer month;
	
	
	String selectedYearMonth;
	
	
	
	
	public void init()
	{
		String nipt = nav.getParam("nipt");	
		
		loadSubjectRaport(nipt);
	}
	
	public void loadSubjectRaport(String nipt)
	{
		try {
			this.subject = new SubjectService().getSubjectByNipt(nipt);
			if(subject == null)
			{
				subject = new SubjectDTO();
				subject.setNipt(nipt);
			}
			loadEmployees();
		}catch(Exception a) {
			Messages.throwFacesMessage(a);
		}
		
	}
	
	public void loadEmployees()
	{
		
		if(StringUtil.isValid(selectedYearMonth))
		{
			this.year = Integer.valueOf(selectedYearMonth.substring(0, 4));
			this.month = Integer.valueOf(selectedYearMonth.substring(5));
		}
		
		if(year == null || month == null)
		{
			if(cache.getMonthYears() != null && !cache.getMonthYears().isEmpty())
			{
				this.year = cache.getMonthYears().get(0).getYear();
				this.month = cache.getMonthYears().get(0).getMonth();
				
			}			
			else
			{
				year = Calendar.getInstance().get(Calendar.YEAR);
				month = Calendar.getInstance().get(Calendar.MONTH);// January = 0; na duhet previous month
				if(month == 0) month = 12;
			}
			
		}
		
		this.selectedYearMonth = year+","+month;
		
		try {
			this.employees = new EmployeeService().getEmployees(subject.getNipt(), year, month) ;
			if(employees == null || employees.isEmpty())
			{
				Messages.throwFacesMessage("Nuk ka listëpagesa per periudhen: "+month+"/"+year, 2);
			}
			
		}catch(Exception a) {
			Messages.throwFacesMessage(a);
			this.employees = null;
		}
		
	}

}
