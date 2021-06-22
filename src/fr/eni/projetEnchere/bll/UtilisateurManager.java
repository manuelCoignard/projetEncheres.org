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
	private UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateur();
	
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
			String mdp,
			int credit,
			boolean administrateur) {
		
		//Vérification des données provenant du formulaire
		
		//Après vérification, création de l'utilisateur
		boUtilisateur nvlUtilisateur = new boUtilisateur(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mdp,credit,administrateur);
		
		//Ajout dans la BDD
		utilisateurDAO.insert(nvlUtilisateur);
	}
	
	public boUtilisateur connexionUtilisateur(String identifiant, String mdp) {
		boUtilisateur utilisateurConnecte = null;
		
		//Vérification des données provenant du formulaire
		
		//Choix de la connexion via pseudo ou adresse mail
		if(identifiant.contains("@")) {
			//utilisation de la méthode avec email
			utilisateurConnecte = utilisateurDAO.connectionEmail(identifiant, mdp);
		} else {
			//utilisation de la méthode avec pseudo
			utilisateurConnecte = utilisateurDAO.connectionPseudo(identifiant, mdp);
		}
		//Récupération de l'utilisateur complet
		
		//Retour de l'utilisateur
		return utilisateurConnecte;
	}
	
}