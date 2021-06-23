package fr.eni.projetEnchere.bll;

public abstract class CodesErreursBLL {

	public static final int EMAIL_REGEX_ERREUR = 20000;
	
	public static final int PSEUDO_TAILLE_ERREUR= 20001;
	
	public static final int PSEUDO_ALPHANUMERIC_ERREUR= 20002;
	
	public static final int NOM_UTILISATEUR_ERREUR= 20003;
	
	public static final int PRENOM_UTILISATEUR_ERREUR= 20004;
	
	public static final int TELEPHONE_REGEX_ERREUR= 20005;
	
	public static final int CODEPOSTAL_REGEX_ERREUR= 20006;
	
	public static final int PASSWORD_REGEX_ERREUR= 20007;
	
	
	public static final int ARTICLE_NOM_ERREUR = 20100;

	public static final int ARTICLE_DESCRIPTON_ERREUR = 20101;
	
	public static final int ARTICLE_DATE_ERREUR = 20102;
	
	public static final int ARTICLE_PRIX_ERREUR = 20103;
	
	public static final int ARTICLE_INSERT_ERREUR = 20104;
}
