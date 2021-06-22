package fr.eni.projetEnchere.bll;

import fr.eni.projetEnchere.bo.boUtilisateur;
import fr.eni.projetEnchere.dal.UtilisateurDAO;
import fr.eni.projetEnchere.dal.jdbcTools.DAOFactory;

/**
 * Classe contenant les méthodes utiles pour intéragir avec l'objet boUtilisateur
 * @author Groupe G (LORENT Maxime, TANGUY Cyril, COIGNARD Manuel
 *
 */
public class UtilisateurManager {

//////////////////////////////////////// SINGLETON /////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////// ATTRIBUTS D'INSTANCE ////////////////////////////////////////////////////
	private UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateur();
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

///////////////////////////////////////// METHODES PUBLICS/////////////////////////////////////////////////////////
	/**
	 * Méthode permettant l'ajout d'un nouvel utilisateur dans la base de données
	 * @param pseudo			pseudonyme de l'utilisateur sur la plate-forme 			(String)
	 * @param nom 				nom de l'utilisateur									(String)
	 * @param prenom			prénom de l'utilisateur									(String)
	 * @param email				adresse mail de l'utilisateur							(String)
	 * @param telephone			numéro de téléphone de l'utilisateur					(String)
	 * @param rue				numéro et nom de la rue de l'utilisateur				(String)
	 * @param code_postal		code postal de l'utilisateur							(String)
	 * @param ville				nom de la ville de l'utilisateur						(String)
	 * @param mdp				mot de passe pour le profil de l'utilisateur			(String)
	 * @param credit			crédit initial du compte de l'utilisateur				(int)
	 * @param administrateur	statut de l'utilisateur (est-il administrateur ou non)	(boolean)
	 */
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
	
	/**
	 * Méthode permettant de retourner un utilisateur présent dans la base de données via un identifiant et un mot de passe
	 * @param identifiant	adresse mail ou pseudo de l'utilisateur (String)
	 * @param mdp			mot de passe du compte de l'utilisateur (String)
	 * @return				un objet de la classe boUtilisateur		(boUtilisateur)
	 */
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
///////////////////////////////////////// METHODES PRIVEES /////////////////////////////////////////////////////////
	/**
	 * Méthode permettant de vérifier qu'une données de type String est bien renseignée
	 * @param data	donnée à vérifier			(String)
	 * @return		statut de la vérification	(boolean)
	 */
	private boolean verifDataString(String data) {
		boolean statut = false;
		
		if(!data.equals("")) {
			statut = true;
		}
		
		return statut;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
}