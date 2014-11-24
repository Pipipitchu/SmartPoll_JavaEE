package br.edu.univas.lab6.smartpoll.dao;

import javax.persistence.EntityManager;

import br.edu.univas.lab6.smartpoll.entity.Question;

public class QuestionDAO extends GenericDAO<Long, Question> {

	public QuestionDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
