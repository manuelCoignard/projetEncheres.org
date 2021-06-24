package fr.eni.projetEnchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boUtilisateur;
import fr.eni.projetEnchere.dal.jdbcTools.JdbcTools;

/**
 * Classe contenant les transactions SQL Server pour la gestion de l'utilisateur
 * @author Groupe G (LORENT Maxime, TANGUY Cyril, COIGNARD Manuel
 *
 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	/////////////////////////////////// CONSTANTES ////////////////////////////////////////
	private static final String INSERT = "INSERT INTO "
									   + "UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)"
									   + " VALUES (?,?,?,?,?,?,?,?,?,100,0);";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=?;";
	private static final String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=? AND mot_de_passe=?;";
	///////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Méthode permet d'insérer un nouvel utilisateur dans la base de données 
	 */
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
	
	/**
	 * Méthode permettant de récupérer un utilisateur dans la base de données via son adresse mail
	 * et retournant un objet boUtilisateur
	 */
	@Override
	public boUtilisateur connectionEmail(String email, String mdp) throws BusinessException {
		//TODO créer la variable BusinessException dès le début de la méthode
		
		boUtilisateur utilisateur = null;
		
		try(Connection cnx = JdbcTools.getConnection()){
			
			/* Utilisation des constantes ResultSet.TYPE_SCROLL_INSENSITIVE pour permettre la navigation dans le ResultSet
			 * et de ResultSet.CONCUR_READ_ONLY pour ne permettre que la lecture seule
			 */
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_EMAIL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			pstmt.setString(1, email);
			
			ResultSet rs= pstmt.executeQuery();

				int nb = isUnique(rs);
				
				if(nb!=1) {
					BusinessException be = new BusinessException();
					switch(nb) {
						case 0  : be.ajouterErreur(CodesErreursDAL.NO_USER_FOUND);
								  break;
						default : be.ajouterErreur(CodesErreursDAL.MULTIPLE_USERS_FOUND);
					}
					throw be;
				}
				
				//Positionne le curseur sur la 1ère ligne de résultat du ResultSet
				rs.absolute(1);
				
				//Vérfication de la concordance des mots de passe
				if(!rs.getString("mot_de_passe").equals(mdp)) {
					BusinessException be = new BusinessException();
					//TODO ajout du message d'erreur
				}
				
				//Récupération des données de l'utilisateur 
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
		catch(SQLException e)
		{
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesErreursDAL.SELECT_INVALID_USER);
			throw be;
		}

		return utilisateur;
	}

	/**
	 * Méthode permettant de récupérer un utilisateur dans la base de données via son pseudo
	 * et retournant un objet boUtilisateur
	 */
	@Override
	public boUtilisateur connectionPseudo(String pseudo, String mdp) throws BusinessException {
		
		boUtilisateur utilisateur = null;
		
		try(Connection cnx = JdbcTools.getConnection()){
			
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			
			pstmt.setString(1, pseudo);
			pstmt.setString(2, mdp);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				int noId = rs.getInt("no_utilisateur");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String tel = rs.getString("telephone");
				String adresse = rs.getString("rue");
				String cp = rs.getString("code_postal");
				String ville = rs.getString("ville");
				int credit = rs.getInt("credit");
				boolean admin = rs.getBoolean("administrateur");
				
				utilisateur =new boUtilisateur(noId, pseudo, nom, prenom, email, tel, adresse, cp, ville, mdp, credit, admin);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return utilisateur;
	}
	
	/**
	 * Méthode permettant de connaître le nombre de ligne dans le résultat de la transaction SQL
	 * @param rs résultat de la transaction SQL exécutée (ResultSet)
	 * @return le nombre de ligne contenu dans le rs
	 * @throws SQLException en cas d'erreur, remonte l'exception pour être gérée par la méthode appelante
	 */
	private int isUnique(ResultSet rs) throws SQLException {
		//Initialisation du compteur de ligne
		int nb=0;
		
		//Positionne le curseur sur la dernière ligne du rs
		if(rs.last()) {
			//récupère le numéro de la ligne
			nb = rs.getRow();
		}
		
		//Retourne le numéro de ligne récupérée
		return nb;
	}

}
