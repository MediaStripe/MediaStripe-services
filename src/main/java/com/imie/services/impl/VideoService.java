package com.imie.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.imie.entities.Video;
import com.imie.services.AbstractPersistenceService;

@Remote
@Stateless
public class VideoService extends AbstractPersistenceService<Video> {

	/** Constructeur par défaut. */
	public VideoService() {
		super();
		// TODO : Corriger l'injection via @PersistenceContext
		initEm();
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
