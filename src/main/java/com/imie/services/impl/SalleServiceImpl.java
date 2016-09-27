package com.imie.services.impl;

import java.util.List;

import com.imie.entities.Salle;
import com.imie.services.AbstractPersistenceService;
import com.imie.services.SalleService;


public class SalleServiceImpl extends AbstractPersistenceService implements SalleService 	{
//	@Produces
//	@PersistenceContext(unitName = "MediaStripe-entities")
//	protected EntityManager em;
	
	/** Constructeur par défaut. */
	public SalleServiceImpl() {
		super();
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
