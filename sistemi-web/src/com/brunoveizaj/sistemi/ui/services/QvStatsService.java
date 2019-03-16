package com.brunoveizaj.sistemi.ui.services;

import java.util.List;

import com.brunoveizaj.sistemi.ui.api.clients.QvStatsClient;
import com.brunoveizaj.sistemi.ui.models.PersonDTO;

public class QvStatsService {
	
	public List<PersonDTO> persons(Integer qvId) {
		return new QvStatsClient().persons(qvId);
	}
	
	public List<PersonDTO> personsNotInPatronage(Integer qvId) {
		return new QvStatsClient().personsNotInPatronage(qvId);
	}
	
	public List<PersonDTO> personsInPatronage(Integer qvId, Integer type) {
		return new QvStatsClient().personsInPatronage(qvId, type);
	}
	
	public List<PersonDTO> patronages(Integer qvId, Integer type) {
		return new QvStatsClient().patronages(qvId, type);
	}
	
	public List<PersonDTO> firstTimeVoters(Integer qvId) {
		return new QvStatsClient().firstTimeVoters(qvId);
	}
	
	public List<PersonDTO> notVotingLastElections(Integer qvId) {
		return new QvStatsClient().notVotingLastElections(qvId);
	}
	
	
}
