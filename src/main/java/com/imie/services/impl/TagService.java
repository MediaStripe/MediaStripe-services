package com.imie.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.imie.entities.Tag;
import com.imie.services.AbstractPersistenceService;

@Remote
@Stateless
public class TagService extends AbstractPersistenceService<Tag> {


	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> findAll() {
		return em.createNamedQuery("Tag.findAll").getResultList();
	}

	@Override
	public Tag findById(Integer id) {
		return em.find(Tag.class, id);
	}

	@Override
	public void insert(Tag entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void update(Tag entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Tag entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}

}
