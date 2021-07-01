package fr.eni.projetEnchere.dal;

import java.util.List;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.bo.boUtilisateur;

public interface ArticleVenduDAO {
	
	void insert(boArticleVendu nvlArticle) throws BusinessException;

	List<boArticleVendu> selectAll() throws BusinessException;
	
	boArticleVendu selectById(int noArticle);
<<<<<<< HEAD

	List<boArticleVendu> selectEncheresOuvertes(String zoneRecherche, int noCategorie, List<boArticleVendu> lst, BusinessException be) throws BusinessException;
	List<boArticleVendu> selectMesEncheres(boUtilisateur utilisateur, String zoneRecherche, int noCategorie, List<boArticleVendu> lst, BusinessException be) throws BusinessException;
	List<boArticleVendu> selectEncheresEmportees(boUtilisateur utilisateur, String zoneRecherche, int noCategorie, List<boArticleVendu> lst, BusinessException be) throws BusinessException;
	
	List<boArticleVendu> selectMesVentesEnCours(boUtilisateur utilisateur, String zoneRecherche, int noCategorie, List<boArticleVendu> lst, BusinessException be) throws BusinessException;
	List<boArticleVendu> selectMesVentesNonDebutees(boUtilisateur utilisateur, String zoneRecherche, int noCategorie, List<boArticleVendu> lst, BusinessException be) throws BusinessException;
	List<boArticleVendu> selectMesVentesTerminees(boUtilisateur utilisateur, String zoneRecherche, int noCategorie, List<boArticleVendu> lst, BusinessException be) throws BusinessException;
=======
>>>>>>> branch 'main' of https://github.com/manuelCoignard/projetEncheres.org
	
}
