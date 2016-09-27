package com.imie.services;

import java.util.List;
import java.util.Map;

import com.imie.entities.Media;

public interface MediaService extends BasicCRUDOperations<Media> {
	/**
	 * Retourne les 20 derniers médias publiés.
	 * 
	 * @return La liste des 20 derniers médias publiés.
	 */
	List<Media> getDerniersPublies();
	
	/**
	 * Effectue une recherche de médias en fonction des critères de sélection
	 * passés en paramètre.
	 * 
	 * @param criteres
	 *            Les critères de recherche, soit les mots clefs tapés par
	 *            l'utilisateur.
	 * @param categories
	 *            Les types de médias voulus.
	 * @return La liste des médias correspondant aux critères de recherche.
	 */
	List<Media> search(final List<String> criteres, final Map<String, Boolean> categories);
}
