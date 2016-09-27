package com.imie.services;

import com.imie.entities.Fichier;

public interface FichierService extends BasicCRUDOperations<Fichier> {
	/**
	 * Retourne le fichier correspondant au chemin passé en paramètres.
	 * @param cheminfichier Le chemin/nom du fichier.
	 * @return Le fichier correspondant au chemin spécifié en paramètres.
	 */
	Fichier findByPath(final String cheminfichier);
}
