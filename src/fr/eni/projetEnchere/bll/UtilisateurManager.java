package fr.eni.projetEnchere.bll;

import fr.eni.projetEnchere.dal.UtilisateurDAO;
import fr.eni.projetEnchere.dal.jdbcTools.DAOFactory;

public class UtilisateurManager {

	/**
	 * Mise en place d'un singleton pour l'utilisation du Manager	
	 */
	private static UtilisateurManager instance;
	
	public static UtilisateurManager getInstance() {
		if(instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	/**
	 * Mise en place des attributs et méthodes utiles au Manager
	 */
	private UtilisateurDAO utilisateur = DAOFactory.getUtilisateur();
	
	
	
}