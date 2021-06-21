package fr.eni.projetEnchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.projetEnchere.bo.boUtilisateur;
import fr.eni.projetEnchere.dal.jdbcTools.JdbcTools;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	
	private static final String INSERT = "INSERT INTO "
									   + "UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur"
									   + " VALUES (?,?,?,?,?,?,?,?,?,?,?);";

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
			pstmt.setString(7, nouvelUtilisateur.getCode_postal());
			pstmt.setString(8, nouvelUtilisateur.getVille());
			pstmt.setString(9, nouvelUtilisateur.getMot_de_passe());
			pstmt.setInt(10, nouvelUtilisateur.getCredit());
			pstmt.setBoolean(11, nouvelUtilisateur.isAdministrateur());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next())
			{
				nouvelUtilisateur.setNo_utilisateur(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	

}