package br.edu.univas.lab6.smartpoll.dao;

import javax.persistence.EntityManager;

import br.edu.univas.lab6.smartpoll.entity.Result;

public class ResultDAO extends GenericDAO<Long, Result> {

	public ResultDAO(EntityManager entityManager) {
		super(entityManager);
	}

}
