package com.imie.services.impl;

import java.util.List;

import com.imie.entities.Playlist;
import com.imie.services.AbstractPersistenceService;
import com.imie.services.PlaylistService;


public class PlaylistServiceImpl extends AbstractPersistenceService implements PlaylistService {
//	@Produces
//	@PersistenceContext(unitName = "MediaStripe-entities")
//	protected EntityManager em;
	
	/** Constructeur par défaut. */
	public PlaylistServiceImpl() {
		super();
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
