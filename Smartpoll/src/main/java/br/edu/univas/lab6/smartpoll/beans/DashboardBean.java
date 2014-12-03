package br.edu.univas.lab6.smartpoll.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.entity.Result;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.QuestionService;

@ManagedBean(name = "dashboard")
@ViewScoped
public class DashboardBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer month;
	private Integer maxValue;
	private LineChartModel lineChart;
	private SimpleEntityManager simpleEntityManager;
	private QuestionService questionService;
	private List<Question> questions;

	public DashboardBean() {
		maxValue = 1;
		simpleEntityManager = new SimpleEntityManager();
		questionService = new QuestionService(simpleEntityManager);
		setMonth(getCurrentMonth());
		createLineChart();
	}

	private void createLineChart() {
		lineChart = initCategoryModel();
		lineChart.setTitle("Category Chart");
		lineChart.setLegendPosition("e");
		lineChart.setShowPointLabels(true);
		lineChart.getAxes().put(AxisType.X, new CategoryAxis("Questions"));
		Axis yAxis = lineChart.getAxis(AxisType.Y);
		yAxis.setLabel("Amount");
		yAxis.setMin(0);
		yAxis.setMax(maxValue);

	}

	private LineChartModel initCategoryModel() {
		LineChartModel model = new LineChartModel();

		questions = questionService.findAll();
		List<Result> votes;

		ChartSeries serie;

		int amount = 0;

		for (Question question : questions) {
			serie = new ChartSeries();
			serie.setLabel(question.getTitle());

			votes = question.getResults();
			
	

			amount = votes.size();

			if (amount > maxValue) {
				maxValue = amount;
			}

		}

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Boys");
		boys.set("2004", 120);
		boys.set("2005", 100);
		boys.set("2006", 44);
		boys.set("2007", 150);
		boys.set("2008", 25);

		ChartSeries girls = new ChartSeries();
		girls.setLabel("Girls");
		girls.set("2004", 52);
		girls.set("2005", 60);
		girls.set("2006", 110);
		girls.set("2007", 90);
		girls.set("2008", 120);

		model.addSeries(boys);
		model.addSeries(girls);

		return model;
	}

	private int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH);
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public LineChartModel getLineChart() {
		return lineChart;
	}

	public void setLineChart(LineChartModel lineChart) {
		this.lineChart = lineChart;
	}

}
