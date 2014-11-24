package br.edu.univas.lab6.smartpoll.service;

import java.util.List;

import br.edu.univas.lab6.smartpoll.dao.QuestionDAO;
import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;

public class QuestionService {

	private QuestionDAO dao;

	private SimpleEntityManager simpleEntityManager;

	public QuestionService(SimpleEntityManager simpleEntityManager) {
		this.simpleEntityManager = simpleEntityManager;
		dao = new QuestionDAO(simpleEntityManager.getEntityManager());
	}

	public void save(Question question) {
		try {
			simpleEntityManager.beginTransaction();
			dao.save(question);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
		simpleEntityManager.close();
	}

	public void delete(Question question) {
		try {
			simpleEntityManager.beginTransaction();
			dao.delete(question.getId(), Question.class);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
		simpleEntityManager.close();
	}

	public List<Question> findAll() {
		return dao.findAll();
	}
}
