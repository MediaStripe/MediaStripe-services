package com.imie.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.imie.entities.Media;
import com.imie.entities.Utilisateur;
import com.imie.services.AbstractPersistenceService;

@Remote
@Stateless
public class MediaService extends AbstractPersistenceService<Media> {

	/** Constructeur par défaut. */
	public MediaService() {
		super();
		// TODO : Corriger l'injection via @PersistenceContext
		initEm();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Media> findAll() {
		return em.createNamedQuery("Media.findAll").getResultList();
	}

	@Override
	public Media findById(Integer id) {
		return em.find(Media.class, id);
	}

	@Override
	public void insert(Media entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void update(Media entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Media entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}

	/**
	 * Retourne les 20 derniers médias publiés.
	 * 
	 * @return La liste des 20 derniers médias publiés.
	 */
	public List<Media> getDerniersPublies() {
		final Query query = em.createNamedQuery("Media.getDerniersPublies");

		query.setMaxResults(20);
		
		List<Media> listeMedias = (List<Media>) query.getResultList();
		
		return listeMedias;
	}
}
