package com.imie.services.impl;

import java.util.List;

import com.imie.entities.Episode;
import com.imie.services.AbstractPersistenceService;
import com.imie.services.EpisodeService;


public class EpisodeServiceImpl extends AbstractPersistenceService implements EpisodeService {
//	@Produces
//	@PersistenceContext(unitName = "MediaStripe-entities")
//	protected EntityManager em;
	
	/** Constructeur par défaut. */
	public EpisodeServiceImpl() {
		super();
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
