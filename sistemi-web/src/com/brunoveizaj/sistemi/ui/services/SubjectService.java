package com.brunoveizaj.sistemi.ui.services;

import java.util.List;

import com.brunoveizaj.sistemi.ui.api.clients.SubjectClient;
import com.brunoveizaj.sistemi.ui.forms.SubjectSx;
import com.brunoveizaj.sistemi.ui.models.SubjectDTO;



public class SubjectService {
	
	
	public List<SubjectDTO> searchSubjects(SubjectSx req)
	{
		return new SubjectClient().searchSubjects(req);
	}
	
	public SubjectDTO getSubjectByNipt(String nipt)
	{
		return new SubjectClient().getSubjectByNipt(nipt);
	}
	

}
