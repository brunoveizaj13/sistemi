package com.brunoveizaj.sistemi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunoveizaj.sistemi.dao.SubjectDAO;
import com.brunoveizaj.sistemi.entities.Subject;
import com.brunoveizaj.sistemi.forms.SubjectSx;
import com.brunoveizaj.sistemi.utils.StringUtil;

@Service
public class SubjectService {
	
	
	@Autowired SubjectDAO subjectDAO;
	
	
	public List<Subject> searchSubjects(SubjectSx req)
	{
		return subjectDAO.searchSubject(req);
	}
	
	
	public Subject getSubjectByNipt(String nipt)
	{
		if(!StringUtil.isValid(nipt)) return null;
		
		SubjectSx request = new SubjectSx();
		request.setNipt(nipt);
		
		List<Subject> subjects = subjectDAO.searchSubject(request);
		
		if(subjects != null && !subjects.isEmpty())
		{
			return subjects.get(0);
		}
		
		return null;

	}
}