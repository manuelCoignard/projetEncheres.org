package fr.eni.projetEnchere.dal;

import java.util.List;

import fr.eni.projetEnchere.bo.boArticleVendu;

public interface ArticleVenduDAO {

	void insert(boArticleVendu nvlArticle);

	List<boArticleVendu> listeArticle();
}
