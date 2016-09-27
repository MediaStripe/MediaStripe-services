package com.imie.services.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.imie.entities.Utilisateur;
import com.imie.services.AbstractPersistenceService;
import com.imie.services.UtilisateurService;

/**
 * Service de persistence de l'entité {@link Utilisateur}.
 * @author takiguchi
 *
 */
public class UtilisateurServiceImpl extends AbstractPersistenceService implements UtilisateurService {
	/** Constructeur par défaut. */
	public UtilisateurServiceImpl() {
		super();
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
	
	@Override
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
