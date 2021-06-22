package fr.eni.projetEnchere.bo;

import java.sql.Date;

public class boArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date finDebutEncheres;
	private int prixInitial;
	private int prixVente;
	private int noUtilisateur;
	private int noCategorie;
	private int noRetrait;
	
	
	//////GETTERS ET SETTERS//////
	
	
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public Date getFinDebutEncheres() {
		return finDebutEncheres;
	}
	public void setFinDebutEncheres(Date finDebutEncheres) {
		this.finDebutEncheres = finDebutEncheres;
	}
	public int getPrixInitial() {
		return prixInitial;
	}
	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	public int getNoRetrait() {
		return noRetrait;
	}
	public void setNoRetrait(int noRetrait) {
		this.noRetrait = noRetrait;
	}
	//////CONSTRUCTEURS///////
	

	// constructeur vide
	public boArticleVendu() {
		
	}
	
	// constructeur de l'article 
	public boArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date finDebutEncheres, int prixInitial, int prixVente) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.finDebutEncheres = finDebutEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;	
	}
	@Override
	public String toString() {
		return "boArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", finDebutEncheres=" + finDebutEncheres
				+ ", prixInitial=" + prixInitial + ", prixVente=" + prixVente + ", noUtilisateur=" + noUtilisateur
				+ ", noCategorie=" + noCategorie + ", noRetrait=" + noRetrait + "]";
	}
	
}
