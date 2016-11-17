package com.imie.services;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerProvider {
	
	private static EntityManager em;
	
	@Produces
	public EntityManager createEntityManager() {
		if(em == null) {
			em = Persistence.createEntityManagerFactory("MediaStripe-entities").createEntityManager();
		}
		return em;
	}
}
