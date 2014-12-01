package br.edu.univas.lab6.smartpoll.beans;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.univas.lab6.smartpoll.entity.Answer;
import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.entity.Result;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.QuestionService;
import br.edu.univas.lab6.smartpoll.service.ResultService;

@ManagedBean(name = "votation")
@ViewScoped
public class VotationBean {

	private static final String INITIAL_MESSAGE = "Choose one poll to participate.";

	private String subtitle = INITIAL_MESSAGE;

	private boolean showQuestions = Boolean.TRUE;
	private boolean showAnswers = Boolean.FALSE;
	private boolean showResult = Boolean.FALSE;

	private List<Question> questions;
	private List<Answer> answers;

	private Question question;
	private Answer answer;

	SimpleEntityManager simpleEntityManager = new SimpleEntityManager();

	QuestionService questionService = new QuestionService(simpleEntityManager);
	ResultService resultService = new ResultService(simpleEntityManager);

	public List<Question> getAllQuestions() {
		questions = questionService.findAll();
		return questions;
	}

	public void updateAnswers(Question question) {
		setQuestion(question);
		setAnswers(question.getAnswers());
		setSubtitle(question.getQuestion());
		setShowQuestions(Boolean.FALSE);
		setShowAnswers(Boolean.TRUE);
	}

	public void insertVote(Answer answer) {
		Result result = new Result();
		result.setQuestion(question);
		result.setAnswer(answer);
		result.setDateVote(new Date());
		resultService.save(result);
		setShowAnswers(Boolean.FALSE);
		setShowResult(Boolean.TRUE);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Thanks for you vote!", ""));
	}

	public void backToQuestions() {
		setSubtitle(INITIAL_MESSAGE);
		setShowQuestions(Boolean.TRUE);
		setShowAnswers(Boolean.FALSE);
		setShowResult(Boolean.FALSE);
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public boolean getShowQuestions() {
		return showQuestions;
	}

	public void setShowQuestions(boolean showQuestions) {
		this.showQuestions = showQuestions;
	}

	public boolean getShowAnswers() {
		return showAnswers;
	}

	public void setShowAnswers(boolean showAnswers) {
		this.showAnswers = showAnswers;
	}

	public boolean getShowResult() {
		return showResult;
	}

	public void setShowResult(boolean showResult) {
		this.showResult = showResult;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
}