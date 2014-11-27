package br.edu.univas.lab6.smartpoll.main;

import java.util.ArrayList;
import java.util.List;

import br.edu.univas.lab6.smartpoll.entity.Question;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.QuestionService;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Question> questions = new ArrayList<Question>();

		SimpleEntityManager simpleEntityManager = new SimpleEntityManager();

		QuestionService service = new QuestionService(simpleEntityManager);

		questions = service.findAll();

		for (Question question : questions) {
			System.out.println(question.getTitle());
		}

		simpleEntityManager.close();
	}
}
