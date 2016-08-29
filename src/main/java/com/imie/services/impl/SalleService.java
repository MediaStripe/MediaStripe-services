package com.imie.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.imie.entities.Salle;
import com.imie.services.AbstractPersistenceService;

@Remote
@Stateless
public class SalleService  extends AbstractPersistenceService<Salle> {

	/** Constructeur par défaut. */
	public SalleService() {
		super();
		// TODO : Corriger l'injection via @PersistenceContext
		initEm();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Salle> findAll() {
		return em.createNamedQuery("Salle.findAll").getResultList();
	}

	@Override
	public Salle findById(Integer id) {
		return em.find(Salle.class, id);
	}

	@Override
	public void insert(Salle entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void update(Salle entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Salle entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}
}
