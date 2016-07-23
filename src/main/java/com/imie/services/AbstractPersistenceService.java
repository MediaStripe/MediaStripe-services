package com.imie.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractPersistenceService<T> implements BasicCRUDOperations<T> {
	
	@PersistenceContext(unitName = "JpaEntities")
	protected EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	protected void setEm(EntityManager em) {
		this.em = em;
	}
	
}
