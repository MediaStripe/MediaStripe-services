package com.imie.services.impl;

import java.util.List;

import com.imie.entities.Video;
import com.imie.services.AbstractPersistenceService;
import com.imie.services.VideoService;


public class VideoServiceImpl extends AbstractPersistenceService implements VideoService {
//	@Produces
//	@PersistenceContext(unitName = "MediaStripe-entities")
//	protected EntityManager em;
	
	/** Constructeur par défaut. */
	public VideoServiceImpl() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Video> findAll() {
		return em.createNamedQuery("Video.findAll").getResultList();
	}

	@Override
	public Video findById(Integer id) {
		return em.find(Video.class, id);
	}

	@Override
	public void insert(Video entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void update(Video entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Video entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}
}
