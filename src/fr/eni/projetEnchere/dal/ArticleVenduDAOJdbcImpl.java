package fr.eni.projetEnchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.dal.jdbcTools.JdbcTools;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String INSERT = "INSERT INTO "
			+ "ARTICLES_VENDUS (no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente)"
			+ "VALUES(?,?,?,?,?,?,?)";
	
	private static final String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente"
											+ "FROM ARTICLEVENDU";

	@Override
	public void insert(boArticleVendu nvlArticle) {
		// TODO Auto-generated method stub
		try (Connection cnx = JdbcTools.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, nvlArticle.getNoArticle());
			pstmt.setString(2, nvlArticle.getNomArticle());
			pstmt.setString(3, nvlArticle.getDescription());
			pstmt.setDate(4, nvlArticle.getDateDebutEncheres());
			pstmt.setDate(5, nvlArticle.getFinDebutEncheres());
			pstmt.setInt(6, nvlArticle.getPrixInitial());
			pstmt.setInt(7, nvlArticle.getPrixVente());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				nvlArticle.setNoArticle(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<boArticleVendu> listeArticle() {
		List<boArticleVendu> listeArticle = new ArrayList<>();
			try(Connection cnx = JdbcTools.getConnection()){
				Statement stmt = cnx.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_ALL);
				while (rs.next()) {
					int noArticle = rs.getInt("no_article");
					String nomArticle = rs.getString("nom_article");
					String description = rs.getString("description");
					Date dateDebutEncheres = rs.getDate("date_debut_encheres");
					Date dateFinEncheres = rs.getDate("date_fin_encheres");
					int prixInitial = rs.getInt("prix_initial");
					int prixVente = rs.getInt("prix_vente");
					
				boArticleVendu articleVendu = new boArticleVendu(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente);
				listeArticle.add(articleVendu);
				}

			} catch (SQLException e) {
				// TODO: handle exception
			}
		return listeArticle;
	}
}
