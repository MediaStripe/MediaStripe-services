package com.imie.services;

import com.imie.entities.Tag;

public interface TagService extends BasicCRUDOperations<Tag> {
	/**
	 * Retourne le tag correspondant au libellé passé en paramètre.
	 * 
	 * @param libelle
	 *            Le libellé du tag recherché.
	 * @return Le tag correspondant ou {@code null} s'il n'a pas été trouvé en
	 *         base.
	 */
	Tag findByLibelle(final String libelle);
}
