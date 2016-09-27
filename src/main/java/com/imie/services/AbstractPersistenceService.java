package com.imie.services;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Classe abstraite servant de base aux services de persistence en leur
 * fournissant un entity manager injecté par le conteneur d'EJB.
 * 
 * @author takiguchi
 *
 * @param <T>
 *            L'entité de persistence à gérer.
 */
public abstract class AbstractPersistenceService {
	@Inject
	protected EntityManager em;
}
