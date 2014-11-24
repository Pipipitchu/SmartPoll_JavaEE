package br.edu.univas.lab6.smartpoll.service;

import java.util.List;

import br.edu.univas.lab6.smartpoll.dao.ResultDAO;
import br.edu.univas.lab6.smartpoll.entity.Result;
import br.edu.univas.lab6.smartpoll.managers.SimpleEntityManager;

public class ResultService {

	private ResultDAO dao;

	private SimpleEntityManager simpleEntityManager;

	public ResultService(SimpleEntityManager simpleEntityManager) {
		this.simpleEntityManager = simpleEntityManager;
		dao = new ResultDAO(simpleEntityManager.getEntityManager());
	}

	public void save(Result result) {
		try {
			simpleEntityManager.beginTransaction();
			dao.save(result);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
		simpleEntityManager.close();
	}

	public void delete(Result result) {
		try {
			simpleEntityManager.beginTransaction();
			dao.delete(result.getId(), Result.class);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		}
		simpleEntityManager.close();
	}

	public List<Result> findAll() {
		return dao.findAll();
	}
}
