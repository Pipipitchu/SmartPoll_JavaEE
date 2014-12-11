package br.edu.univas.lab6.smartpoll.main;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MainClass {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		Integer amountOfVotes = 200;

		Integer answer = 0;
		Integer question = 0;
		Integer month = 0;
		Integer day = 0;

		Random random = new Random();

		for (int i = 0; i < amountOfVotes; i++) {

			question = random.nextInt(2) + 28;

			if (question == 28) {
				answer = random.nextInt(4) + 25;
			} else {
				answer = random.nextInt(4) + 29;
			}

			month = random.nextInt(12) + 1;

			if (month == 2) {
				day = random.nextInt(28) + 1;
			} else if (month == 1 || month == 3 || month == 5 || month == 7
					|| month == 8 || month == 10) {
				day = random.nextInt(31) + 1;
			} else if (month == 12) {
				day = random.nextInt(11) + 1;
			} else {
				day = random.nextInt(30) + 1;
			}

			String sql = "insert into result (question_id, answer_id, date_vote) values(";
			sql += question + ", ";
			sql += answer + ", ";
			sql += "'" + day + "/" + month + "/2014');";

			System.out.println(sql);
		}

	}
}
