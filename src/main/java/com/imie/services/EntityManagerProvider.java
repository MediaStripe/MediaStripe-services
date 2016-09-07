package com.imie.services;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

// TODO : Supprimer une fois l'injection EJB corrigée.
public class EntityManagerProvider {
	
	private static EntityManager em;
	
	public static EntityManager getEntityManager() {
		if(em == null) {
			em = Persistence.createEntityManagerFactory("MediaStripe-entities").createEntityManager();
		}
		return em;
	}
}
