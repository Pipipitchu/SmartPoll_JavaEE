package br.edu.univas.lab6.smartpoll.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.QuestionService;

@ManagedBean(name = "insert")
@SessionScoped
public class InsertPollBean {
	
	private Question question = new Question();
	
	SimpleEntityManager simpleEntityManager = new SimpleEntityManager();
	QuestionService service = new QuestionService(simpleEntityManager);
	
	public Question getQuestion() {
		return question;
		
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public void insertQuestion(Question question) {
		service.save(question);
	}
}
