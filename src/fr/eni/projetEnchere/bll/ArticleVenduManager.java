package fr.eni.projetEnchere.bll;


import java.time.LocalDate;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.dal.ArticleVenduDAO;
import fr.eni.projetEnchere.dal.jdbcTools.DAOFactory;

public class ArticleVenduManager {

	/**
	 * Mise en place d'un singleton pour l'utilisation du Manager
	 */
	private static ArticleVenduManager instance;

	public static ArticleVenduManager getInstance() {
		if (instance == null) {
			instance = new ArticleVenduManager();
		}
		return instance;
	}

	// attribut d'instance
	private ArticleVenduDAO articleVendu = DAOFactory.getArticleVendu();

	// Méthode

	public void ajoutArticle(

			String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate finDebutEnchere,
			int prixInitial, int prixVente, int idUtilisateur, int categorie) {

		// Vérification des données provenant du formulaire

		// Après vérification, création de l'article
		boArticleVendu nvlArticle = new boArticleVendu(nomArticle, description, dateDebutEnchere, finDebutEnchere,
				prixInitial, prixVente, idUtilisateur, categorie);

		// Ajout à la BDD
		articleVendu.insert(nvlArticle);
	}

	/*******************************************************************************
	 *
	 * ENSEMBLE DES MÉTHODES INDÉPENDANTES DE VERIFICATION DE CHAMPS
	 *
	 *******************************************************************************/

	private void validerArticleVendu(boArticleVendu articleVendu, BusinessException businessException) {

	}
	
	private void validerNomArticle(boArticleVendu articleVendu, BusinessException businessException) {
		if(articleVendu.getNomArticle().length() > 5 && articleVendu.getNomArticle().length() < 30) {
			
		}
	}
}