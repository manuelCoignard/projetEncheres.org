package fr.eni.projetEnchere.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEnchere.bll.ArticleVenduManager;

/**
 * Servlet implementation class ServletAjoutArticle
 */
@WebServlet("/ServletAjoutArticle")
public class ServletAjoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAjoutArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ajoutArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		LocalDate dateDebutEnchere = LocalDate.parse(request.getParameter("dateDebutEncheres"));
		LocalDate dateFinEnchere = LocalDate.parse(request.getParameter("dateFinEncheres"));
		int prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
		int prixVente = Integer.parseInt(request.getParameter("prixVente"));
		//TODO a modifier en session
		int idUtilisateur = 1;
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		
		//
		try {
			ArticleVenduManager.getInstance().ajoutArticle(nomArticle, description, dateDebutEnchere, dateFinEnchere, prixInitial, prixVente, idUtilisateur, categorie);
		}catch (Exception e) {
			//TODO gerer validation erreur
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
