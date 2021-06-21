package fr.eni.projetEnchere.bll;

import fr.eni.projetEnchere.bo.boUtilisateur;
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
	//Attribut d'instance
	private UtilisateurDAO utilisateur = DAOFactory.getUtilisateur();
	
	//Liste des méthodes
	public void ajoutNouvelUtilisateur(
			String pseudo,
			String nom,
			String prenom,
			String email,
			String telephone,
			String rue,
			String code_postal,
			String ville,
			String mot_de_passe,
			int credit,
			boolean administrateur) {
		
		//Vérification des données provenant du formulaire
		
		//Après vérification, création de l'utilisateur
		boUtilisateur nvlUtilisateur = new boUtilisateur(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur);
		
		//Ajout dans la BDD
		utilisateur.insert(nvlUtilisateur);
	}
	
}