package fr.eni.projetEnchere.dal;

import java.util.List;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boArticleVendu;

public interface ArticleVenduDAO {

	void insert(boArticleVendu nvlArticle) throws BusinessException;

	List<boArticleVendu> listeArticle() throws BusinessException;
}
