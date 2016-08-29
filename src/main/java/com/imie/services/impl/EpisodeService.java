package com.imie.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.imie.entities.Episode;
import com.imie.services.AbstractPersistenceService;

@Remote
@Stateless
public class EpisodeService extends AbstractPersistenceService<Episode> {

	/** Constructeur par défaut. */
	public EpisodeService() {
		super();
		// TODO : Corriger l'injection via @PersistenceContext
		initEm();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Episode> findAll() {
		return em.createNamedQuery("Episode.findAll").getResultList();
	}

	@Override
	public Episode findById(Integer id) {
		return em.find(Episode.class, id);
	}

	@Override
	public void insert(Episode entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void update(Episode entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Episode entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}
}
