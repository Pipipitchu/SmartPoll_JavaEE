package br.edu.univas.lab6.smartpoll.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import br.edu.univas.lab6.smartpoll.entity.Answer;
import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.QuestionService;

@ManagedBean(name = "result")
@ViewScoped
public class resultBean {

	private List<Question> questions = new ArrayList<Question>();

	SimpleEntityManager simpleEntityManager = new SimpleEntityManager();
	QuestionService service = new QuestionService(simpleEntityManager);

	private PieChartModel pieChart;

	public resultBean() {
		pieChart = new PieChartModel();
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Question> getAllQuestions() {
		questions = service.findAll();
		return questions;
	}

	public void createPieChart(Question question) {
		pieChart = new PieChartModel();

		for (Answer answer : question.getAnswers()) {
			pieChart.set(answer.getAnswer(), answer.getResults().size());
		}

		// pieChart.set("Brand 1", 540);
		// pieChart.set("Brand 2", 325);
		// pieChart.set("Brand 3", 702);
		// pieChart.set("Brand 4", 421);

		pieChart.setTitle(question.getTitle());
		pieChart.setLegendPosition("w");
	}

	public PieChartModel getPieChart() {
		return pieChart;
	}

	public void setPieChart(PieChartModel pieChart) {
		this.pieChart = pieChart;
	}

}
