package br.edu.univas.lab6.smartpoll.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.entity.Result;

public class ResultDAO extends GenericDAO<Long, Result> {

	public ResultDAO(EntityManager entityManager) {
		super(entityManager);
	}

	public Long countVotesPerDate(Question question, int month, int day) {
		Query query = entityManager
				.createNamedQuery("Result.countVotesPerDate");
		query.setParameter("month", month);
		query.setParameter("day", day);
		query.setParameter("question", question);

		try {
			return (Long) query.getSingleResult();
		} catch (NoResultException e) {
			return 0L;
		}
	}

}
