package com.imie.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.imie.entities.Utilisateur;
import com.imie.services.AbstractPersistenceService;

/**
 * Service de persistence de l'entité {@link Utilisateur}.
 * @author takiguchi
 *
 */
@Stateless
@Remote
public class UtilisateurService extends AbstractPersistenceService<Utilisateur> {

	/** Constructeur par défaut. */
	public UtilisateurService() {
		super();
		// TODO : Corriger l'injection via @PersistenceContext
		initEm();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> findAll() {
		return em.createNamedQuery("Utilisateur.findAll").getResultList();
	}

	@Override
	public Utilisateur findById(Integer id) throws PersistenceException {
		final Utilisateur utilisateur = em.find(Utilisateur.class, id);
		
		if(utilisateur == null) {
			throw new PersistenceException("Aucun utilisateur n'existe pour l'id n°" + id + ".");
		}
		
		return utilisateur;
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
	
	/**
	 * Retourne l'utilisateur correspondant à l'adresse mail saisie en paramtètre.
	 * @param mail l'adresse mail de l'utilisateur recherché.
	 * @return L'utilisateur correspondant à l'adresse mail saisie.
	 * @throws PersistenceException Si l'utilisateur n'existe pas en base.
	 */
	public Utilisateur findByEmail(final String mail) throws PersistenceException {
		final Query query = em.createNamedQuery("Utilisateur.findByEmail");

		query.setParameter("mail", mail);
		
		Utilisateur utilisateur = null;
		try {
			utilisateur = (Utilisateur) query.getSingleResult();
		} catch (final PersistenceException e) {
			System.err.println(new StringBuilder("Aucun utilisateur trouvé pour l'adresse mail suivante : ").append(mail).toString());
			throw e;
		}
		
		return utilisateur;
	}

}
