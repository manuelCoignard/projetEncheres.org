package fr.eni.projetEnchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.bo.boCategorie;
import fr.eni.projetEnchere.bo.boEnchere;
import fr.eni.projetEnchere.bo.boUtilisateur;
import fr.eni.projetEnchere.dal.jdbcTools.JdbcTools;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String INSERT = "INSERT INTO "
			+ "ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)"
			+ "VALUES(?,?,?,?,?,?,?,?)";
	
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur";
	
	
	private static final String SELECT_BY_ID = "SELECT ARTICLES_VENDUS.no_article, nom_article,description, prix_initial, date_fin_encheres, CATEGORIES.no_categorie AS numCategorie, libelle, VENDEUR.no_utilisateur AS numVendeur , VENDEUR.pseudo as vendeur, VENDEUR.rue as vendeurRue, VENDEUR.code_postal as vendeurCp ,VENDEUR.ville as vendeurVille ,VENDEUR.credit as vendeurCredit, no_enchere, MAX(montant_enchere) as montant_enchere, ACHETEUR.no_utilisateur AS numAcheteur,ACHETEUR.pseudo AS acheteur FROM ARTICLES_VENDUS " + 
			"LEFT JOIN ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article " + 
			"LEFT JOIN UTILISATEURS AS VENDEUR ON ARTICLES_VENDUS.no_utilisateur = VENDEUR.no_utilisateur " + 
			"LEFT JOIN UTILISATEURS AS ACHETEUR ON ENCHERES.no_utilisateur = ACHETEUR.no_utilisateur " + 
			"LEFT JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie " + 
			"WHERE ARTICLES_VENDUS.no_article = ? " + 
			"GROUP BY ARTICLES_VENDUS.no_article, nom_article,  description, prix_initial, date_fin_encheres, CATEGORIES.no_categorie, CATEGORIES.libelle, VENDEUR.no_utilisateur , VENDEUR.pseudo, VENDEUR.rue, VENDEUR.code_postal, VENDEUR.ville,VENDEUR.credit, no_enchere, ACHETEUR.pseudo, ACHETEUR.no_utilisateur " + 
			"ORDER BY montant_enchere DESC" ;
	
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
	
	
	
	public List<boArticleVendu> selectAll() throws BusinessException {
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
					int categorie = rs.getInt("no_categorie");
					
					int idVendeur = rs.getInt("no_utilisateur");
					String pseudoVendeur = rs.getString("pseudo");

				boArticleVendu articleVendu = new boArticleVendu(noArticle,nomArticle,dateFinEncheres,prixInitial,new boUtilisateur(idVendeur,pseudoVendeur));
				//boArticleVendu articleVendu = new boArticleVendu(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente, idUtilisateur, categorie);
				listeArticle.add(articleVendu);
				}

			} catch (Exception e) {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesErreursDAL.ARTICLE_SELECTALL_ERREUR);
				throw businessException;
			}
		return listeArticle;
	}
	
	@Override
	public boArticleVendu selectById(int noArticle) {
		boArticleVendu articleId = null;
				try (Connection cnx = JdbcTools.getConnection()) {

					PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);

					pstmt.setInt(1, noArticle);

					ResultSet rs = pstmt.executeQuery();

					if (rs.next()) {
						String nomArticle = rs.getString("nom_article");
						String description = rs.getString("description");
						LocalDate dateFinEncheres = rs.getDate("date_fin_encheres").toLocalDate();
						int prixInitial = rs.getInt("prix_initial");
						int categorie = rs.getInt("numCategorie");
						String libelle = rs.getString("libelle");
						int idVendeur = rs.getInt("numVendeur");
						String pseudoVendeur = rs.getString("vendeur");
						String adresse = rs.getString("vendeurRue");
						String codePostal = rs.getString("vendeurCp");
						String ville = rs.getString("vendeurVille");
						int credit = rs.getInt("vendeurCredit");
						int noEnchere = rs.getInt("no_enchere");
						int montantEnchere = rs.getInt("montant_enchere");
						int idAcheteur = rs.getInt("numAcheteur");
						String pseudoAcheteur = rs.getString("acheteur");
						
						boUtilisateur acheteur = new boUtilisateur(idAcheteur, pseudoAcheteur);
						articleId = new boArticleVendu(noArticle, nomArticle, description, dateFinEncheres,prixInitial,
								new boCategorie(categorie, libelle), 
								new boUtilisateur(idVendeur, pseudoVendeur, adresse, codePostal, ville, credit), 
								new boEnchere(noEnchere, montantEnchere, acheteur ));
					}

				} catch (Exception e) {
					e.printStackTrace();
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesErreursDAL.ARTICLE_INSERT_ERREUR);
				}
		return articleId;
	}
}
