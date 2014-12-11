package br.edu.univas.lab6.smartpoll.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.QuestionService;
import br.edu.univas.lab6.smartpoll.service.ResultService;

@ManagedBean(name = "dashboard")
@ViewScoped
public class DashboardBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer month;
	private LineChartModel lineChart;
	private SimpleEntityManager simpleEntityManager;
	private QuestionService questionService;
	private ResultService resultService;

	public DashboardBean() {

		simpleEntityManager = new SimpleEntityManager();
		questionService = new QuestionService(simpleEntityManager);
		resultService = new ResultService(simpleEntityManager);
		setMonth(getCurrentMonth());
		createLineChart();
	}

	public void createLineChart() {
		Long maxValue = 0L;
		
		lineChart = new LineChartModel();

		List<Question> questions = questionService.findAll();

		ChartSeries serie;

		for (Question question : questions) {

			serie = new ChartSeries();
			serie.setLabel(question.getTitle());
			for (int day = 1; day <= lastDayMonth(month); day++) {
				Long amount = 0L;

				amount = resultService.countVotesPerDate(question, month, day);

				if (amount > maxValue) {
					maxValue = amount;
				}
				serie.set(day, amount);
			}
			lineChart.addSeries(serie);
		}

		lineChart.setTitle("Amount of votes per month");
		lineChart.setLegendPosition("ne");
		lineChart.setShowPointLabels(true);
		lineChart.setShowDatatip(true);
		lineChart.getAxes().put(AxisType.X, new CategoryAxis("Days"));
		Axis yAxis = lineChart.getAxis(AxisType.Y);
		yAxis.setLabel("Amount");
		yAxis.setMin(0);
		yAxis.setMax(maxValue + (maxValue / 3));
	}

	private int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.MONTH));
		return cal.get(Calendar.MONTH) + 1;
	}

	private Integer lastDayMonth(Integer month) {
		Calendar dateFim = new GregorianCalendar();
		dateFim.setTime(new Date());
		dateFim.set(Calendar.MONTH, month - 1);
		return dateFim.getActualMaximum(Calendar.DAY_OF_MONTH);
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
