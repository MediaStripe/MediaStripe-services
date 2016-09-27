package com.imie.services.impl;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.imie.entities.Fichier;
import com.imie.services.AbstractPersistenceService;
import com.imie.services.FichierService;

public class FichierServiceImpl extends AbstractPersistenceService implements FichierService {
//	@Produces
//	@PersistenceContext(unitName = "MediaStripe-entities")
//	protected EntityManager em;
	
	/** Constructeur par défaut. */
	public FichierServiceImpl() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Fichier> findAll() {
		return em.createNamedQuery("Fichier.findAll").getResultList();
	}

	@Override
	public Fichier findById(Integer id) {
		return em.find(Fichier.class, id);
	}

	@Override
	public void insert(Fichier entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void update(Fichier entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Fichier entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}
	
	@Override
	public Fichier findByPath(final String cheminfichier) {
		final Query query = em.createNamedQuery("Fichier.findByPath");

		query.setParameter("cheminfichier", cheminfichier);
		
		Fichier fichier = null;
		try {
			fichier = (Fichier) query.getSingleResult();
		} catch (final PersistenceException e) {
			System.err.println(new StringBuilder("Aucun fichier trouvé pour l'adresse suivante : ").append(cheminfichier).toString());
			throw e;
		}
		
		return fichier;
	}
}
