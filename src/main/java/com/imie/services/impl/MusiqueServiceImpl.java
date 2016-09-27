package com.imie.services.impl;

import java.util.List;

import com.imie.entities.Musique;
import com.imie.services.AbstractPersistenceService;
import com.imie.services.MusiqueService;


public class MusiqueServiceImpl extends AbstractPersistenceService implements MusiqueService {
//	@Produces
//	@PersistenceContext(unitName = "MediaStripe-entities")
//	protected EntityManager em;
	
	/** Constructeur par défaut. */
	public MusiqueServiceImpl() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Musique> findAll() {
		return em.createNamedQuery("Musique.findAll").getResultList();
	}

	@Override
	public Musique findById(Integer id) {
		return em.find(Musique.class, id);
	}

	@Override
	public void insert(Musique entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void update(Musique entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Musique entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}
}
