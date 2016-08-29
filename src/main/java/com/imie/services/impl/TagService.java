package com.imie.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.imie.entities.Tag;
import com.imie.services.AbstractPersistenceService;

@Remote
@Stateless
public class TagService extends AbstractPersistenceService<Tag> {

	/** Constructeur par défaut. */
	public TagService() {
		super();
		// TODO : Corriger l'injection via @PersistenceContext
		initEm();
	}

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

	/**
	 * Retourne le tag correspondant au libellé passé en paramètre.
	 * 
	 * @param libelle
	 *            Le libellé du tag recherché.
	 * @return Le tag correspondant ou {@code null} s'il n'a pas été trouvé en
	 *         base.
	 */
	public Tag findByLibelle(final String libelle) {
		final Query query = em.createNamedQuery("Tag.findByLibelle");

		query.setParameter("libelle", libelle);

		Tag tag = null;
		try {
			tag = (Tag) query.getSingleResult();
		} catch (final NoResultException e) {
			System.err.println(new StringBuilder("Aucun tag trouvé pour le libellé suivant : ").append(libelle));
		}

		return tag;
	}
}
