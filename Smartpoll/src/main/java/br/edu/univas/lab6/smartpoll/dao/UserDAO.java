package br.edu.univas.lab6.smartpoll.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.univas.lab6.smartpoll.entity.User;

public class UserDAO extends GenericDAO<Long, User>{

	public UserDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	
	public User findByEmailPassword(String email, String password) {
		Query query = entityManager.createNamedQuery("User.control").setParameter("email", email).setParameter("password", password);
		try {
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			// TODO: handle exception
			return null;
		}
	}
		
}
