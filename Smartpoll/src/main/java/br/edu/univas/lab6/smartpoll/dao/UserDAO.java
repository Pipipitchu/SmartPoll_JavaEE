package br.edu.univas.lab6.smartpoll.dao;

import javax.persistence.EntityManager;

import br.edu.univas.lab6.smartpoll.entity.User;

public class UserDAO extends GenericDAO<Long, User>{

	public UserDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
}
