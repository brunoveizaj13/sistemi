package com.brunoveizaj.sistemi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunoveizaj.sistemi.dao.QvStatsDAO;
import com.brunoveizaj.sistemi.entities.Person;

@Service
public class QvStatsService {
	
	@Autowired QvStatsDAO qvStatsDAO;
	
	public List<Person> persons(Integer qvId, String uname)
	{
		return qvStatsDAO.persons(qvId);
	}
	
	public List<Person> personsNotInPatronage(Integer qvId, String uname)
	{
		return qvStatsDAO.personsNotInPatronage(qvId);
	}
	
	public List<Person> personsInPatronage(Integer qvId, Integer type, String uname)
	{
		return qvStatsDAO.personsInPatronage(qvId, type);
	}
	
	public List<Person> patronages(Integer qvId, Integer type, String uname)
	{
		return qvStatsDAO.patronages(qvId, type);
	}
	
	public List<Person> firstTimeVoters(Integer qvId, String uname)
	{
		return qvStatsDAO.firstTimeVoters(qvId);
	}
	
	public List<Person> notVotingLastElections(Integer qvId, String uname)
	{
		return qvStatsDAO.notVotingLastElections(qvId);
	}
	
	

}
