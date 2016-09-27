package com.imie.services;

import javax.persistence.PersistenceException;

import com.imie.entities.Utilisateur;

public interface UtilisateurService extends BasicCRUDOperations<Utilisateur> {
	/**
	 * Retourne l'utilisateur correspondant à l'adresse mail saisie en paramtètre.
	 * @param mail l'adresse mail de l'utilisateur recherché.
	 * @return L'utilisateur correspondant à l'adresse mail saisie.
	 * @throws PersistenceException Si l'utilisateur n'existe pas en base.
	 */
	Utilisateur findByEmail(final String mail) throws PersistenceException;
}
