package com.imie.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.imie.entities.Photo;
import com.imie.services.AbstractPersistenceService;

@Remote
@Stateless
public class PhotoService extends AbstractPersistenceService<Photo> {

	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> findAll() {
		return em.createNamedQuery("Photo.findAll").getResultList();
	}

	@Override
	public Photo findById(Integer id) {
		return em.find(Photo.class, id);
	}

	@Override
	public void insert(Photo entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void update(Photo entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Photo entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}
}
