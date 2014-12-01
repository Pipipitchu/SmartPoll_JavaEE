package br.edu.univas.lab6.smartpoll.main;

import java.util.ArrayList;
import java.util.List;

import br.edu.univas.lab6.smartpoll.entity.Answer;
import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.QuestionService;

public class MainClass {

	public static void main(String[] args) {

		List<Question> questions = new ArrayList<Question>();

		SimpleEntityManager simpleEntityManager = new SimpleEntityManager();

		QuestionService questionService = new QuestionService(
				simpleEntityManager);

		questions = questionService.findAll();

		for (Question question : questions) {
			System.out.println(question.getTitle());

			for (Answer answer : question.getAnswers()) {
				System.out.println(answer.getAnswer());
			}

		}

		System.out.println();

		simpleEntityManager.close();
	}
}
