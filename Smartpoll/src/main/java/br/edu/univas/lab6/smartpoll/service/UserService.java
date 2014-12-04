package br.edu.univas.lab6.smartpoll.service;

import java.io.Serializable;
import java.util.List;

import br.edu.univas.lab6.smartpoll.dao.UserDAO;
import br.edu.univas.lab6.smartpoll.entity.User;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;

public class UserService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserDAO dao;

	private SimpleEntityManager simpleEntityManager;

	public UserService(SimpleEntityManager simpleEntityManager) {
		this.simpleEntityManager = simpleEntityManager;
		dao = new UserDAO(simpleEntityManager.getEntityManager());
	}

	public void save(User user) {
		try {
			simpleEntityManager.beginTransaction();
			dao.save(user);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
	}

	public void delete(User user) {
		try {
			simpleEntityManager.beginTransaction();
			dao.delete(user.getId(), User.class);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
	}

	public List<User> findAll() {
		return dao.findAll();
	}

	public User findById(Long id) {
		return dao.getById(id);
	}

	public User findByEmailPassword(String email, String password) {
		return dao.findByEmailPassword(email, password);
	}

}
