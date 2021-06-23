package fr.eni.projetEnchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projetEnchere.bo.boUtilisateur;
import fr.eni.projetEnchere.dal.jdbcTools.JdbcTools;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	
	private static final String INSERT = "INSERT INTO "
									   + "UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)"
									   + " VALUES (?,?,?,?,?,?,?,?,?,100,0);";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=? AND mot_de_passe=?;";
	private static final String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=? AND mot_de_passe=?;";

	@Override
	public void insert(boUtilisateur nouvelUtilisateur) {

		try(Connection cnx = JdbcTools.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, nouvelUtilisateur.getPseudo());
			pstmt.setString(2, nouvelUtilisateur.getNom());
			pstmt.setString(3, nouvelUtilisateur.getPrenom());
			pstmt.setString(4, nouvelUtilisateur.getEmail());			
			pstmt.setString(5, nouvelUtilisateur.getTelephone().equals("")?null:nouvelUtilisateur.getTelephone());
			pstmt.setString(6, nouvelUtilisateur.getRue());
			pstmt.setString(7, nouvelUtilisateur.getCodePostal());
			pstmt.setString(8, nouvelUtilisateur.getVille());
			pstmt.setString(9, nouvelUtilisateur.getMotDePpasse());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next())
			{
				nouvelUtilisateur.setNoUtilisateur(rs.getInt(1));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public boUtilisateur connectionEmail(String email, String mdp) {
		
		boUtilisateur utilisateur = null;
		
		try(Connection cnx = JdbcTools.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_EMAIL);
			
			pstmt.setString(1, email);
			pstmt.setString(2, mdp);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				int noId = rs.getInt("no_utilisateur");
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String tel = rs.getString("telephone");
				String adresse = rs.getString("rue");
				String cp = rs.getString("code_postal");
				String ville = rs.getString("ville");
				int credit = rs.getInt("credit");
				boolean admin = rs.getBoolean("administrateur");
				
				utilisateur =new boUtilisateur(noId, pseudo, nom, prenom, email, tel, adresse, cp, ville, mdp, credit, admin);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return utilisateur;
	}

	@Override
	public boUtilisateur connectionPseudo(String pseudo, String mdp) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
