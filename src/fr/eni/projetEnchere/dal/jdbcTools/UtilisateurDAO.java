package fr.eni.projetEnchere.dal.jdbcTools;

public interface UtilisateurDAO {
	
	void insertNewUserWithPhoneNumber(
			String pseudo, 
			String nom, 
			String prenom, 
			String email,
			String telephone, 
			String rue, 
			String code_postal, 
			String ville, 
			String mot_de_passe, 
			int credit,
			boolean administrateur);
	
	void insertNewUserWithoutPhoneNumber(
			String pseudo, 
			String nom, 
			String prenom, 
			String email,
			String rue, 
			String code_postal, 
			String ville, 
			String mot_de_passe, 
			int credit,
			boolean administrateur);

}
