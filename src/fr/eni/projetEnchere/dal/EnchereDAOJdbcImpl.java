package fr.eni.projetEnchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.bo.boCategorie;
import fr.eni.projetEnchere.bo.boEnchere;
import fr.eni.projetEnchere.bo.boUtilisateur;
import fr.eni.projetEnchere.dal.jdbcTools.JdbcTools;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String INSERT = "INSERT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur) "
			+ "VALUES(CURRENT_TIMESTAMP,?,?,?)";

	private static final String SELECT_DATE_ARTICLE_ID = "SELECT * FROM(SELECT date_enchere, no_article FROM ENCHERES) as select";

	@Override
	public void insert(boEnchere nvlEnchere) {
		try (Connection cnx = JdbcTools.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, nvlEnchere.getMontantEnchere());
			pstmt.setInt(2, nvlEnchere.getArticleId().getNoArticle());
			pstmt.setInt(3, nvlEnchere.getUtilisateurId().getNoUtilisateur());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				nvlEnchere.setNoEnchere(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boEnchere selectDateArticleId(LocalDate dateEnchere, boArticleVendu articleId) throws SQLException {
		boEnchere select = null;

		try (Connection cnx = JdbcTools.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_DATE_ARTICLE_ID);

			pstmt.setDate(1, Date.valueOf(dateEnchere));
			pstmt.setObject(2, articleId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				LocalDate date = rs.getDate("date_enchere").toLocalDate();
				boArticleVendu id = (boArticleVendu) rs.getObject("no_article");
				select = new boEnchere(date, id);

			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesErreursDAL.ARTICLE_INSERT_ERREUR);
		}
		return select;

	}
}