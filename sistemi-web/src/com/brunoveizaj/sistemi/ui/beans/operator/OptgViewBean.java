package com.brunoveizaj.sistemi.ui.beans.operator;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.brunoveizaj.sistemi.ui.beans.application.NavBean;
import com.brunoveizaj.sistemi.ui.forms.PatronageForm;
import com.brunoveizaj.sistemi.ui.forms.PatronagePersonModelForm;
import com.brunoveizaj.sistemi.ui.forms.PersonSx;
import com.brunoveizaj.sistemi.ui.models.PatronageDTO;
import com.brunoveizaj.sistemi.ui.models.PatronagePersonDTO;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter @Setter
public class OptgViewBean implements Serializable {
	
	
	
	@ManagedProperty(value="#{navBean}")
	NavBean nav;
	
	
	PatronageForm patronageForm;
	PatronageDTO selectedPatronage;
	List<PatronagePersonDTO> personsInPatronage;
	
	PersonSx req;
	List<PatronagePersonModelForm> personForms;
	
	
	@PostConstruct
	public void load()
	{}
	
	
	

}
