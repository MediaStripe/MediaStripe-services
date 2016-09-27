package com.imie.services.impl;

import java.util.List;

import com.imie.entities.Galerie;
import com.imie.services.AbstractPersistenceService;
import com.imie.services.GalerieService;


public class GalerieServiceImpl extends AbstractPersistenceService implements GalerieService {
//	@Produces
//	@PersistenceContext(unitName = "MediaStripe-entities")
//	protected EntityManager em;
	
	/** Constructeur par défaut. */
	public GalerieServiceImpl() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Galerie> findAll() {
		return em.createNamedQuery("Galerie.findAll").getResultList();
	}

	@Override
	public Galerie findById(Integer id) {
		return em.find(Galerie.class, id);
	}

	@Override
	public void insert(Galerie entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void update(Galerie entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Galerie entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}
}
