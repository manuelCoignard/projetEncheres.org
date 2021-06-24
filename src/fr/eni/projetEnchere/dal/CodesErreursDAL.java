package fr.eni.projetEnchere.dal;

public class CodesErreursDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_USER_NULL=10000;
	
	/**
	 * Echec général lors de la recherche d'un utilisateur
	 */
	public static final int SELECT_INVALID_USER = 11000;
	
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
