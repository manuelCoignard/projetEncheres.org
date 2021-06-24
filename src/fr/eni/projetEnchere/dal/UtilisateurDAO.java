package fr.eni.projetEnchere.dal;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boUtilisateur;

public interface UtilisateurDAO {
	
	void insert(boUtilisateur nouvelUtilisateur) throws BusinessException;
	
	boUtilisateur connectionEmail(String email, String mdp) throws BusinessException;
	
	boUtilisateur connectionPseudo(String pseudo, String mdp) throws BusinessException;

}
