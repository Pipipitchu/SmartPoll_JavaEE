package br.edu.univas.lab6.smartpoll.dao;

import javax.persistence.EntityManager;

import br.edu.univas.lab6.smartpoll.entity.Users;

public class UsersDAO extends GenericDAO<Long, Users>{

	public UsersDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
	
}
