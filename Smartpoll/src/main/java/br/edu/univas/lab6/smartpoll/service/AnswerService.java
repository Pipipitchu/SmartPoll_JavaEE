package br.edu.univas.lab6.smartpoll.service;

import java.io.Serializable;
import java.util.List;

import br.edu.univas.lab6.smartpoll.dao.AnswerDAO;
import br.edu.univas.lab6.smartpoll.entity.Answer;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;

public class AnswerService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AnswerDAO dao;

	private SimpleEntityManager simpleEntityManager;

	public AnswerService(SimpleEntityManager simpleEntityManager) {
		this.simpleEntityManager = simpleEntityManager;
		dao = new AnswerDAO(simpleEntityManager.getEntityManager());
	}

	public void save(Answer answer) {
		try {
			simpleEntityManager.beginTransaction();
			dao.save(answer);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
	}

	public void delete(Answer answer) {
		try {
			simpleEntityManager.beginTransaction();
			dao.delete(answer.getId(), Answer.class);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
	}

	public List<Answer> findAll() {
		return dao.findAll();
	}
}
