package br.edu.univas.lab6.smartpoll.dao;

import javax.persistence.EntityManager;

import br.edu.univas.lab6.smartpoll.entity.Answer;

public class AnswerDAO extends GenericDAO<Long, Answer>{

	public AnswerDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}
}
