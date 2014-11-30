package br.edu.univas.lab6.smartpoll.managers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SimpleEntityManager {
	private EntityManager entityManager;
	private static final EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("smartpoll");

	public SimpleEntityManager() {
		this.entityManager = factory.createEntityManager();
	}

	public void beginTransaction() {
		entityManager.getTransaction().begin();
	}

	public void commit() {
		entityManager.getTransaction().commit();
	}

	public void close() {
		entityManager.close();
		factory.close();
	}

	public void rollBack() {
		entityManager.getTransaction().rollback();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}