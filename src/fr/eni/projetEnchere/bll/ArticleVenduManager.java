package fr.eni.projetEnchere.bll;


import java.time.LocalDate;
import java.util.List;

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
	
	private ArticleVenduDAO articleVenduDAO;
	public ArticleVenduDAO articleVendu = DAOFactory.getArticleVendu();

	public List<boArticleVendu> selectAll() throws BusinessException{
		return articleVenduDAO.selectAll();
	}
	// Méthode

	public void ajoutArticle(

			String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate finDebutEnchere,
			int prixInitial, int prixVente, int idUtilisateur, int categorie) throws BusinessException {

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
		validerNomArticle(articleVendu, businessException);
		validerDescription(articleVendu, businessException);
		validerDate(articleVendu.getDateDebutEncheres(), businessException);
		validerDate(articleVendu.getFinDebutEncheres(), businessException);
		validerPrix(articleVendu.getPrixInitial(), businessException);
		validerPrix(articleVendu.getPrixVente(), businessException);
	}
	
	private void validerNomArticle(boArticleVendu articleVendu, BusinessException businessException) {
		if(articleVendu.getNomArticle().length() > 5 && articleVendu.getNomArticle().length() < 30) {
			businessException.ajouterErreur(CodesErreursBLL.ARTICLE_NOM_ERREUR);
		}
	}
	private void validerDescription(boArticleVendu articleVendu, BusinessException businessException) {
		
		if(articleVendu.getDescription().length() > 10 && articleVendu.getDescription().length() < 300) {
			businessException.ajouterErreur(CodesErreursBLL.ARTICLE_DESCRIPTON_ERREUR);
		}
	}
	private void validerDate (LocalDate date, BusinessException businessException) {
		if (date == null || date.equals(LocalDate.now()) || date.isAfter(LocalDate.now())) {
			businessException.ajouterErreur(CodesErreursBLL.ARTICLE_DATE_ERREUR);
		}
	}
	private void validerPrix(int prix, BusinessException businessException) {
		if(prix < 0 ) {
			businessException.ajouterErreur(CodesErreursBLL.ARTICLE_PRIX_ERREUR);
		}
	}
}