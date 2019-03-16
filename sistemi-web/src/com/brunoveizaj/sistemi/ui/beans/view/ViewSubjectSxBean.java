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

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class ViewSubjectSxBean implements Serializable {
	
	
	@ManagedProperty(value = "#{navBean}")
	NavBean nav;
	
    SubjectSx req;
	
	List<SubjectDTO> subjects;
	SubjectDTO subject;
	
	
	@PostConstruct
	public void load()
	{
		init();
	}
	
	private void init()
	{
		this.req = new SubjectSx();
		this.subjects = null;
		this.subject = null;
	}
	
	public void search()
	{
		try {
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
