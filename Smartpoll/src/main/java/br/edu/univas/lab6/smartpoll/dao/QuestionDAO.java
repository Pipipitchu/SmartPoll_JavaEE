package br.edu.univas.lab6.smartpoll.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.univas.lab6.smartpoll.entity.Question;

public class QuestionDAO extends GenericDAO<Long, Question> {

	public QuestionDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	public List<Question> findByMonthVote(Integer month) {
		List<Question> questions = new ArrayList<Question>();
		try {
			questions = (List<Question>) entityManager
					.createNamedQuery("Question.findByMonthVote")
					.setParameter("month", month).getResultList();

			// remove registers duplicated
			for (int i = 0; i < questions.size(); i++) {
				Object a = questions.get(i);
				for (int j = i + 1; j < questions.size(); j++) {
					Object b = questions.get(j);
					if (a.equals(b)) {
						questions.remove(j);
						j--;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}
}
