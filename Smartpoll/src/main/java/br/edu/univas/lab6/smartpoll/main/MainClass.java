package br.edu.univas.lab6.smartpoll.main;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.QuestionService;

public class MainClass {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		List<Question> questions = new ArrayList<Question>();

		SimpleEntityManager simpleEntityManager = new SimpleEntityManager();

		QuestionService questionService = new QuestionService(
				simpleEntityManager);

		questions = questionService.findByMonthVote(11);

		System.out.println(questions.size());
		
		for (Question question : questions) {
			System.out.println(question.getTitle());
			System.out.println(question.getResults().size());

		}

	}
}
