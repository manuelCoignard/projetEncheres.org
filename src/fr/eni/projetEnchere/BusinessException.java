package fr.eni.projetEnchere;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Groupe G (LORENT Maxime, TANGUY Cyril, COIGNARD Manuel
 *
 * Classe listant l'ensemble des erreurs (par leur code) pouvant survenir lors d'un traitement
 * quel que soit la couche à l'origine.
 */
public class BusinessException extends Exception {
	//------------------------- CONSTANTES --------------------------//
	private static final long serialVersionUID = 1L;
	//---------------------------------------------------------------//
	
	//------------------------- ATTRIBUTS D'INSTANCE -----------------//
	private List<Integer> listeCodesErreur;
	//----------------------------------------------------------------//
	
	//----------------------- CONSTRUCTEURS --------------------------//
	/**
	 * Constructeur de la classe créant une nouvelle liste de codes erreurs
	 */
	public BusinessException() {
		super();
		this.listeCodesErreur=new ArrayList<>();
	}
	//----------------------------------------------------------------//
	
	//-------------------------- METHODES ---------------------------//
	/**
	 * Méthode permettant l'ajout d'un code erreur dans la liste des codes erreurs
	 * @param code Code de l'erreur (int)
	 * 
	 * ATTENTION : Ce code doit avoir un message associé dans un fichier properties !!
	 */
	public void ajouterErreur(int code)
	{
		if(!this.listeCodesErreur.contains(code))
		{
			this.listeCodesErreur.add(code);
		}
	}
	
	/**
	 * Méthode permettant de savoir si des erreurs ont été remontées
	 * @return false s'il existe au moins 1 erreur dans la liste
	 */
	public boolean hasErreurs()
	{
		return this.listeCodesErreur.size()>0;
	}
	
	/**
	 * Méthode retournant la liste des codes erreurs
	 * @return la liste des codes erreurs (List<Integer>)
	 */
	public List<Integer> getListeCodesErreur()
	{
		return this.listeCodesErreur;
	}
	//---------------------------------------------------------------//
}