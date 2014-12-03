package br.edu.univas.lab6.smartpoll.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.chart.PieChartModel;

import br.edu.univas.lab6.smartpoll.entity.Answer;
import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.entity.Result;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.QuestionService;
import br.edu.univas.lab6.smartpoll.service.ResultService;

@ManagedBean(name = "votation")
@ViewScoped
public class VotationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String INITIAL_MESSAGE = "Choose one poll to participate.";

	private String subtitle = INITIAL_MESSAGE;

	private boolean showPanelQuestions = Boolean.TRUE;
	private boolean showPanelAnswers = Boolean.FALSE;
	private boolean showPanelResult = Boolean.FALSE;

	private List<Question> questions;
	private List<Answer> answers;

	private Question question;
	private Answer answer;

	SimpleEntityManager simpleEntityManager = new SimpleEntityManager();

	QuestionService questionService = new QuestionService(simpleEntityManager);
	ResultService resultService = new ResultService(simpleEntityManager);

	private PieChartModel pieChart = new PieChartModel();

	public List<Question> getAllQuestions() {
		questions = new ArrayList<Question>();
		List<Question> aux = questionService.findAll();

		Date now = new Date();
		for (Question question : aux) {
			if (question.getExpirationDate().after(now)) {
				questions.add(question);
			}
		}
		return questions;
	}

	public void showAnswers(Question question) {
		if (validateVote(question.getId())) {
			setQuestion(question);
			setAnswers(question.getAnswers());
			setSubtitle(question.getQuestion());
			setShowPanelQuestions(Boolean.FALSE);
			setShowPanelAnswers(Boolean.TRUE);
		} else {
			createPieChart(question);

			setShowPanelQuestions(Boolean.FALSE);
			showMessage(FacesMessage.SEVERITY_ERROR,
					"You already participated in this poll!");
			setShowPanelResult(Boolean.TRUE);
		}
	}

	public void insertVote(Answer answer) {
		Result result = new Result();
		result.setQuestion(question);
		result.setAnswer(answer);
		result.setDateVote(new Date());
		createCookie(String.valueOf(question.getId()),
				String.valueOf(question.getId()), 86400);
		resultService.save(result);

		createPieChart(question);
		setShowPanelAnswers(Boolean.FALSE);
		setShowPanelResult(Boolean.TRUE);

		showMessage(FacesMessage.SEVERITY_INFO, "Thanks for you vote!");
	}

	public void backToQuestions() {
		setSubtitle(INITIAL_MESSAGE);
		setShowPanelQuestions(Boolean.TRUE);
		setShowPanelAnswers(Boolean.FALSE);
		setShowPanelResult(Boolean.FALSE);
	}

	private void createCookie(String name, String value, int maxAge) {
		FacesContext context = FacesContext.getCurrentInstance();
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		((HttpServletResponse) context.getExternalContext().getResponse())
				.addCookie(cookie);
	}

	private Boolean validateVote(Long idQuestion) {

		String nameCookie = String.valueOf(idQuestion);

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext
				.getExternalContext().getRequest();

		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies) {
			if (cookie.getName().trim().equalsIgnoreCase(nameCookie)) {
				return false;
			}
		}
		return true;
	}

	public void createPieChart(Question question) {
		pieChart = new PieChartModel();

		for (Answer answer : question.getAnswers()) {
			pieChart.set(answer.getAnswer(), answer.getResults().size());
		}

		pieChart.setShowDataLabels(true);
		pieChart.setTitle(question.getTitle());
		pieChart.setLegendPosition("w");
	}

	private void showMessage(Severity severity, String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, message, ""));
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

	public boolean getShowPanelQuestions() {
		return showPanelQuestions;
	}

	public void setShowPanelQuestions(boolean showQuestions) {
		this.showPanelQuestions = showQuestions;
	}

	public boolean getShowPanelAnswers() {
		return showPanelAnswers;
	}

	public void setShowPanelAnswers(boolean showAnswers) {
		this.showPanelAnswers = showAnswers;
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

	public boolean getShowPanelResult() {
		return showPanelResult;
	}

	public void setShowPanelResult(boolean showPanelResult) {
		this.showPanelResult = showPanelResult;
	}

	public PieChartModel getPieChart() {
		return pieChart;
	}

	public void setPieChart(PieChartModel pieChart) {
		this.pieChart = pieChart;
	}
}