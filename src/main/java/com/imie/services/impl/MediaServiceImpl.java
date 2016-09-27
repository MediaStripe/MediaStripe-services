package com.imie.services.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import com.imie.entities.Media;
import com.imie.services.AbstractPersistenceService;
import com.imie.services.MediaService;

public class MediaServiceImpl extends AbstractPersistenceService implements MediaService {
	/** Constructeur par défaut. */
	public MediaServiceImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Media> findAll() {
		return em.createNamedQuery("Media.findAll").getResultList();
	}

	@Override
	public Media findById(Integer id) {
		return em.find(Media.class, id);
	}

	@Override
	public void insert(Media entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
	}

	@Override
	public void update(Media entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public void delete(Media entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}

	@Override
	public List<Media> getDerniersPublies() {
		final Query query = em.createNamedQuery("Media.getDerniersPublies");

		query.setMaxResults(20);

		List<Media> listeMedias = (List<Media>) query.getResultList();

		return listeMedias;
	}

	@Override
	public List<Media> search(final List<String> criteres, final Map<String, Boolean> categories) {
		final String querySql = buildQuery(criteres, categories);

		final Query query = em.createQuery(querySql);

		for (int i = 0; i < criteres.size(); i++) {
			query.setParameter(i + 1, "%" + criteres.get(i) + "%");
		}

		return query.getResultList();
	}

	/**
	 * Fabrique la requête JPQL consituant le moteur de recherche du site.
	 * 
	 * @param criteres
	 *            Les critères de recherche, soit les mots clefs tapés par
	 *            l'utilisateur.
	 * @param categories
	 *            Les types de médias voulus.
	 * @return La requête JPQL construite, correspondant à la recherche de
	 *         l'utilisateur.
	 */
	private String buildQuery(final List<String> criteres, final Map<String, Boolean> categories) {
		final StringBuilder requeteBuilder = new StringBuilder("SELECT DISTINCT m FROM Media m ");

		requeteBuilder.append("WHERE m.id IN (").append(buildCriteresQuery(criteres)).append(") ");

		if (getNombreCategoriesVoulues(categories) < categories.size()) {
			requeteBuilder.append("AND m.id IN (").append(buildCategoriesQuery(categories)).append(") ");
		}

		requeteBuilder.append("AND m.publique = TRUE ");

		return requeteBuilder.toString();
	}

	private int getNombreCategoriesVoulues(final Map<String, Boolean> listeCategories) {
		int nombreCategoriesVoulues = 0;
		for (final Map.Entry<String, Boolean> categorie : listeCategories.entrySet()) {
			if (categorie.getValue()) {
				nombreCategoriesVoulues++;
			}
		}
		return nombreCategoriesVoulues;
	}

	/**
	 * Construit une requete du type :<br/>
	 * SELECT mc.id FROM Media mc <br/>
	 * WHERE mc.titre LIKE :critereA <br/>
	 * OR mc.description LIKE :critereA <br/>
	 * OR mc.mainTheme.libelle LIKE :critereA <br/>
	 * OR t.libelle LIKE :critereA <br/>
	 * OR mc.titre LIKE :critereB <br/>
	 * OR mc.description LIKE :critereB <br/>
	 * OR mc.mainTheme.libelle LIKE :critereB <br/>
	 * OR t.libelle LIKE :critereB <br/>
	 * 
	 * @param listeCriteres
	 * @return
	 */
	private String buildCriteresQuery(final List<String> listeCriteres) {
		final StringBuilder requeteBuilder = new StringBuilder(
				"SELECT mc.id FROM Media mc INNER JOIN mc.listeTags t WHERE");

		for (int i = 1; i <= listeCriteres.size(); i++) {
			if (i > 1) {
				requeteBuilder.append(" OR");
			}

			requeteBuilder.append(" mc.titre LIKE ?").append(i);
			requeteBuilder.append(" OR mc.description LIKE ?").append(i);
			requeteBuilder.append(" OR mc.mainTheme.libelle LIKE ?").append(i);
			requeteBuilder.append(" OR t.libelle LIKE ?").append(i);
			requeteBuilder.append(" OR mc.publieur.nom LIKE ?").append(i);
			requeteBuilder.append(" OR mc.publieur.prenom LIKE ?").append(i);
			requeteBuilder.append(" OR mc.publieur.mail LIKE ?").append(i);
		}

		return requeteBuilder.toString();
	}

	/**
	 * Construit une requete du type :<br/>
	 * SELECT mca.id FROM Media mca <br/>
	 * WHERE mca.id NOT IN (SELECT c.id FROM Musique c) <br/>
	 * AND mca.id NOT IN (SELECT c.id FROM Video c) <br/>
	 * 
	 * @param listeCategories
	 * @return
	 */
	private String buildCategoriesQuery(final Map<String, Boolean> listeCategories) {
		final StringBuilder requeteBuilder = new StringBuilder("SELECT mca.id FROM Media mca WHERE");

		int i = 0;
		for (final Map.Entry<String, Boolean> categorie : listeCategories.entrySet()) {
			if (!categorie.getValue()) {
				if (i > 0) {
					requeteBuilder.append(" AND");
				}
				requeteBuilder.append(" mca.id NOT IN (SELECT mf.id FROM ").append(categorie.getKey()).append(" mf)");
				i++;
			}
		}

		return requeteBuilder.toString();
	}
}