package fr.eni.projetEnchere.dal.jdbcTools;

import fr.eni.projetEnchere.dal.UtilisateurDAO;
import fr.eni.projetEnchere.dal.UtilisateurDAOJdbcImpl;

public abstract class DAOFactory {

	public static UtilisateurDAO getUtilisateur() {
		return new UtilisateurDAOJdbcImpl();
	}
	
}
