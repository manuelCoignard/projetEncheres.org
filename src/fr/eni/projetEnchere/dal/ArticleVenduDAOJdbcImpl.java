package fr.eni.projetEnchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.dal.jdbcTools.JdbcTools;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String INSERT = "INSERT INTO "
			+ "ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)"
			+ "VALUES(?,?,?,?,?,?,?,?)";
	
	private static final String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente"
											+ "FROM ARTICLES_VENDUS";

	@Override
	public void insert(boArticleVendu nvlArticle) throws BusinessException {
		// TODO Auto-generated method stub
		try (Connection cnx = JdbcTools.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, nvlArticle.getNomArticle());
			pstmt.setString(2, nvlArticle.getDescription());
			pstmt.setDate(3, Date.valueOf(nvlArticle.getDateDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(nvlArticle.getFinDebutEncheres()));
			pstmt.setInt(5, nvlArticle.getPrixInitial());
			pstmt.setInt(6, nvlArticle.getPrixVente());
			pstmt.setInt(7, nvlArticle.getNoUtilisateur());
			pstmt.setInt(8, nvlArticle.getNoCategorie());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				nvlArticle.setNoArticle(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesErreursDAL.ARTICLE_INSERT_ERREUR);
		}
	}

	public List<boArticleVendu> listeArticle() throws BusinessException {
		//System.out.println("je suis dans le select");
		List<boArticleVendu> listeArticle = new ArrayList<>();
			try(Connection cnx = JdbcTools.getConnection()){
				Statement stmt = cnx.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_ALL);
				while (rs.next()) {
					int noArticle = rs.getInt("no_article");
					String nomArticle = rs.getString("nom_article");
					String description = rs.getString("description");
					LocalDate dateDebutEncheres = rs.getDate("date_debut_encheres").toLocalDate();
					LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
					int prixInitial = rs.getInt("prix_initial");
					int prixVente = rs.getInt("prix_vente");

				
				boArticleVendu articleVendu = new boArticleVendu(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente);
				listeArticle.add(articleVendu);
				System.out.println(articleVendu.toString());
				}

			} catch (Exception e) {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesErreursDAL.ARTICLE_SELECTALL_ERREUR);
				throw businessException;
			}
		return listeArticle;
	}
}
