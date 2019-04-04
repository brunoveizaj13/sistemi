package com.brunoveizaj.sistemi.ui.beans.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.forms.SubjectSx;
import com.brunoveizaj.sistemi.ui.models.Param;
import com.brunoveizaj.sistemi.ui.models.SubjectDTO;
import com.brunoveizaj.sistemi.ui.services.SubjectService;
import com.brunoveizaj.sistemi.ui.utils.Messages;
import com.brunoveizaj.sistemi.ui.utils.StringUtil;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class ViewSubjectSxBean implements Serializable {
	
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
    SubjectSx req;
	Boolean onlyAdministrate;
    
	List<SubjectDTO> subjects;
	SubjectDTO subject;
	
	boolean renderBack = false;
	
	@PostConstruct
	public void load()
	{
		//init();
	}
	
	public void init()
	{
		this.req = new SubjectSx();
		this.subjects = null;
		this.subject = null;
		
        String municipId = nav.getParam("municip_id");
        String onlyAdm = nav.getParam("administrate");
        if(StringUtil.isValid(onlyAdm))
        {
        	this.onlyAdministrate = Boolean.valueOf(onlyAdm);       	
        }
    	if(StringUtil.isValid(municipId))
    	{
    	     req.setMunicipalityId(Integer.valueOf(municipId));
    	     if(onlyAdministrate == null || !onlyAdministrate)
    	     {
    	         req.setMaxResult(1000);
    	     }
    	     search();
    	     this.renderBack = true;
    	}
    	
	}
	
	public void search()
	{
		try {
			
			if(onlyAdministrate != null && onlyAdministrate)
			{
				this.req.setLegalForm("ENT PUBLIK");
				this.req.setNotName("%BASHKIA% %");
				this.req.setSkipRaport(false);
			}
			
			this.subjects = new SubjectService().searchSubjects(req);
			if(subjects == null || subjects.isEmpty())
			{
				Messages.throwFacesMessage("Nuk u gjet asnje subjekt", 2);
			}
		}catch(Exception a) {
			Messages.throwFacesMessage(a);
			this.subjects = null;
		}
		this.subject = null;
	}
	
	public void clear()
	{
		init();
	}
	
	public void onSubjectSelect()
	{
		List<Param> params = new ArrayList<>();
		params.add(new Param("nipt",subject.getNipt()));
		nav.navigate("subject_view",params);
	}
	
	
	
	
}
