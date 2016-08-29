package com.imie.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.imie.entities.Film;
import com.imie.services.AbstractPersistenceService;

@Remote
@Stateless
public class FilmService extends AbstractPersistenceService<Film> {

	/** Constructeur par défaut. */
	public FilmService() {
		super();
		// TODO : Corriger l'injection via @PersistenceContext
		initEm();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Film> findAll() {
		return em.createNamedQuery("Film.findAll").getResultList();
	}

	@Override
	public Film findById(Integer id) {
		return em.find(Film.class, id);
	}

	@Override
	public void insert(Film entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void update(Film entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Film entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}
}
