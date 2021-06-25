package fr.eni.projetEnchere.bll;

import fr.eni.projetEnchere.BusinessException;
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


///////////////////////////////////////// ATTRIBUTS D'INSTANCE ////////////////////////////////////////////////////
	private UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateur();


///////////////////////////////////////// METHODES PUBLICS/////////////////////////////////////////////////////////
	/**
	 * Méthode permettant l'ajout d'un nouvel utilisateur dans la base de données
	 * @param pseudo			pseudonyme de l'utilisateur sur la plate-forme 			(String)
	 * @param nom 				nom de l'utilisateur									(String)
	 * @param prenom			prénom de l'utilisateur									(String)
	 * @param email				adresse mail de l'utilisateur							(String)
	 * @param telephone			numéro de téléphone de l'utilisateur					(String)
	 * @param rue				numéro et nom de la rue de l'utilisateur				(String)
	 * @param codePostal		code postal de l'utilisateur							(String)
	 * @param ville				nom de la ville de l'utilisateur						(String)
	 * @param mdp				mot de passe pour le profil de l'utilisateur			(String)
	 */
	public void ajoutNouvelUtilisateur (String pseudo,String nom,String prenom,String email,String telephone,String rue,String codePostal,String ville,String mdp) throws BusinessException {
			BusinessException be = new BusinessException();
		
			// Création de l'utilisateur
			boUtilisateur nvlUtilisateur = new boUtilisateur(pseudo,nom,prenom,email,telephone,rue,codePostal,ville,mdp);
			
			// Vérification des données provenant du formulaire
			
			methodeGestionErreur(nvlUtilisateur,be);
			
			if (be.hasErreurs()) {
				throw be;
			}
			// Si pas erreur : Ajout dans la BDD
			utilisateurDAO.insert(nvlUtilisateur);
			
			
			
			
			
	}
	
	/**
	 * Méthode permettant de retourner un utilisateur présent dans la base de données via un identifiant et un mot de passe
	 * @param identifiant	adresse mail ou pseudo de l'utilisateur (String)
	 * @param mdp			mot de passe du compte de l'utilisateur (String)
	 * @return				un objet de la classe boUtilisateur		(boUtilisateur)
	 * @throws BusinessException 
	 */
	public boUtilisateur connexionUtilisateur(String identifiant, String mdp) throws BusinessException {
		boUtilisateur utilisateurConnecte = null;
		
		//Vérification des données provenant du formulaire
		
		
		
		//Choix de la connexion via pseudo ou adresse mail
		if(identifiant.contains("@")) {
			//utilisation de la méthode avec email
			utilisateurConnecte = utilisateurDAO.connectionEmail(identifiant);
			
		} else {
			//utilisation de la méthode avec pseudo
			utilisateurConnecte = utilisateurDAO.connectionPseudo(identifiant);
		}
		
		//Vérfication de la concordance des mots de passe
		if(!utilisateurConnecte.getMotDePpasse().equals(mdp)) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesErreursBLL.CONNECT_WRONG_PASSWORD);
			throw be;
		}
				
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
	
	

	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///                          PARTIES CONCERNANT LES GESTIONS D'ERREURS											///
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
/**
 * Methode Mere qui a en parametres un utilisateur et une businessException, qui appel les methodes enfants concernant elles-memes chaque erreur precise.
 * 	
 */
	
	
	private void methodeGestionErreur(boUtilisateur utilisateur, BusinessException businessException) {
		validerPseudo(utilisateur, businessException);
		validerNom(utilisateur, businessException);
		validerPrenom(utilisateur, businessException);
		validerEmail(utilisateur, businessException);
		validerTelephone(utilisateur, businessException);
		validerCodePostal(utilisateur, businessException);
		validerPassword(utilisateur, businessException);
}
	
	
	private	void validerPseudo(boUtilisateur utilisateur, BusinessException businessException) {
		String checkPseudoAlphanumeric = "/[A-Za-z][0-9]";
		
		
		if(utilisateur.getPseudo().length() < 3 && utilisateur.getPseudo().length() > 20 ) {
			businessException.ajouterErreur(CodesErreursBLL.PSEUDO_TAILLE_ERREUR);
		}
		
		if(utilisateur.getPseudo().matches(checkPseudoAlphanumeric)) {
			businessException.ajouterErreur(CodesErreursBLL.PSEUDO_ALPHANUMERIC_ERREUR);
		}		
	}
	
	private	void validerNom(boUtilisateur utilisateur, BusinessException businessException) {
		
		if(utilisateur.getNom().length() < 3 && utilisateur.getNom().length() > 30) {
			businessException.ajouterErreur(CodesErreursBLL.NOM_UTILISATEUR_ERREUR);
		}
	}
	
	private	void validerPrenom(boUtilisateur utilisateur, BusinessException businessException) {
		
		if(utilisateur.getPrenom().length() < 3 && utilisateur.getPrenom().length() > 30) {
			businessException.ajouterErreur(CodesErreursBLL.PRENOM_UTILISATEUR_ERREUR);
		}
	}
	
	private void validerEmail(boUtilisateur utilisateur, BusinessException businessException) {
		String checkEmail = "^[\\w+.-]+@\\w+.\\w{2,5}$";
		
		if(!utilisateur.getEmail().matches(checkEmail)) {
			businessException.ajouterErreur(CodesErreursBLL.EMAIL_REGEX_ERREUR);
		}
	}
	
	private void validerTelephone(boUtilisateur utilisateur, BusinessException businessException) {
		String checkTelephone = "(\\+[0-9]{3}( [0-9][0-9])+)|([0-9]+)";
		
		if(!utilisateur.getEmail().matches(checkTelephone)) {
			businessException.ajouterErreur(CodesErreursBLL.TELEPHONE_REGEX_ERREUR);
		}
	}
	
	private void validerCodePostal(boUtilisateur utilisateur, BusinessException businessException) {
		String checkCodePostal = "[0-9]{5}";
		
		if(!utilisateur.getEmail().matches(checkCodePostal)) {
			businessException.ajouterErreur(CodesErreursBLL.CODEPOSTAL_REGEX_ERREUR);
		}
	}
	
	private void validerPassword(boUtilisateur utilisateur, BusinessException businessException) {
		// Doit contenir au minimum 8 caracteres, au minimum une minuscule, une majuscule, un chiffre, un caractere special
		String checkPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		   
		if (utilisateur.getMotDePpasse().length() < 6 && utilisateur.getMotDePpasse().length() > 30 && !utilisateur.getMotDePpasse().matches(checkPassword)) {
			businessException.ajouterErreur(CodesErreursBLL.PASSWORD_REGEX_ERREUR);
		}
				
	}
	
	
}