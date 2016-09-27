package com.imie.services;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerProvider {

	@Produces
	public EntityManager createEntityManager() {
		return Persistence.createEntityManagerFactory("MediaStripe-entities").createEntityManager();
	}
}
