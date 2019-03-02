package com.brunoveizaj.sistemi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.brunoveizaj.sistemi.entities.User;

@Repository
@SuppressWarnings("unchecked")
public class UserDAO {
	
	
	@PersistenceContext
	EntityManager em;
	
	
	public User findByUsername(String username)
	{
		
		List<User> list = em.createQuery("FROM User u where u.username=:uname")
				.setParameter("uname", username)
				.getResultList();
		
		if(list!=null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}
	
	
	
	public List<User> listUsers()
	{
		return em.createQuery("FROM User u ORDER BY u.id DESC").getResultList();
	}

}
