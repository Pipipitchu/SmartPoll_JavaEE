package br.edu.univas.lab6.smartpoll.service;

import java.util.List;

import br.edu.univas.lab6.smartpoll.dao.UsersDAO;
import br.edu.univas.lab6.smartpoll.entity.Users;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;

public class UsersService {

	private UsersDAO dao;

	private SimpleEntityManager simpleEntityManager;

	public UsersService(SimpleEntityManager simpleEntityManager) {
		this.simpleEntityManager = simpleEntityManager;
		dao = new UsersDAO(simpleEntityManager.getEntityManager());
	}

	public void save(Users users) {
		try {
			simpleEntityManager.beginTransaction();
			dao.save(users);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
		simpleEntityManager.close();
	}
	
	public void delete(Users users) {
		try {
			simpleEntityManager.beginTransaction();
			dao.delete(users.getId(), Users.class);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
		simpleEntityManager.close();
	}

	public List<Users> findAll() {
		return dao.findAll();
	}
}
