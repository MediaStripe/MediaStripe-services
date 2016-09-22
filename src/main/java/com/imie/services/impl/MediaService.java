package com.imie.services.impl;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.imie.entities.Media;
import com.imie.entities.Utilisateur;
import com.imie.services.AbstractPersistenceService;

@Remote
@Stateless
public class MediaService extends AbstractPersistenceService<Media> {

	private static final int A_CHAR = 97;
	
	/** Constructeur par défaut. */
	public MediaService() {
		super();
		// TODO : Corriger l'injection via @PersistenceContext
		initEm();
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

	/**
	 * Retourne les 20 derniers médias publiés.
	 * 
	 * @return La liste des 20 derniers médias publiés.
	 */
	public List<Media> getDerniersPublies() {
		final Query query = em.createNamedQuery("Media.getDerniersPublies");

		query.setMaxResults(20);
		
		List<Media> listeMedias = (List<Media>) query.getResultList();
		
		return listeMedias;
	}
	
	public List<Media> search(final List<String> criteres, final Map<String, Boolean> categories) {
//		final String querySql = completerRequete("SELECT DISTINCT m FROM Media m INNER JOIN m.listeTags t ", criteres, categories);
		final String querySql = buildQuery(criteres, categories);
		
		final Query query = em.createQuery(querySql);
		
		query.setParameter("critere" + ((char) (A_CHAR)), "%" + criteres.get(0) + "%");
		
		for(int i = 0 ; i < criteres.size() ; i++) {
			query.setParameter("critere" + ((char) (A_CHAR + i)), "%" + criteres.get(i) + "%");
		}
		
		final List<Media> resultat = query.getResultList();
		
		return resultat;
	}
	
	private String completerRequete(final String requete, final List<String> criteres, final Map<String, Boolean> categories) {
		final StringBuilder requeteBuilder = new StringBuilder(requete);
		
		requeteBuilder.append("WHERE ");
		ajouterUnCritere(requeteBuilder, 0);
		
		for(int i = 1 ; i < criteres.size() ; i++) {
			requeteBuilder.append(" OR ");
			ajouterUnCritere(requeteBuilder, i);
		}
		
		requeteBuilder.append(" AND m.publique is TRUE");
		
		for(final Map.Entry<String, Boolean> categorie : categories.entrySet()) {
			ajouterUneCategorie(requeteBuilder, categorie);
		}
		
		return requeteBuilder.toString();
	}
	
	private void ajouterUnCritere(final StringBuilder requeteBuilder, final int numeroCritere) {
		final char extension = (char) (A_CHAR + numeroCritere);
		requeteBuilder.append("m.titre like :critere").append(extension);
		requeteBuilder.append(" OR m.description like :critere").append(extension);
		requeteBuilder.append(" OR m.mainTheme.libelle like :critere").append(extension);
		requeteBuilder.append(" OR t.libelle like :critere").append(extension);
	}
	
	private void ajouterUneCategorie(final StringBuilder requeteBuilder, final Map.Entry<String, Boolean> categorie) {
		if(!categorie.getValue()) {
			requeteBuilder.append(" AND m.id not in (SELECT c.id FROM ").append(categorie.getKey()).append(" c) ");
		}
	}
	
	
	
	
	
	
	
	
	
	private String buildQuery(final List<String> criteres, final Map<String, Boolean> categories) {
		final StringBuilder requeteBuilder = new StringBuilder("SELECT m FROM Media m ");
		
		requeteBuilder.append("WHERE m.id IN (").append(buildCriteresQuery(criteres)).append(") ");
		
		requeteBuilder.append("AND m.id IN (").append(buildTagQuery(criteres)).append(") ");
		
		requeteBuilder.append("AND m.id IN (").append(buildCategoriesQuery(categories)).append(") ");
		
		return requeteBuilder.toString();
	}
	
	/**
	 * Construit une requete du type :<br/>
	 * SELECT mc.id FROM Media mc 
	 * WHERE mc.titre LIKE :critereA
	 * OR mc.description LIKE :critereA
	 * OR mc.mainTheme.libelle LIKE :critereA
	 * OR mc.titre LIKE :critereB
	 * OR mc.description LIKE :critereB
	 * OR mc.mainTheme.libelle LIKE :critereB
	 * 
	 * @param listeCriteres
	 * @return
	 */
	private String buildCriteresQuery(final List<String> listeCriteres) {
		final StringBuilder requeteBuilder = new StringBuilder("SELECT mc.id FROM Media mc WHERE");
	
		for(int i = 0 ; i < listeCriteres.size() ; i++) {
			final char extension = (char) (A_CHAR + i);
			
			if(i > 0) {
				requeteBuilder.append(" OR");
			}
			
			requeteBuilder.append(" mc.titre LIKE :").append("critere").append(extension);
			requeteBuilder.append(" OR mc.description LIKE :").append("critere").append(extension);
			requeteBuilder.append(" OR mc.mainTheme.libelle LIKE :").append("critere").append(extension);
		}
		
		return requeteBuilder.toString();
	}
	
	/**
	 * Construit une requete du type :<br/>
	 * SELECT mca.id FROM Media mca
	 * WHERE mca.id NOT IN (SELECT c.id FROM Musique c)
	 * AND mca.id NOT IN (SELECT c.id FROM Video c)
	 * 
	 * @param listeCategories
	 * @return
	 */
	private String buildCategoriesQuery(final Map<String, Boolean> listeCategories) {
		final StringBuilder requeteBuilder = new StringBuilder("SELECT mca.id FROM Media mca WHERE");
		
		int i = 0;
		for(final Map.Entry<String, Boolean> categorie : listeCategories.entrySet()) {
			if(!categorie.getValue()) {
				if(i > 0) {
					requeteBuilder.append(" AND");
				}
				requeteBuilder.append(" mca.id NOT IN (SELECT mf.id FROM ").append(categorie.getKey()).append(" mf)");
				i++;
			}
		}
		
		return requeteBuilder.toString();
	}
	
	/**
	 * Construit une requete du type :<br/>
	 * SELECT mcr.id FROM Media mcr INNER JOIN m.listeTags t 
	 * WHERE t.libelle LIKE :critereA
	 * OR t.libelle LIKE :critereB
	 * OR t.libelle LIKE :critereC
	 * 
	 * @param criteres
	 * @return
	 */
	private String buildTagQuery(final List<String> criteres) {
		final StringBuilder requeteBuilder = new StringBuilder("SELECT mcr.id FROM Media mcr INNER JOIN m.listeTags t WHERE");
		
		for(int i = 0 ; i < criteres.size() ; i++) {
			final char extension = (char) (A_CHAR + i);
			
			if(i > 0) {
				requeteBuilder.append(" OR");
			}
			
			requeteBuilder.append(" t.libelle LIKE :").append("critere").append(extension);
		}
		
		return requeteBuilder.toString();
	}
}