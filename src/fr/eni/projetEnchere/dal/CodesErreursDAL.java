package fr.eni.projetEnchere.dal;

public class CodesErreursDAL {
	
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_USER_NULL = 10000;
	
	/**
	 * Erreur quand tentative création utilisateur avec Email déjà en base
	 */	
	public static final int INSERT_EMAIL_ERREUR = 10001;
	
	/**
	 * Erreur quand tentative création utilisateur avec Pseudo déjà en base
	 */	
	public static final int INSERT_PSEUDO_ERREUR = 10002;
	
	/**
	 * Erreur majeure dans la recherche de l'utilisateur
	 */
	public static final int SELECT_USER_ERROR = 11000;
	
	/**
	 * Aucun utilisateur retourné lors de la recherche
	 */
	public static final int NO_USER_FOUND = 11001;
	
	/**
	 * Plusieurs utilisateurs retournés lors de la recherche
	 */
	public static final int MULTIPLE_USERS_FOUND = 11002;
	
	public static final int ARTICLE_INSERT_ERREUR = 10100;

	public static final int ARTICLE_SELECTALL_ERREUR = 10101;
	
	
	
}
