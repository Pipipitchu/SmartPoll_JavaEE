package br.edu.univas.lab6.smartpoll.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import br.edu.univas.lab6.smartpoll.entity.Answer;
import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.QuestionService;

@ManagedBean(name = "result")
@ViewScoped
public class resultBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Question> questions = new ArrayList<Question>();

	SimpleEntityManager simpleEntityManager = new SimpleEntityManager();
	QuestionService service = new QuestionService(simpleEntityManager);

	private PieChartModel pieChart;

	private BarChartModel barChart;

	public resultBean() {
		pieChart = new PieChartModel();
		barChart = new BarChartModel();
		// createBarChart();
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

	public void createBarChart(Question question) {
		barChart = new BarChartModel();

		int max = 1;

		ChartSeries answerSeries = new ChartSeries();
		answerSeries.setLabel("Answers");

		for (Answer answer : question.getAnswers()) {
			int amount = answer.getResults().size();
			answerSeries.set(answer.getAnswer(), amount);

			if (amount > max) {
				max = amount + 1;
			}
		}

		barChart.addSeries(answerSeries);

		barChart.setTitle("Bar Chart");
		barChart.setLegendPosition("ne");
		barChart.isAnimate();

		Axis xAxis = barChart.getAxis(AxisType.X);
		xAxis.setLabel("Answer");

		Axis yAxis = barChart.getAxis(AxisType.Y);
		yAxis.setLabel("Amount");
		yAxis.setMin(0);
		yAxis.setMax(max);

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

	public PieChartModel getPieChart() {
		return pieChart;
	}

	public void setPieChart(PieChartModel pieChart) {
		this.pieChart = pieChart;
	}

	public BarChartModel getBarChart() {
		return barChart;
	}

	public void setBarChart(BarChartModel barChart) {
		this.barChart = barChart;
	}

}
