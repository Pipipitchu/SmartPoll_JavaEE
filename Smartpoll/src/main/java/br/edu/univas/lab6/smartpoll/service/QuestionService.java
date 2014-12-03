package br.edu.univas.lab6.smartpoll.service;

import java.io.Serializable;
import java.util.List;

import br.edu.univas.lab6.smartpoll.dao.AnswerDAO;
import br.edu.univas.lab6.smartpoll.dao.QuestionDAO;
import br.edu.univas.lab6.smartpoll.entity.Answer;
import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;

public class QuestionService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private QuestionDAO dao;
	private AnswerDAO answerDAO;

	private SimpleEntityManager simpleEntityManager;

	public QuestionService(SimpleEntityManager simpleEntityManager) {
		this.simpleEntityManager = simpleEntityManager;
		dao = new QuestionDAO(simpleEntityManager.getEntityManager());
		answerDAO = new AnswerDAO(simpleEntityManager.getEntityManager());
	}

	public void save(Question question) {
		try {
			simpleEntityManager.beginTransaction();
			dao.save(question);
			for (Answer answer : question.getAnswers()) {
				answer.setQuestion(question);
				answerDAO.save(answer);
			}
			simpleEntityManager.commit();

		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
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
	}

	public List<Question> findAll() {
		return dao.findAll();
	}
}
