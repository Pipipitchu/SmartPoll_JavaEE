package br.edu.univas.lab6.smartpoll.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.QuestionService;

@ManagedBean(name = "listpoll")
@ViewScoped
public class ListPollBean implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Question> questions = new ArrayList<Question>();

	SimpleEntityManager simpleEntityManager = new SimpleEntityManager();
	QuestionService service = new QuestionService(simpleEntityManager);

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Question> getAllQuestions() {
		questions = service.findAll();
		// simpleEntityManager.close();
		return questions;
	}

	public void removeQuestion(Question question) {
		service.delete(question);
	}
}
