package com.imie.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.imie.entities.Playlist;
import com.imie.services.AbstractPersistenceService;

@Remote
@Stateless
public class PlaylistService extends AbstractPersistenceService<Playlist> {

	/** Constructeur par défaut. */
	public PlaylistService() {
		super();
		// TODO : Corriger l'injection via @PersistenceContext
		initEm();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Playlist> findAll() {
		return em.createNamedQuery("Playlist.findAll").getResultList();
	}

	@Override
	public Playlist findById(Integer id) {
		return em.find(Playlist.class, id);
	}

	@Override
	public void insert(Playlist entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void update(Playlist entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Playlist entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}
}
