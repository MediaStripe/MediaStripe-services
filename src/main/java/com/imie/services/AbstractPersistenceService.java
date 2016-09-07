package com.imie.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Classe abstraite servant de base aux services de persistence en leur
 * fournissant un entity manager injecté par le conteneur d'EJB.
 * 
 * @author takiguchi
 *
 * @param <T>
 *            L'entité de persistence à gérer.
 */
public abstract class AbstractPersistenceService<T> implements
		BasicCRUDOperations<T> {

	@PersistenceContext(unitName = "MediaStripe-entities", type = PersistenceContextType.EXTENDED)
	protected EntityManager em;
	
	// TODO : Supprimer une fois l'injection corrigée
	protected void initEm() {
		em = EntityManagerProvider.getEntityManager();
	}

	/**
	 * Retourne l'entity manager.
	 * 
	 * @return L'entity manager.
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * Modifie l'entity manager.
	 * 
	 * @param em
	 *            Le nouvel entity manager.
	 */
	protected void setEm(EntityManager em) {
		this.em = em;
	}

}
