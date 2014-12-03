package br.edu.univas.lab6.smartpoll.main;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainClass {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		/*List<Question> questions = new ArrayList<Question>();

		SimpleEntityManager simpleEntityManager = new SimpleEntityManager();

		/*QuestionService questionService = new QuestionService(
				simpleEntityManager);

		questions = questionService.findByMonthVote(12);

		System.out.println(questions.size());
		for (Question question : questions) {
			System.out.println(question.getTitle());
			System.out.println(question.getResults().size());

		}
   
		System.out.println();
		
		UserService service = new UserService(simpleEntityManager);
		User user = service.findByEmailPassword("tscodeler@gmail.com", "camila");
		
		System.out.println(user.getName());

		simpleEntityManager.close(); */
		
		String password = "admin";
		
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(password.getBytes(), 0, password.length());
		BigInteger bigInteger = new BigInteger(1, messageDigest.digest());
		System.out.println(String.format("%1$032x", bigInteger));

	}
}
