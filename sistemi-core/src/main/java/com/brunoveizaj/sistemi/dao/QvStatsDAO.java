package com.brunoveizaj.sistemi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.brunoveizaj.sistemi.constants.IPatronageType;
import com.brunoveizaj.sistemi.constants.IStatus;
import com.brunoveizaj.sistemi.entities.Person;

@Repository
@SuppressWarnings("unchecked")
public class QvStatsDAO {

	@PersistenceContext
	EntityManager em;
		
	
	public List<Person> persons(Integer qvId)
	{
		return em.createQuery("From Person p join fetch p.details d where p.qv.id=:qvid ORDER BY p.fraction,p.votingNo")
				.setParameter("qvid", qvId)
				.getResultList();
	}
	
	public List<Person> personsNotInPatronage(Integer qvId)
	{
		return em.createQuery("From Person p join fetch p.details d where p.qv.id=:qvid AND p NOT IN (SELECT pp.person FROM PatronagePerson pp WHERE pp.status=:ppst AND pp.patronage.patronageType.id=:pid AND pp.person.qv.id=:pqvid) ORDER BY p.fraction,p.votingNo")
				.setParameter("qvid", qvId)
				.setParameter("pid", IPatronageType.PERSON)
				.setParameter("ppst", IStatus.ACTIVE)
				.setParameter("pqvid", qvId)
				.getResultList();
	}
	
	public List<Person> personsInPatronage(Integer qvId, Integer type)
	{
		return em.createQuery("SELECT DISTINCT(pp.person) From PatronagePerson pp where pp.status=:st AND pp.patronage.patronageType.id=:pid AND pp.person.qv.id=:qvid ORDER BY pp.person.fraction,pp.person.votingNo")
				.setParameter("qvid", qvId)
				.setParameter("pid", type)
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<Person> patronages(Integer qvId, Integer type)
	{
		return em.createQuery("SELECT DISTINCT(pp.patronage.person) From PatronagePerson pp where pp.status=:st AND pp.patronage.patronageType.id=:pid AND pp.patronage.person.qv.id=:qvid ORDER BY pp.patronage.person.fraction,pp.patronage.person.votingNo")
				.setParameter("qvid", qvId)
				.setParameter("pid", type)
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<Person> firstTimeVoters(Integer qvId)
	{
		return em.createQuery("From Person p join fetch p.details d where p.qv.id=:qvid AND d.firstTimeVoterStatus=:st ORDER BY p.fraction,p.votingNo")
				.setParameter("qvid", qvId)
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}
	
	public List<Person> notVotingLastElections(Integer qvId)
	{
		return em.createQuery("From Person p join fetch p.details d where p.qv.id=:qvid AND d.voting2017Status=:st ORDER BY p.fraction,p.votingNo")
				.setParameter("qvid", qvId)
				.setParameter("st", IStatus.ACTIVE)
				.getResultList();
	}
	
	
	
	
}
