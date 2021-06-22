package fr.eni.projetEnchere.bll;

import java.sql.Date;

import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.dal.ArticleVenduDAO;
import fr.eni.projetEnchere.dal.jdbcTools.DAOFactory;

public class ArticleVenduManager {

	/**
	 * Mise en place d'un singleton pour l'utilisation du Manager
	 */
	private static UtilisateurManager instance;

	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	// attribut d'instance
	private ArticleVenduDAO articleVendu = DAOFactory.getArticleVendu();

	// Méthode

	public void ajoutArticle(

			int noArticle,
			String nomArticle,
			String description,
			Date dateDebutEncheres,
			Date finDebutEncheres,
			int prixInitial,
			int prixVente) {

		// Vérification des données provenant du formulaire

		// Après vérification, création de l'utilisateur
		boArticleVendu nvlArticle = new boArticleVendu(noArticle, nomArticle, description, dateDebutEncheres,
				finDebutEncheres, prixInitial, prixVente);

		// Ajout à la BDD
		articleVendu.insert(nvlArticle);
	}
}
