package br.edu.univas.lab6.smartpoll.main;

import br.edu.univas.lab6.smartpoll.entity.Users;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;
import br.edu.univas.lab6.smartpoll.service.UsersService;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String persistenceUnitName = "smartpoll";

		SimpleEntityManager simpleEntityManager = new SimpleEntityManager();
		
		Users users = new Users();
		
	    users.setName("emilio");
		users.setLogin("emilios");
		users.setPassword("em");
		users.setEmail("emilio@gmail.com");
		
		
		UsersService usersService = new UsersService(simpleEntityManager);

		//usersService.save(users);
		
		//users.setId(3);
		
		usersService.save(users);
		//usersService.delete(new Users(3, "thiago", "thiagoscodeler", "scodeler", "scodeler@gmail.com"));

		for (Users u : usersService.findAll()) {
			System.out.println(u.getName());
			System.out.println(u.getEmail());
		}

		simpleEntityManager.close();
	}
}
