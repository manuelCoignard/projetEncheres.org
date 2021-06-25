package fr.eni.projetEnchere.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

import fr.eni.projetEnchere.BusinessException;
import fr.eni.projetEnchere.bll.ArticleVenduManager;
import fr.eni.projetEnchere.bll.CategorieManager;
import fr.eni.projetEnchere.bo.boArticleVendu;
import fr.eni.projetEnchere.bo.boCategorie;
import fr.eni.projetEnchere.exceptions.LecteurMessage;

/**
 * Servlet implementation class ServletPageAccueil
 */
@WebServlet("/ServletPageAccueil")
public class ServletPageAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * //2. on va chercher la liste en BDD ListeCourse listeCourse =
		 * ListeCourseManager.getInstance().selectById(idListe); //3. on stocke la liste
		 * de course dans la requête request.setAttribute("listeCourse", listeCourse);
		 */

		try {
			// Récupération de la liste de tous les articles disponibles
			List<boArticleVendu> listeArticle = new ArrayList<>();
			listeArticle = ArticleVenduManager.getInstance().selectAll();
			request.setAttribute("listeArticle", listeArticle);
			
			//Récupération de la liste des catégories
			List<boCategorie> lstCategorie = new ArrayList<>();
			lstCategorie = CategorieManager.getInstance().selectAllCategories();
			request.setAttribute("lstCategorie", lstCategorie);

		} catch (BusinessException be) {
			List<String> lstMsgError = new ArrayList<>();
			for (int code : be.getListeCodesErreur()) {
				lstMsgError.add(LecteurMessage.getMessageErreur(code));
			}
			
			request.setAttribute("listeMessagesErreur", lstMsgError);
		}
		
		//Affichage de la .jsp
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
