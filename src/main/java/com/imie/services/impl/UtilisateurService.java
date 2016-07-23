package com.imie.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.imie.entities.Utilisateur;
import com.imie.services.AbstractPersistenceService;

@Remote
@Stateless
public class UtilisateurService extends AbstractPersistenceService<Utilisateur> {


	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> findAll() {
		return em.createNamedQuery("Utilisateur.findAll").getResultList();
	}

	@Override
	public Utilisateur findById(Integer id) {
		return em.find(Utilisateur.class, id);
	}

	@Override
	public void insert(Utilisateur entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void update(Utilisateur entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Utilisateur entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}

}
