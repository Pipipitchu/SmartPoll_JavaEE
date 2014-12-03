package br.edu.univas.lab6.smartpoll.beans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.univas.lab6.smartpoll.entity.Answer;
import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.QuestionService;
import br.edu.univas.lab6.smartpoll.service.UserService;

@ManagedBean(name = "insert")
@ViewScoped
public class InsertPollBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Question question;

	SimpleEntityManager simpleEntityManager = new SimpleEntityManager();
	QuestionService questionService = new QuestionService(simpleEntityManager);
	UserService userService = new UserService(simpleEntityManager);

	private int qtdAnswers = 4;

	public InsertPollBean() {
		question = new Question();
		createAnswers(question);
	}

	public Question getQuestion() {
		return question;

	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void insertQuestion() {
		question.setDateQuestion(new Date());
		question.setUser(userService.findById((long) 1));
		questionService.save(question);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Successfully registered Poll!", ""));

		question = new Question();
		createAnswers(question);
	}

	private void createAnswers(Question question) {
		for (int i = 0; i < qtdAnswers; i++) {
			question.addAnswer(new Answer());
		}
	}

}
