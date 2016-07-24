package com.imie.services;

import java.util.List;

import javax.persistence.PersistenceException;

/**
 * Interface CRUD avec la méthode {@link BasicCRUDOperations#findAll()} en plus.
 * @author takiguchi
 *
 * @param <T> L'entité de persistence à gérer.
 */
public interface BasicCRUDOperations<T> {
	/**
	 * Retourne toutes les entités de classe T dans la base de données.
	 * @return La liste de toutes les entités de classe T.
	 */
	public List<T> findAll();

	/**
	 * Retourne l'objet T correspondant à l'ID passé en paramètre.
	 * @param id L'ID passé en paramètre.
	 * @return L'objet T correspondant à l'ID passé en paramètre.
	 * @throws PersistenceException Si l'objet n'a pas été trouvé en base.
	 */
	public T findById(Integer id) throws PersistenceException;

	/**
	 * Enregistre l'objet de classe T dans la base de données.
	 * @param entity L'objet de classe T à enregistrer.
	 */
	public void insert(T entity);

	/**
	 * Met à jour l'objet de classe T en base.
	 * @param entity L'objet de classe T à mettre à jour.
	 */
	public void update(T entity);

	/**
	 * Supprime l'objet de classe T dans la base de données.
	 * @param entity L'objet de classe T à supprimer.
	 */
	public void delete(T entity);
}
